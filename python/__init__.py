"""
NHTSA VIN Decoder
================

World-class VIN decoder with comprehensive offline database (2,015+ WMI codes)
and NHTSA vPIC API integration.

Usage:
    from python.nhtsa_vin_decoder import NHTSAVinDecoder
    from python.wmi_database import WMIDatabase

    # Online decoding with NHTSA API
    decoder = NHTSAVinDecoder()
    vehicle = decoder.decode("1HGCM82633A004352")
    print(f"{vehicle.year} {vehicle.make} {vehicle.model}")

    # Offline decoding (no internet required)
    manufacturer = WMIDatabase.get_manufacturer("1HGCM82633A004352")
    year = WMIDatabase.get_year("1HGCM82633A004352")
    print(f"{year} {manufacturer}")

Author: Wal33D <aquataze@yahoo.com>
License: MIT
"""

__version__ = "0.1.0"
__author__ = "Wal33D"
__email__ = "aquataze@yahoo.com"
__license__ = "MIT"

from .nhtsa_vin_decoder import NHTSAVinDecoder, VehicleData
from .wmi_database import WMIDatabase

__all__ = [
    "NHTSAVinDecoder",
    "VehicleData",
    "WMIDatabase",
]
