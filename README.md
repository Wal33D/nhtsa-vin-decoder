# NHTSA VIN Decoder

Official NHTSA vPIC API wrapper with offline WMI database fallback for decoding Vehicle Identification Numbers

**Author**: Wal33D
**Email**: aquataze@yahoo.com

## Overview

Comprehensive VIN decoder using the official US government NHTSA (National Highway Traffic Safety Administration) vPIC API with integrated offline WMI (World Manufacturer Identifier) database for fallback support. Returns complete vehicle specifications, not just manufacturer mappings.

## Features

- **Official Government API** - NHTSA vPIC database
- **Offline WMI Database** - 2000+ manufacturer codes for basic decoding without internet
- **Automatic Fallback** - Seamlessly switches to offline mode when API is unavailable
- **FREE** - No API key required
- **Comprehensive Data** - Make, model, year, engine, transmission, safety ratings
- **Always Current** - Database updated by NHTSA
- **Caching** - Built-in LRU cache to reduce API calls
- **Multi-platform** - Java/Android and Python implementations
- **Zero Dependencies** - Python version uses standard library only

## Directory Structure

```
nhtsa-vin-decoder/
├── java/                 # Java/Android implementation
│   ├── VINDecoderService.java
│   ├── VehicleData.java
│   ├── NHTSAApiService.java
│   └── Response classes
├── python/               # Python implementation
│   ├── nhtsa_vin_decoder.py    # Main decoder with API integration
│   └── wmi_database.py         # Offline WMI database (2000+ codes)
└── docs/                 # Documentation
    ├── API.md            # Complete API reference
    ├── WMI_DATABASE.md   # WMI database details
    ├── USAGE.md          # Examples and best practices
    └── INSTALLATION.md   # Setup guide
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

### Python - Online Mode (Full NHTSA Data)
```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")

print(f"Vehicle: {vehicle.year} {vehicle.make} {vehicle.model}")
# Output: Vehicle: 2003 HONDA Accord

print(f"Body: {vehicle.body_class}, {vehicle.doors} doors")
# Output: Body: Coupe, 2 doors
```

### Python - Offline Mode (WMI Database)
```python
# Works without internet connection
vehicle = decoder.decode_offline("WBA5B3C50GG252337")

print(f"Manufacturer: {vehicle.manufacturer}")
# Output: Manufacturer: BMW

print(f"Country: {vehicle.plant_country}, Year: {vehicle.year}")
# Output: Country: Germany, Year: 2016
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

| Feature | NHTSA API | WMI Database (This Library) | Static WMI Only |
|---------|-----------|---------------------------|----------------|
| Manufacturer | ✓ | ✓ | ✓ |
| Make/Model | ✓ | ✓ (Make only) | ✗ |
| Year | ✓ | ✓ (2001-2030) | Partial |
| Country | ✓ | ✓ | ✓ |
| Engine Details | ✓ | ✗ | ✗ |
| Transmission | ✓ | ✗ | ✗ |
| Body Style | ✓ | ✗ | ✗ |
| Safety Data | ✓ | ✗ | ✗ |
| Works Offline | ✗ | ✓ | ✓ |
| Auto Fallback | N/A | ✓ | ✗ |
| Free | ✓ | ✓ | ✓ |

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

## Documentation

Comprehensive documentation is available in the [docs/](docs/) directory:

- [Installation Guide](docs/INSTALLATION.md) - Setup and configuration
- [API Reference](docs/API.md) - Complete API documentation
- [Usage Examples](docs/USAGE.md) - Code examples and best practices
- [WMI Database](docs/WMI_DATABASE.md) - Offline database details

## Supported Manufacturers (Offline WMI)

The offline WMI database includes 2000+ manufacturer codes:

- **American**: Ford, GM, Chrysler, Tesla, Rivian, Lucid
- **European**: BMW, Mercedes-Benz, Volkswagen, Audi, Porsche, Ferrari, Lamborghini
- **Japanese**: Toyota, Honda, Nissan, Mazda, Subaru, Mitsubishi
- **Korean**: Hyundai, Kia, Genesis
- **And many more...**

## Limitations

- **API Mode** - Requires internet connection
- **Rate Limits** - Be respectful, use caching
- **Offline Mode** - Basic info only (manufacturer, year, country)

## Contributing

Found an issue? Create a GitHub issue or PR.

## License

MIT License - Free for commercial and non-commercial use

## Contact

Wal33D - aquataze@yahoo.com

## Credits

- NHTSA for providing the free vPIC API
- US Department of Transportation