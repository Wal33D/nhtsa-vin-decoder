# NHTSA VIN Decoder

Official NHTSA vPIC API wrapper for decoding Vehicle Identification Numbers

Author: Wal33D
Email: aquataze@yahoo.com

## What is this?

This is the **real** VIN decoder - uses the official US government NHTSA (National Highway Traffic Safety Administration) API to get comprehensive vehicle data. Not just manufacturer mappings, but EVERYTHING about the vehicle.

## Features

- **Official Government API** - NHTSA vPIC database
- **FREE** - No API key required
- **Comprehensive Data** - Make, model, year, engine, transmission, safety ratings
- **Always Current** - Database updated by NHTSA
- **Caching** - Reduces API calls
- **Multi-platform** - Java (Android), Python, JavaScript

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
```bash
pip install nhtsa-vin-decoder
# Or from source
cd python && pip install -r requirements.txt
```

### Java/Android
Add to your `build.gradle`:
```gradle
dependencies {
    implementation 'com.wal33d:nhtsa-vin-decoder:1.0.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
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

## Integration with DTC Database

Perfect companion for our DTC database:

```python
# Step 1: Get manufacturer from NHTSA
from nhtsa_vin_decoder import NHTSAVinDecoder
decoder = NHTSAVinDecoder()
vehicle = decoder.decode(vin)
manufacturer = vehicle.manufacturer  # e.g., "Mercedes-Benz AG"

# Step 2: Use with DTC database
from dtc_database import DTCDatabase
dtc_db = DTCDatabase()
dtc = dtc_db.get_smart("P0171", manufacturer=manufacturer)
```

## Limitations

- **Internet Required** - API calls need network
- **Rate Limits** - Be respectful, use caching
- **US-Focused** - Best for North American vehicles

## Contributing

Found an issue? Create a GitHub issue or PR.

## License

MIT - Use freely

## Related Projects

- [dtc-database](https://github.com/Wal33D/dtc-database) - 18,821 DTC codes
- [OBD-Droid](https://github.com/Wal33D/OBD-Droid) - Android OBD app

## Credits

- NHTSA for providing the free vPIC API
- US Department of Transportation