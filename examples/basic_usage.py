#!/usr/bin/env python3
"""
Basic VIN Decoder Usage Example

Demonstrates the simplest way to decode VINs using both online
and offline modes.

Author: Wal33D
"""

import sys
import os

# Add parent directory to path for imports
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from python.nhtsa_vin_decoder import NHTSAVinDecoder
from python.wmi_database import WMIDatabase


def example_online_decode():
    """Example: Online decoding with NHTSA API"""
    print("=" * 60)
    print("EXAMPLE 1: Online Decode (NHTSA API)")
    print("=" * 60)

    decoder = NHTSAVinDecoder()
    vin = "1HGCM82633A004352"

    print(f"Decoding VIN: {vin}")
    print("Fetching data from NHTSA API...")

    vehicle = decoder.decode(vin)

    print(f"\nResults:")
    print(f"  Year:         {vehicle.year}")
    print(f"  Make:         {vehicle.make}")
    print(f"  Model:        {vehicle.model}")
    print(f"  Manufacturer: {vehicle.manufacturer}")
    print(f"  Body Type:    {vehicle.body_class}")
    print(f"  Country:      {vehicle.plant_country}")
    print(f"  Engine:       {vehicle.engine_cylinders} cylinders")
    print(f"  Fuel Type:    {vehicle.fuel_type_primary}")


def example_offline_decode():
    """Example: Offline decoding (no internet required)"""
    print("\n" + "=" * 60)
    print("EXAMPLE 2: Offline Decode (WMI Database)")
    print("=" * 60)

    vin = "4JGDA5HB7JB158144"

    print(f"Decoding VIN: {vin}")
    print("Using offline WMI database...")

    manufacturer = WMIDatabase.get_manufacturer(vin)
    year = WMIDatabase.get_year(vin)
    region = WMIDatabase.get_region(vin[0])

    print(f"\nResults:")
    print(f"  Year:         {year}")
    print(f"  Manufacturer: {manufacturer}")
    print(f"  Region:       {region}")
    print(f"  WMI Code:     {vin[:3]}")


def example_validation():
    """Example: VIN validation"""
    print("\n" + "=" * 60)
    print("EXAMPLE 3: VIN Validation")
    print("=" * 60)

    from python.nhtsa_vin_decoder import NHTSAVinDecoder

    decoder = NHTSAVinDecoder()

    test_vins = {
        "1HGCM82633A004352": "Valid Honda Accord",
        "1HGCM82633A004353": "Invalid (wrong check digit)",
        "INVALID123456789": "Invalid (wrong format)",
        "1HGCM8263": "Invalid (too short)"
    }

    for vin, description in test_vins.items():
        try:
            result = decoder.decode_offline(vin)
            if result.error_code:
                status = "INVALID"
                reason = result.error_text
            else:
                status = "VALID"
                reason = f"{result.manufacturer}"
        except Exception as e:
            status = "INVALID"
            reason = str(e)

        print(f"  {vin[:17]:20} - {status:8} ({description})")


def example_multiple_vins():
    """Example: Decoding multiple VINs"""
    print("\n" + "=" * 60)
    print("EXAMPLE 4: Batch Decoding")
    print("=" * 60)

    vins = [
        "1HGCM82633A004352",  # Honda
        "4JGDA5HB7JB158144",  # Mercedes
        "1FTFW1ET5DFC10312",  # Ford
        "5YJ3E1EA5KF000316",  # Tesla
        "WBA5B3C50GG252337"   # BMW
    ]

    print(f"Decoding {len(vins)} VINs using offline mode...\n")

    for vin in vins:
        manufacturer = WMIDatabase.get_manufacturer(vin)
        year = WMIDatabase.get_year(vin)
        print(f"  {vin} -> {year} {manufacturer}")


if __name__ == "__main__":
    print("\n" + "=" * 60)
    print("NHTSA VIN Decoder - Basic Usage Examples")
    print("=" * 60 + "\n")

    # Run all examples
    try:
        example_online_decode()
    except Exception as e:
        print(f"\nOnline decode failed (API may be unavailable): {e}")
        print("Continuing with offline examples...")

    example_offline_decode()
    example_validation()
    example_multiple_vins()

    print("\n" + "=" * 60)
    print("All examples completed!")
    print("=" * 60 + "\n")
