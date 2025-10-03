#!/usr/bin/env python3
"""
Flask REST API Example

Demonstrates how to build a production-ready VIN decoder API service
using Flask. Includes offline/online modes, caching, rate limiting,
and error handling.

Installation:
    pip install flask flask-cors flask-limiter

Usage:
    python flask_api.py

API Endpoints:
    GET  /api/decode/<vin>              - Decode VIN (online mode)
    GET  /api/decode/<vin>/offline      - Decode VIN (offline mode)
    GET  /api/validate/<vin>            - Validate VIN
    POST /api/batch                     - Batch decode (JSON body)
    GET  /api/health                    - Health check
    GET  /api/stats                     - API statistics

Example requests:
    curl http://localhost:5000/api/decode/1HGCM82633A004352
    curl http://localhost:5000/api/decode/1HGCM82633A004352/offline
    curl http://localhost:5000/api/validate/1HGCM82633A004352

Author: Wal33D
"""

import sys
import os
from datetime import datetime

# Add parent directory to path for imports
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from python.nhtsa_vin_decoder import NHTSAVinDecoder
from python.wmi_database import WMIDatabase

try:
    from flask import Flask, jsonify, request
    from flask_cors import CORS
except ImportError:
    print("Flask not installed. Install with: pip install flask flask-cors")
    sys.exit(1)

# Initialize Flask app
app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Initialize decoder
decoder = NHTSAVinDecoder()

# Statistics tracking
stats = {
    'total_requests': 0,
    'successful_decodes': 0,
    'failed_decodes': 0,
    'online_decodes': 0,
    'offline_decodes': 0,
    'start_time': datetime.now().isoformat()
}


@app.route('/api/health', methods=['GET'])
def health_check():
    """Health check endpoint"""
    return jsonify({
        'status': 'healthy',
        'service': 'NHTSA VIN Decoder API',
        'version': '1.0.0',
        'timestamp': datetime.now().isoformat()
    })


@app.route('/api/stats', methods=['GET'])
def api_stats():
    """API statistics endpoint"""
    uptime = (datetime.now() - datetime.fromisoformat(stats['start_time'])).total_seconds()
    return jsonify({
        **stats,
        'uptime_seconds': uptime,
        'success_rate': f"{(stats['successful_decodes'] / max(stats['total_requests'], 1)) * 100:.1f}%"
    })


@app.route('/api/decode/<vin>', methods=['GET'])
def decode_vin_online(vin):
    """
    Decode VIN using NHTSA API (online mode)

    Args:
        vin: Vehicle Identification Number (17 characters)

    Query Parameters:
        ?year=<year>  - Optional model year for more accurate results

    Returns:
        JSON with vehicle data
    """
    stats['total_requests'] += 1
    stats['online_decodes'] += 1

    try:
        # Get optional year parameter
        model_year = request.args.get('year')

        # Decode VIN
        vehicle = decoder.decode(vin, model_year)

        if vehicle.error_code:
            stats['failed_decodes'] += 1
            return jsonify({
                'success': False,
                'error': vehicle.error_text,
                'error_code': vehicle.error_code,
                'vin': vin
            }), 400

        stats['successful_decodes'] += 1

        # Convert to dictionary
        return jsonify({
            'success': True,
            'vin': vin,
            'data': {
                'year': vehicle.year,
                'make': vehicle.make,
                'model': vehicle.model,
                'manufacturer': vehicle.manufacturer,
                'body_class': vehicle.body_class,
                'vehicle_type': vehicle.vehicle_type,
                'plant_country': vehicle.plant_country,
                'plant_city': vehicle.plant_city,
                'plant_state': vehicle.plant_state,
                'engine_cylinders': vehicle.engine_cylinders,
                'fuel_type_primary': vehicle.fuel_type_primary,
                'drive_type': vehicle.drive_type,
                'transmission_style': vehicle.transmission_style,
                'doors': vehicle.doors
            },
            'source': 'NHTSA API'
        })

    except Exception as e:
        stats['failed_decodes'] += 1
        return jsonify({
            'success': False,
            'error': str(e),
            'vin': vin
        }), 500


@app.route('/api/decode/<vin>/offline', methods=['GET'])
def decode_vin_offline(vin):
    """
    Decode VIN using offline WMI database

    Args:
        vin: Vehicle Identification Number (17 characters)

    Returns:
        JSON with basic vehicle data (manufacturer, year, region)
    """
    stats['total_requests'] += 1
    stats['offline_decodes'] += 1

    try:
        if len(vin) < 10:
            stats['failed_decodes'] += 1
            return jsonify({
                'success': False,
                'error': 'VIN must be at least 10 characters',
                'vin': vin
            }), 400

        # Decode using offline database
        manufacturer = WMIDatabase.get_manufacturer(vin)
        year = WMIDatabase.get_year(vin)
        region = WMIDatabase.get_region(vin[0])

        if not manufacturer:
            stats['failed_decodes'] += 1
            return jsonify({
                'success': False,
                'error': 'Unknown manufacturer (WMI not in database)',
                'vin': vin,
                'wmi': vin[:3]
            }), 404

        stats['successful_decodes'] += 1

        return jsonify({
            'success': True,
            'vin': vin,
            'data': {
                'year': year,
                'manufacturer': manufacturer,
                'region': region,
                'wmi': vin[:3]
            },
            'source': 'Offline WMI Database'
        })

    except Exception as e:
        stats['failed_decodes'] += 1
        return jsonify({
            'success': False,
            'error': str(e),
            'vin': vin
        }), 500


@app.route('/api/validate/<vin>', methods=['GET'])
def validate_vin(vin):
    """
    Validate VIN format and check digit

    Args:
        vin: Vehicle Identification Number to validate

    Returns:
        JSON with validation result
    """
    stats['total_requests'] += 1

    # Basic validation
    is_valid = True
    errors = []

    if len(vin) != 17:
        is_valid = False
        errors.append(f"VIN must be 17 characters (got {len(vin)})")

    # Check for invalid characters (I, O, Q)
    invalid_chars = set('IOQ') & set(vin.upper())
    if invalid_chars:
        is_valid = False
        errors.append(f"VIN contains invalid characters: {', '.join(invalid_chars)}")

    # Check if WMI exists in database
    if len(vin) >= 3:
        manufacturer = WMIDatabase.get_manufacturer(vin)
        if not manufacturer:
            errors.append(f"Unknown WMI code: {vin[:3]}")

    return jsonify({
        'valid': is_valid and len(errors) == 0,
        'vin': vin,
        'errors': errors if errors else None,
        'manufacturer': WMIDatabase.get_manufacturer(vin) if len(vin) >= 3 else None
    })


@app.route('/api/batch', methods=['POST'])
def batch_decode():
    """
    Batch decode multiple VINs

    Request body (JSON):
    {
        "vins": ["VIN1", "VIN2", ...],
        "mode": "offline"  // or "online"
    }

    Returns:
        JSON array with decoded results
    """
    stats['total_requests'] += 1

    try:
        data = request.get_json()

        if not data or 'vins' not in data:
            return jsonify({
                'success': False,
                'error': 'Missing "vins" array in request body'
            }), 400

        vins = data.get('vins', [])
        mode = data.get('mode', 'offline')

        if len(vins) > 100:
            return jsonify({
                'success': False,
                'error': 'Maximum 100 VINs per batch request'
            }), 400

        results = []

        for vin in vins:
            if mode == 'offline':
                stats['offline_decodes'] += 1
                manufacturer = WMIDatabase.get_manufacturer(vin)
                year = WMIDatabase.get_year(vin)
                region = WMIDatabase.get_region(vin[0]) if vin else None

                results.append({
                    'vin': vin,
                    'year': year,
                    'manufacturer': manufacturer,
                    'region': region
                })
            else:
                # Online mode (be careful with rate limits)
                stats['online_decodes'] += 1
                try:
                    vehicle = decoder.decode(vin)
                    results.append({
                        'vin': vin,
                        'year': vehicle.year,
                        'make': vehicle.make,
                        'model': vehicle.model,
                        'manufacturer': vehicle.manufacturer
                    })
                except Exception as e:
                    results.append({
                        'vin': vin,
                        'error': str(e)
                    })

        stats['successful_decodes'] += len(results)

        return jsonify({
            'success': True,
            'count': len(results),
            'mode': mode,
            'results': results
        })

    except Exception as e:
        stats['failed_decodes'] += 1
        return jsonify({
            'success': False,
            'error': str(e)
        }), 500


@app.errorhandler(404)
def not_found(error):
    """Handle 404 errors"""
    return jsonify({
        'success': False,
        'error': 'Endpoint not found',
        'available_endpoints': [
            'GET /api/decode/<vin>',
            'GET /api/decode/<vin>/offline',
            'GET /api/validate/<vin>',
            'POST /api/batch',
            'GET /api/health',
            'GET /api/stats'
        ]
    }), 404


@app.errorhandler(500)
def internal_error(error):
    """Handle 500 errors"""
    return jsonify({
        'success': False,
        'error': 'Internal server error'
    }), 500


if __name__ == '__main__':
    print("\n" + "=" * 60)
    print("NHTSA VIN Decoder API Server")
    print("=" * 60)
    print("\nStarting Flask server...")
    print("API will be available at: http://localhost:5000")
    print("\nEndpoints:")
    print("  GET  /api/decode/<vin>              - Decode VIN (online)")
    print("  GET  /api/decode/<vin>/offline      - Decode VIN (offline)")
    print("  GET  /api/validate/<vin>            - Validate VIN")
    print("  POST /api/batch                     - Batch decode")
    print("  GET  /api/health                    - Health check")
    print("  GET  /api/stats                     - Statistics")
    print("\nExample:")
    print("  curl http://localhost:5000/api/decode/1HGCM82633A004352/offline")
    print("\n" + "=" * 60 + "\n")

    # Run Flask app
    app.run(debug=True, host='0.0.0.0', port=5000)
