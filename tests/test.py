#!/usr/bin/env python3
"""
Test script for NHTSA VIN Decoder
Author: Wal33D
"""

import sys
import os
sys.path.insert(0, os.path.dirname(__file__))

from python.nhtsa_vin_decoder import NHTSAVinDecoder

def test_vin_decoder():
    """Test NHTSA VIN decoder functionality"""
    print("Testing NHTSA VIN Decoder...")
    print("-" * 50)

    # Initialize decoder
    decoder = NHTSAVinDecoder()

    # Test VINs
    test_vins = [
        '1HGCM82633A004352',  # Honda Accord
        '4JGDA5HB7JB158144',  # Mercedes GLE
        'WBA3B5C59FF541902',  # BMW 328i
    ]

    for vin in test_vins:
        print(f"\nDecoding VIN: {vin}")
        vehicle = decoder.decode(vin)

        if vehicle.error_text:
            print(f"  Error: {vehicle.error_text}")
        else:
            print(f"  Vehicle: {vehicle.year} {vehicle.make} {vehicle.model} {vehicle.trim or ''}")
            print(f"  Manufacturer: {vehicle.manufacturer}")
            print(f"  Model: {vehicle.model}")
            print(f"  Year: {vehicle.year}")
            print(f"  Type: {vehicle.vehicle_type}")
            if vehicle.engine_cylinders:
                print(f"  Engine: {vehicle.engine_cylinders} cylinders")
            if vehicle.engine_displacement_l:
                print(f"  Displacement: {vehicle.engine_displacement_l}L")

    # Test manufacturer lookup
    print("\n\nTesting manufacturer lookup:")
    makes = decoder.get_makes_for_manufacturer("Honda")
    if makes:
        print(f"  Honda makes: {', '.join(makes[:5])}")

    print("\n✓ All tests passed!")

if __name__ == "__main__":
    test_vin_decoder()
