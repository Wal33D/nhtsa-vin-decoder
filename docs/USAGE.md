# Usage Guide

## Getting Started

### Basic VIN Decoding

```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

# Create decoder instance
decoder = NHTSAVinDecoder()

# Decode a VIN
result = decoder.decode('1HGCM82633A004352')

# Access vehicle information
print(f"Vehicle: {result.year} {result.make} {result.model}")
print(f"Manufacturer: {result.manufacturer}")
print(f"Body Type: {result.body_class}")
print(f"Country: {result.plant_country}")
```

### Offline VIN Decoding

When internet connectivity is unavailable or for faster basic lookups:

```python
# Use offline WMI database only
result = decoder.decode_offline('WBA5B3C50GG252337')

print(f"Manufacturer: {result.manufacturer}")  # BMW
print(f"Country: {result.plant_country}")      # Germany
print(f"Year: {result.year}")                  # 2016
```

## Advanced Usage

### Batch Processing

Process multiple VINs efficiently:

```python
# List of VINs to decode
vins = [
    '1HGCM82633A004352',  # Honda Accord
    'WBA5B3C50GG252337',  # BMW 5 Series
    '5YJ3E1EA5KF000316',  # Tesla Model 3
    '1FTFW1ET5DFC10312'   # Ford F-150
]

# Decode in batch
results = decoder.decode_batch(vins)

# Process results
for vin, data in results.items():
    if data.error_code:
        print(f"{vin}: Error - {data.error_text}")
    else:
        print(f"{vin}: {data.year} {data.make} {data.model}")
```

### Asynchronous Decoding

For non-blocking operations:

```python
import time

def handle_result(vehicle_data):
    """Callback function for async decode"""
    if vehicle_data.error_code:
        print(f"Error: {vehicle_data.error_text}")
    else:
        print(f"Decoded: {vehicle_data.year} {vehicle_data.make} {vehicle_data.model}")

# Start async decode
decoder.decode_async('1HGCM82633A004352', handle_result)

# Continue with other work
print("Decoding in progress...")
time.sleep(2)  # Wait for completion
```

### Error Handling

Proper error handling for production use:

```python
def safe_decode(vin):
    """Safely decode a VIN with comprehensive error handling"""
    try:
        result = decoder.decode(vin)

        if result.error_code:
            # Handle NHTSA API errors
            if 'WMI fallback' in result.error_text:
                print(f"Using offline data for {vin}")
                return {
                    'status': 'partial',
                    'manufacturer': result.manufacturer,
                    'year': result.year,
                    'country': result.plant_country
                }
            else:
                print(f"Decode failed: {result.error_text}")
                return {'status': 'error', 'message': result.error_text}
        else:
            # Success - full data available
            return {
                'status': 'success',
                'vehicle': f"{result.year} {result.make} {result.model}",
                'details': result.to_dict()
            }

    except Exception as e:
        print(f"Unexpected error: {e}")
        return {'status': 'error', 'message': str(e)}

# Use the safe decoder
result = safe_decode('1HGCM82633A004352')
print(result['status'])
```

## Common Use Cases

### 1. Vehicle Registration System

```python
def register_vehicle(vin, owner_name):
    """Register a vehicle with decoded information"""
    # Decode VIN
    vehicle = decoder.decode(vin)

    if vehicle.error_code:
        # Try offline decode as fallback
        vehicle = decoder.decode_offline(vin)
        if not vehicle.manufacturer:
            return False, "Invalid VIN"

    # Create registration record
    registration = {
        'vin': vin,
        'owner': owner_name,
        'make': vehicle.make,
        'model': vehicle.model,
        'year': vehicle.year,
        'manufacturer': vehicle.manufacturer,
        'registered_date': datetime.now()
    }

    # Save to database (example)
    # db.save_registration(registration)

    return True, registration
```

### 2. Insurance Quote System

```python
def get_insurance_quote(vin):
    """Generate insurance quote based on vehicle details"""
    vehicle = decoder.decode(vin)

    # Base rate calculation
    base_rate = 500

    # Adjust based on vehicle type
    if vehicle.vehicle_type == 'PASSENGER CAR':
        base_rate *= 1.0
    elif vehicle.vehicle_type == 'TRUCK':
        base_rate *= 1.2
    elif vehicle.vehicle_type == 'MOTORCYCLE':
        base_rate *= 0.8

    # Adjust based on year (newer = higher)
    if vehicle.year:
        age = datetime.now().year - vehicle.year
        if age < 3:
            base_rate *= 1.3
        elif age < 7:
            base_rate *= 1.1
        elif age > 15:
            base_rate *= 0.9

    return {
        'vehicle': f"{vehicle.year} {vehicle.make} {vehicle.model}",
        'annual_premium': round(base_rate, 2),
        'monthly_payment': round(base_rate / 12, 2)
    }
```

### 3. Fleet Management

```python
def analyze_fleet(vin_list):
    """Analyze a fleet of vehicles"""
    fleet_data = decoder.decode_batch(vin_list)

    # Fleet statistics
    stats = {
        'total_vehicles': len(vin_list),
        'manufacturers': {},
        'years': {},
        'countries': {},
        'errors': []
    }

    for vin, vehicle in fleet_data.items():
        if vehicle.error_code:
            stats['errors'].append(vin)
            continue

        # Count by manufacturer
        mfg = vehicle.manufacturer or 'Unknown'
        stats['manufacturers'][mfg] = stats['manufacturers'].get(mfg, 0) + 1

        # Count by year
        year = str(vehicle.year) if vehicle.year else 'Unknown'
        stats['years'][year] = stats['years'].get(year, 0) + 1

        # Count by country
        country = vehicle.plant_country or 'Unknown'
        stats['countries'][country] = stats['countries'].get(country, 0) + 1

    return stats
```

### 4. VIN Validation

```python
def validate_vin(vin):
    """Validate a VIN and return detailed information"""
    # Basic format check
    if not vin or len(vin) != 17:
        return {'valid': False, 'reason': 'VIN must be 17 characters'}

    # Check for invalid characters
    if 'I' in vin or 'O' in vin or 'Q' in vin:
        return {'valid': False, 'reason': 'VIN contains invalid characters (I, O, or Q)'}

    # Try to decode
    result = decoder.decode(vin)

    if result.error_code and result.error_code != '0':
        # Try offline validation
        offline = decoder.decode_offline(vin)
        if offline.manufacturer and offline.manufacturer != 'Unknown':
            return {
                'valid': True,
                'confidence': 'medium',
                'manufacturer': offline.manufacturer,
                'year': offline.year
            }
        else:
            return {'valid': False, 'reason': result.error_text}

    return {
        'valid': True,
        'confidence': 'high',
        'vehicle': f"{result.year} {result.make} {result.model}",
        'details': result.to_dict()
    }
```

## Performance Optimization

### Caching Results

```python
from functools import lru_cache

class CachedDecoder(NHTSAVinDecoder):
    @lru_cache(maxsize=1000)
    def decode_cached(self, vin, model_year=None):
        """Cached version of decode method"""
        return self.decode(vin, model_year)

# Use cached decoder
cached_decoder = CachedDecoder()
result1 = cached_decoder.decode_cached('1HGCM82633A004352')  # API call
result2 = cached_decoder.decode_cached('1HGCM82633A004352')  # From cache
```

### Rate Limiting

```python
import time
from collections import deque

class RateLimitedDecoder(NHTSAVinDecoder):
    def __init__(self, max_per_minute=60):
        super().__init__()
        self.max_per_minute = max_per_minute
        self.call_times = deque()

    def _check_rate_limit(self):
        """Ensure we don't exceed rate limit"""
        now = time.time()
        # Remove calls older than 1 minute
        while self.call_times and self.call_times[0] < now - 60:
            self.call_times.popleft()

        if len(self.call_times) >= self.max_per_minute:
            # Wait until oldest call expires
            sleep_time = 60 - (now - self.call_times[0])
            if sleep_time > 0:
                time.sleep(sleep_time)

        self.call_times.append(now)

    def decode(self, vin, model_year=None):
        """Rate-limited decode"""
        self._check_rate_limit()
        return super().decode(vin, model_year)
```

## Integration Examples

### Django Integration

```python
# models.py
from django.db import models

class Vehicle(models.Model):
    vin = models.CharField(max_length=17, unique=True)
    make = models.CharField(max_length=50)
    model = models.CharField(max_length=50)
    year = models.IntegerField()
    manufacturer = models.CharField(max_length=100)
    decoded_data = models.JSONField()
    created_at = models.DateTimeField(auto_now_add=True)

# views.py
from django.http import JsonResponse
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()

def decode_vin(request):
    vin = request.GET.get('vin')
    if not vin:
        return JsonResponse({'error': 'VIN required'}, status=400)

    # Check if already in database
    try:
        vehicle = Vehicle.objects.get(vin=vin)
        return JsonResponse(vehicle.decoded_data)
    except Vehicle.DoesNotExist:
        pass

    # Decode and save
    result = decoder.decode(vin)
    if not result.error_code:
        vehicle = Vehicle.objects.create(
            vin=vin,
            make=result.make,
            model=result.model,
            year=result.year,
            manufacturer=result.manufacturer,
            decoded_data=result.to_dict()
        )

    return JsonResponse(result.to_dict())
```

### Flask Integration

```python
from flask import Flask, jsonify, request
from python.nhtsa_vin_decoder import NHTSAVinDecoder

app = Flask(__name__)
decoder = NHTSAVinDecoder()

@app.route('/api/decode/<vin>')
def decode_vin(vin):
    """API endpoint for VIN decoding"""
    try:
        result = decoder.decode(vin)
        return jsonify(result.to_dict())
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@app.route('/api/validate/<vin>')
def validate_vin(vin):
    """API endpoint for VIN validation"""
    if len(vin) != 17:
        return jsonify({'valid': False, 'reason': 'Invalid length'}), 400

    result = decoder.decode_offline(vin)
    return jsonify({
        'valid': result.manufacturer is not None,
        'manufacturer': result.manufacturer,
        'year': result.year
    })

if __name__ == '__main__':
    app.run(debug=True)
```