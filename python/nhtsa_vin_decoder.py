#!/usr/bin/env python3
"""
NHTSA VIN Decoder - Official Government API with WMI Fallback
Free, comprehensive vehicle data from National Highway Traffic Safety Administration
Includes offline WMI database for basic manufacturer identification

Author: Wal33D
Email: aquataze@yahoo.com
"""

import urllib.request
import urllib.error
import json
from typing import Dict, Optional, Callable, List
from dataclasses import dataclass
from functools import lru_cache

# Import WMI database for offline fallback
try:
    from wmi_database import WMIDatabase
    wmi_available = True
except ImportError:
    wmi_available = False


@dataclass
class VehicleData:
    """Complete vehicle information from NHTSA"""
    vin: str
    make: Optional[str] = None
    manufacturer: Optional[str] = None
    model: Optional[str] = None
    year: Optional[int] = None
    trim: Optional[str] = None
    vehicle_type: Optional[str] = None
    body_class: Optional[str] = None
    doors: Optional[int] = None
    drive_type: Optional[str] = None
    engine_cylinders: Optional[int] = None
    engine_displacement_l: Optional[float] = None
    engine_model: Optional[str] = None
    fuel_type: Optional[str] = None
    transmission_style: Optional[str] = None
    transmission_speeds: Optional[str] = None
    gvwr: Optional[str] = None
    plant_city: Optional[str] = None
    plant_state: Optional[str] = None
    plant_country: Optional[str] = None
    error_text: Optional[str] = None
    raw_data: Dict = None

    def is_valid(self) -> bool:
        """Check if vehicle data is valid"""
        return self.make is not None and self.model is not None

    def get_display_name(self) -> str:
        """Get formatted display name"""
        parts = []
        if self.year:
            parts.append(str(self.year))
        if self.make:
            parts.append(self.make)
        if self.model:
            parts.append(self.model)
        if self.trim:
            parts.append(self.trim)
        return ' '.join(parts) if parts else self.vin


class NHTSAVinDecoder:
    """
    NHTSA VIN Decoder using official vPIC API

    Features:
    - Free government API (no key required)
    - Comprehensive vehicle information
    - Supports partial VINs
    - Built-in caching
    - Synchronous and asynchronous modes
    """

    BASE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles"

    def __init__(self):
        """Initialize decoder"""
        # Using @lru_cache on decode for caching
        pass

    @lru_cache(maxsize=128)
    def decode(self, vin: str, model_year: Optional[str] = None) -> VehicleData:
        """
        Decode a VIN using NHTSA API

        Args:
            vin: Vehicle Identification Number (17 characters for full VIN)
            model_year: Optional model year to help with ambiguous decoding

        Returns:
            VehicleData object with decoded information
        """
        if not vin or not vin.strip():
            return VehicleData(vin="", error_text="VIN cannot be empty")

        vin = vin.strip().upper()

        # Build URL
        if model_year:
            url = f"{self.BASE_URL}/DecodeVinValuesExtended/{vin}?format=json&modelyear={model_year}"
        else:
            url = f"{self.BASE_URL}/DecodeVinValues/{vin}?format=json"

        try:
            # Make API request
            with urllib.request.urlopen(url, timeout=10) as response:
                data = json.loads(response.read().decode('utf-8'))

            if 'Results' in data and len(data['Results']) > 0:
                result = data['Results'][0]

                # Parse response into VehicleData
                vehicle = VehicleData(
                    vin=vin,
                    make=self._clean_value(result.get('Make')),
                    manufacturer=self._clean_value(result.get('Manufacturer')),
                    model=self._clean_value(result.get('Model')),
                    year=self._parse_int(result.get('ModelYear')),
                    trim=self._clean_value(result.get('Trim')),
                    vehicle_type=self._clean_value(result.get('VehicleType')),
                    body_class=self._clean_value(result.get('BodyClass')),
                    doors=self._parse_int(result.get('Doors')),
                    drive_type=self._clean_value(result.get('DriveType')),
                    engine_cylinders=self._parse_int(result.get('EngineCylinders')),
                    engine_displacement_l=self._parse_float(result.get('DisplacementL')),
                    engine_model=self._clean_value(result.get('EngineModel')),
                    fuel_type=self._clean_value(result.get('FuelTypePrimary')),
                    transmission_style=self._clean_value(result.get('TransmissionStyle')),
                    transmission_speeds=self._clean_value(result.get('TransmissionSpeeds')),
                    gvwr=self._clean_value(result.get('GVWR')),
                    plant_city=self._clean_value(result.get('PlantCity')),
                    plant_state=self._clean_value(result.get('PlantState')),
                    plant_country=self._clean_value(result.get('PlantCountry')),
                    error_text=self._clean_value(result.get('ErrorText')),
                    raw_data=result
                )

                # Check for API errors
                error_code = result.get('ErrorCode')
                if error_code and error_code != '0':
                    vehicle.error_text = f"NHTSA Error {error_code}: {vehicle.error_text or 'Unknown error'}"

                return vehicle
            else:
                return VehicleData(vin=vin, error_text="No data returned from NHTSA")

        except (urllib.error.URLError, json.JSONDecodeError, Exception) as e:
            # API failed, try WMI fallback if available
            if wmi_available:
                return self._wmi_fallback(vin, str(e))

            # No fallback available, return error
            if isinstance(e, urllib.error.URLError):
                if hasattr(e, 'reason') and 'timed out' in str(e.reason):
                    return VehicleData(vin=vin, error_text="Request timed out")
                return VehicleData(vin=vin, error_text=f"Network error: {str(e)}")
            elif isinstance(e, json.JSONDecodeError):
                return VehicleData(vin=vin, error_text="Invalid response from NHTSA")
            else:
                return VehicleData(vin=vin, error_text=f"Unexpected error: {str(e)}")

    def _wmi_fallback(self, vin: str, api_error: str) -> VehicleData:
        """
        Use WMI database for basic manufacturer identification when API fails

        Args:
            vin: Vehicle Identification Number
            api_error: Original API error message

        Returns:
            VehicleData with basic WMI information
        """
        if not wmi_available or len(vin) < 3:
            return VehicleData(vin=vin, error_text=f"API failed: {api_error}")

        # Get basic info from WMI database
        manufacturer = WMIDatabase.get_manufacturer(vin)
        country = WMIDatabase.get_country(vin)
        year = WMIDatabase.get_year(vin) if len(vin) >= 10 else None

        # Return basic vehicle data with WMI fallback
        return VehicleData(
            vin=vin,
            manufacturer=manufacturer,
            make=manufacturer,
            year=year,
            plant_country=country,
            error_text=f"Using WMI fallback (API: {api_error})",
            raw_data={'source': 'WMI_FALLBACK', 'wmi': vin[:3], 'api_error': api_error}
        )

    def decode_offline(self, vin: str) -> VehicleData:
        """
        Decode VIN using only offline WMI database (no API call)

        Args:
            vin: Vehicle Identification Number

        Returns:
            VehicleData with basic WMI information only
        """
        if not wmi_available:
            return VehicleData(vin=vin, error_text="WMI database not available")

        if not vin or len(vin) < 3:
            return VehicleData(vin=vin or "", error_text="VIN too short for WMI lookup")

        vin = vin.strip().upper()

        # Get basic info from WMI database
        manufacturer = WMIDatabase.get_manufacturer(vin)
        country = WMIDatabase.get_country(vin)
        year = WMIDatabase.get_year(vin) if len(vin) >= 10 else None

        return VehicleData(
            vin=vin,
            manufacturer=manufacturer,
            make=manufacturer,
            year=year,
            plant_country=country,
            raw_data={'source': 'WMI_DATABASE', 'wmi': vin[:3] if len(vin) >= 3 else None}
        )

    def decode_async(self, vin: str, callback: Callable[[VehicleData], None],
                     model_year: Optional[str] = None) -> None:
        """
        Decode a VIN asynchronously (requires threading)

        Args:
            vin: Vehicle Identification Number
            callback: Function to call with results
            model_year: Optional model year
        """
        import threading

        def _decode_thread():
            result = self.decode(vin, model_year)
            callback(result)

        thread = threading.Thread(target=_decode_thread)
        thread.daemon = True
        thread.start()

    def decode_batch(self, vins: List[str]) -> Dict[str, VehicleData]:
        """Decode multiple VINs and return a mapping of VIN to VehicleData.

        Args:
            vins: List of VIN strings

        Returns:
            Dict mapping VIN -> VehicleData
        """
        results: Dict[str, VehicleData] = {}
        if not vins:
            return results
        for v in vins:
            try:
                results[v] = self.decode(v)
            except Exception as e:
                results[v] = VehicleData(vin=v, error_text=f"Decode error: {e}")
        return results

    def get_makes_for_manufacturer(self, manufacturer: str) -> list:
        """
        Get all makes for a manufacturer

        Args:
            manufacturer: Manufacturer name (e.g., "honda")

        Returns:
            List of make names
        """
        url = f"{self.BASE_URL}/GetMakesForManufacturerName/{manufacturer}?format=json"

        try:
            with urllib.request.urlopen(url, timeout=10) as response:
                data = json.loads(response.read().decode('utf-8'))

            if 'Results' in data:
                return [item['Make_Name'] for item in data['Results'] if item.get('Make_Name')]

        except Exception:
            pass

        return []

    def get_manufacturer_details(self, manufacturer: str) -> Dict:
        """
        Get manufacturer details

        Args:
            manufacturer: Manufacturer name or ID

        Returns:
            Dictionary with manufacturer details
        """
        url = f"{self.BASE_URL}/GetManufacturerDetails/{manufacturer}?format=json"

        try:
            with urllib.request.urlopen(url, timeout=10) as response:
                data = json.loads(response.read().decode('utf-8'))

            if 'Results' in data and len(data['Results']) > 0:
                return data['Results'][0]

        except Exception:
            pass

        return {}

    def clear_cache(self):
        """Clear the VIN cache"""
        self.decode.cache_clear()

    @staticmethod
    def _clean_value(value: any) -> Optional[str]:
        """Clean API response values"""
        if value is None:
            return None
        value = str(value).strip()
        if value.lower() in ['', 'null', 'none', 'not applicable']:
            return None
        return value

    @staticmethod
    def _parse_int(value: any) -> Optional[int]:
        """Parse integer from API response"""
        if value is None:
            return None
        try:
            cleaned = NHTSAVinDecoder._clean_value(value)
            if cleaned:
                return int(cleaned)
        except (ValueError, TypeError):
            pass
        return None

    @staticmethod
    def _parse_float(value: any) -> Optional[float]:
        """Parse float from API response"""
        if value is None:
            return None
        try:
            cleaned = NHTSAVinDecoder._clean_value(value)
            if cleaned:
                return float(cleaned)
        except (ValueError, TypeError):
            pass
        return None


def main():
    """Example usage"""
    decoder = NHTSAVinDecoder()

    # Test VINs
    test_vins = [
        "1HGCM82633A004352",  # Honda Accord
        "4JGDA5HB7JB158144",  # Mercedes GLE
        "WBA3B5C50EJ841989",  # BMW 3 Series
    ]

    for vin in test_vins:
        print(f"\nDecoding VIN: {vin}")
        vehicle = decoder.decode(vin)

        if vehicle.is_valid():
            print(f"  Vehicle: {vehicle.get_display_name()}")
            print(f"  Manufacturer: {vehicle.manufacturer}")
            print(f"  Engine: {vehicle.engine_cylinders} cylinder, {vehicle.engine_displacement_l}L")
            print(f"  Fuel: {vehicle.fuel_type}")
            print(f"  Body: {vehicle.body_class}")
            print(f"  Drive: {vehicle.drive_type}")
        else:
            print(f"  Error: {vehicle.error_text}")

    # Test manufacturer lookup
    print("\nHonda makes:")
    makes = decoder.get_makes_for_manufacturer("honda")
    for make in makes[:5]:
        print(f"  - {make}")


if __name__ == "__main__":
    main()
