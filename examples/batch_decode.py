#!/usr/bin/env python3
"""
Batch VIN Decoder Example

Demonstrates high-performance batch decoding of multiple VINs
from various sources (files, databases, API requests).

Use cases:
- Fleet management: Process entire vehicle inventory
- Dealerships: Decode multiple vehicles in showroom
- Insurance: Bulk policy data enrichment
- Data analysis: VIN dataset processing

Author: Wal33D
"""

import sys
import os
import time
from concurrent.futures import ThreadPoolExecutor, as_completed

# Add parent directory to path for imports
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from python.nhtsa_vin_decoder import NHTSAVinDecoder
from python.wmi_database import WMIDatabase


def batch_decode_offline(vins):
    """
    Decode multiple VINs using offline WMI database.
    Ultra-fast: <1ms per VIN

    Args:
        vins: List of VIN strings

    Returns:
        List of dictionaries with decoded data
    """
    print(f"\nBatch Offline Decode: {len(vins)} VINs")
    print("-" * 60)

    start_time = time.time()
    results = []

    for vin in vins:
        result = {
            'vin': vin,
            'manufacturer': WMIDatabase.get_manufacturer(vin),
            'year': WMIDatabase.get_year(vin),
            'region': WMIDatabase.get_region(vin[0])
        }
        results.append(result)

    elapsed = time.time() - start_time
    avg_time = (elapsed / len(vins)) * 1000  # Convert to ms

    print(f"Processed {len(vins)} VINs in {elapsed:.3f}s")
    print(f"Average time per VIN: {avg_time:.3f}ms")
    print(f"Throughput: {len(vins)/elapsed:.0f} VINs/second")

    return results


def batch_decode_online_sequential(vins):
    """
    Decode VINs sequentially using NHTSA API.
    Slower but gets complete data.

    Args:
        vins: List of VIN strings

    Returns:
        List of VehicleData objects
    """
    print(f"\nBatch Online Decode (Sequential): {len(vins)} VINs")
    print("-" * 60)

    decoder = NHTSAVinDecoder()
    start_time = time.time()
    results = []

    for i, vin in enumerate(vins, 1):
        print(f"  [{i}/{len(vins)}] Decoding {vin}...", end='\r')
        try:
            vehicle = decoder.decode(vin)
            results.append(vehicle)
            time.sleep(0.1)  # Be nice to NHTSA API
        except Exception as e:
            print(f"\n  Error decoding {vin}: {e}")
            results.append(None)

    elapsed = time.time() - start_time
    avg_time = (elapsed / len(vins))

    print(f"\nProcessed {len(vins)} VINs in {elapsed:.1f}s")
    print(f"Average time per VIN: {avg_time:.1f}s")

    return results


def batch_decode_online_parallel(vins, max_workers=5):
    """
    Decode VINs in parallel using ThreadPool.
    Faster than sequential, but be careful with API rate limits.

    Args:
        vins: List of VIN strings
        max_workers: Maximum concurrent API calls

    Returns:
        List of VehicleData objects
    """
    print(f"\nBatch Online Decode (Parallel): {len(vins)} VINs, {max_workers} workers")
    print("-" * 60)

    decoder = NHTSAVinDecoder()
    start_time = time.time()

    def decode_vin(vin):
        """Decode single VIN"""
        try:
            time.sleep(0.1)  # Rate limiting
            return decoder.decode(vin)
        except Exception as e:
            print(f"Error decoding {vin}: {e}")
            return None

    results = []
    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        future_to_vin = {executor.submit(decode_vin, vin): vin for vin in vins}

        for i, future in enumerate(as_completed(future_to_vin), 1):
            print(f"  [{i}/{len(vins)}] Completed", end='\r')
            results.append(future.result())

    elapsed = time.time() - start_time
    avg_time = (elapsed / len(vins))

    print(f"\nProcessed {len(vins)} VINs in {elapsed:.1f}s")
    print(f"Average time per VIN: {avg_time:.1f}s")
    print(f"Speedup: {len(vins)/max_workers/avg_time:.1f}x vs sequential")

    return results


def batch_decode_from_file(filename):
    """
    Decode VINs from a text file (one VIN per line).

    Args:
        filename: Path to file containing VINs

    Returns:
        List of decoded results
    """
    print(f"\nBatch Decode from File: {filename}")
    print("-" * 60)

    try:
        with open(filename, 'r') as f:
            vins = [line.strip() for line in f if line.strip()]

        print(f"Loaded {len(vins)} VINs from file")
        return batch_decode_offline(vins)

    except FileNotFoundError:
        print(f"File not found: {filename}")
        return []


def export_results_csv(results, filename):
    """
    Export decoded results to CSV file.

    Args:
        results: List of result dictionaries
        filename: Output CSV filename
    """
    import csv

    print(f"\nExporting results to {filename}...")

    with open(filename, 'w', newline='') as f:
        if not results:
            print("No results to export")
            return

        writer = csv.DictWriter(f, fieldnames=results[0].keys())
        writer.writeheader()
        writer.writerows(results)

    print(f"Exported {len(results)} records to {filename}")


def example_performance_comparison():
    """Compare offline vs online performance"""
    print("\n" + "=" * 60)
    print("PERFORMANCE COMPARISON")
    print("=" * 60)

    test_vins = [
        "1HGCM82633A004352",  # Honda
        "4JGDA5HB7JB158144",  # Mercedes
        "1FTFW1ET5DFC10312",  # Ford
        "5YJ3E1EA5KF000316",  # Tesla
        "WBA5B3C50GG252337",  # BMW
        "1G1ZD5ST0LF042812",  # Chevrolet
        "JTDKB20U297878234",  # Toyota
        "2HGFG12848H542071",  # Honda
        "1N4AL3AP9JC238972",  # Nissan
        "3VW2B7AJ6JM273849"   # Volkswagen
    ]

    # Offline decode (fast)
    offline_results = batch_decode_offline(test_vins)

    # Show sample results
    print("\nSample Offline Results:")
    for result in offline_results[:3]:
        print(f"  {result['vin']}: {result['year']} {result['manufacturer']}")

    # Note: Comment out online decode to avoid API rate limiting in demo
    # online_results = batch_decode_online_parallel(test_vins[:3], max_workers=2)


if __name__ == "__main__":
    print("\n" + "=" * 60)
    print("NHTSA VIN Decoder - Batch Processing Examples")
    print("=" * 60)

    # Run performance comparison
    example_performance_comparison()

    # Decode from file if test_vins.txt exists
    test_file = os.path.join(os.path.dirname(__file__), "test_vins.txt")
    if os.path.exists(test_file):
        print("\n" + "=" * 60)
        print("FILE BATCH DECODE")
        print("=" * 60)
        file_results = batch_decode_from_file(test_file)

        # Export to CSV
        if file_results:
            csv_output = os.path.join(os.path.dirname(__file__), "decoded_vins.csv")
            export_results_csv(file_results, csv_output)
    else:
        print(f"\nNote: Create {test_file} with VINs (one per line) to test file batch processing")

    print("\n" + "=" * 60)
    print("Batch Processing Examples Complete!")
    print("=" * 60 + "\n")

    print("\nKey Takeaways:")
    print("  - Offline mode: <1ms per VIN (instant)")
    print("  - Online mode: ~200-500ms per VIN (API latency)")
    print("  - Use offline for speed, online for complete data")
    print("  - Parallel processing can speed up online decoding")
