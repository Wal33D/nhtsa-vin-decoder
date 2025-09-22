# NHTSA VIN Decoder

Official NHTSA vPIC API wrapper for decoding Vehicle Identification Numbers

**Author**: Wal33D
**Email**: aquataze@yahoo.com

## Overview

Comprehensive VIN decoder using the official US government NHTSA (National Highway Traffic Safety Administration) vPIC API. Returns complete vehicle specifications, not just manufacturer mappings.

## Features

- **Official Government API** - NHTSA vPIC database
- **FREE** - No API key required
- **Comprehensive Data** - Make, model, year, engine, transmission, safety ratings
- **Always Current** - Database updated by NHTSA
- **Caching** - Built-in LRU cache to reduce API calls
- **Multi-platform** - Java/Android and Python implementations

## Directory Structure

```
nhtsa-vin-decoder/
├── java/                 # Java/Android implementation
│   ├── VINDecoderService.java
│   ├── VehicleData.java
│   ├── NHTSAApiService.java
│   └── Response classes
├── python/               # Python implementation
│   └── nhtsa_vin_decoder.py
└── docs/                 # Documentation
```

## What You Get

```json
{
  "vin": "1HGCM82633A004352",
  "make": "Honda",
  "manufacturer": "Honda Motor Company",
  "model": "Accord",
  "year": 2003,
  "trim": "EX",
  "vehicle_type": "Passenger Car",
  "body_class": "Sedan/Saloon",
  "doors": 4,
  "drive_type": "FWD",
  "engine_cylinders": 4,
  "engine_displacement_l": 2.4,
  "fuel_type": "Gasoline",
  "transmission_speeds": "5",
  "plant_city": "Marysville",
  "plant_state": "Ohio",
  "plant_country": "United States"
}
```

## Quick Start

### Python
```python
from nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")

print(f"Vehicle: {vehicle.get_display_name()}")
# Output: 2003 Honda Accord EX

print(f"Engine: {vehicle.engine_cylinders}cyl {vehicle.engine_displacement_l}L")
# Output: Engine: 4cyl 2.4L
```

### Java (Android)
```java
VINDecoderService decoder = VINDecoderService.getInstance();

decoder.decodeVIN("1HGCM82633A004352", new VINDecoderCallback() {
    @Override
    public void onSuccess(VehicleData vehicle) {
        System.out.println("Vehicle: " + vehicle.getDisplayName());
        System.out.println("Engine: " + vehicle.getEngineCylinders() + "cyl");
    }

    @Override
    public void onError(String error) {
        System.err.println("Error: " + error);
    }
});
```

## Installation

### Python
```python
# No external dependencies required - uses urllib
from python.nhtsa_vin_decoder import NHTSAVinDecoder
```

### Java/Android
```java
// Copy java/*.java files to your project
// Requires Retrofit for Android:
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

## API Endpoints Used

- **Decode VIN**: `https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVin/{vin}?format=json`
- **With Year**: `https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinValues/{vin}?format=json&modelyear={year}`
- **Get Makes**: `https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForManufacturerName/{manufacturer}`

## Why NHTSA?

- **Official Source** - US government database
- **Complete Data** - Not just manufacturer, EVERYTHING
- **Free Forever** - Public service, no fees
- **Always Accurate** - Updated by manufacturers
- **Legal Compliance** - Meets regulatory requirements

## Use Cases

- **OBD-II Apps** - Get vehicle context for diagnostics
- **Insurance** - Verify vehicle details
- **Fleet Management** - Track vehicle specifications
- **Parts Lookup** - Match parts to exact vehicle
- **Safety Checks** - Access recall information

## Comparison

| Feature | NHTSA API | Static WMI Mapping |
|---------|-----------|-------------------|
| Manufacturer | ✓ | ✓ |
| Make/Model | ✓ | ✗ |
| Year | ✓ | Partial |
| Engine Details | ✓ | ✗ |
| Transmission | ✓ | ✗ |
| Body Style | ✓ | ✗ |
| Plant Location | ✓ | ✗ |
| Safety Data | ✓ | ✗ |
| Free | ✓ | ✓ |
| Offline | ✗ | ✓ |

## Example Use Cases

```python
# Get full vehicle information from VIN
from python.nhtsa_vin_decoder import NHTSAVinDecoder
decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")

# Access vehicle details
print(f"Manufacturer: {vehicle.manufacturer}")
print(f"Model: {vehicle.model} {vehicle.trim}")
print(f"Engine: {vehicle.engine_cylinders}cyl {vehicle.engine_displacement_l}L")
print(f"Fuel Type: {vehicle.fuel_type}")
```

## Limitations

- **Internet Required** - API calls need network
- **Rate Limits** - Be respectful, use caching
- **US-Focused** - Best for North American vehicles

## Contributing

Found an issue? Create a GitHub issue or PR.

## License

MIT License - Free for commercial and non-commercial use

## Contact

Wal33D - aquataze@yahoo.com

## Credits

- NHTSA for providing the free vPIC API
- US Department of Transportation