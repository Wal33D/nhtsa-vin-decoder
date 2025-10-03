# NHTSA VIN Decoder

World-class VIN decoder with comprehensive offline database (948+ WMI codes) and NHTSA vPIC API integration

**Author**: Wal33D
**Email**: aquataze@yahoo.com

## 🎯 Overview

Advanced VIN decoder featuring both enhanced offline decoding capabilities and official NHTSA vPIC API integration. Provides complete vehicle specifications through manufacturer-specific decoders and a comprehensive WMI database with **948+ manufacturer codes**.

## ✨ Features

- **Enhanced Offline Decoder** - Full VIN decoding without internet
- **948+ Manufacturer Codes** - Comprehensive WMI database (3x industry standard)
- **Manufacturer-Specific Decoders** - Detailed model/trim/engine extraction
- **VIN Validation** - Check digit verification per ISO 3779
- **Year Decoding** - Accurate model year extraction (1980-2039)
- **Official NHTSA API** - Falls back to government database when online
- **Automatic Fallback** - Seamlessly switches between offline/online
- **FREE** - No API key required
- **Caching** - Built-in LRU cache to reduce API calls
- **Multi-platform** - Java/Android and Python implementations

## 📁 Directory Structure

```
nhtsa-vin-decoder/
├── java/io/github/vindecoder/
│   ├── offline/                    # Offline decoder implementation
│   │   ├── OfflineVINDecoder.java  # Main offline decoder
│   │   ├── VINValidator.java       # VIN validation & structure
│   │   ├── WMIDatabase.java        # 948+ manufacturer codes
│   │   └── MercedesBenzDecoder.java # Example manufacturer decoder
│   └── nhtsa/                      # NHTSA API integration
│       ├── VINDecoderService.java
│       ├── VehicleData.java
│       └── NHTSAApiService.java
├── python/                         # Python implementation
│   ├── nhtsa_vin_decoder.py       # Main decoder with API
│   └── wmi_database.py            # Offline WMI database
├── data/                          # Reference data (not used at runtime)
│   ├── *.csv                      # Source WMI data from WALL-E/vin-decoder
│   ├── process_wmi.py             # Script to regenerate database
│   └── wmi_database_generated.java # Generated code
└── docs/                          # Documentation
    ├── ADDING_DECODERS.md        # Guide for adding manufacturers
    ├── VIN_DECODER_RESOURCES.md  # External data sources
    ├── API.md                    # Complete API reference
    ├── INSTALLATION.md           # Setup guide
    ├── USAGE.md                  # Examples and best practices
    └── WMI_DATABASE.md          # Offline database details
```

## 🚀 What You Get

### Offline Decoding (No Internet Required)
```json
{
  "vin": "4JGDA5HB7JB158144",
  "make": "Mercedes-Benz",
  "manufacturer": "Mercedes-Benz (Daimler AG)",
  "model": "GLE-Class",
  "year": "2018",
  "trim": "GLE 350 4MATIC",
  "vehicle_type": "Sport Utility Vehicle (SUV)",
  "body_class": "Sport Utility Vehicle (SUV)",
  "doors": "4",
  "drive_type": "4MATIC",
  "engine_model": "3.5L V6",
  "engine_cylinders": "6",
  "engine_displacement_l": "3.5",
  "fuel_type": "Gasoline",
  "transmission_style": "Automatic",
  "transmission_speeds": "9",
  "plant_city": "Tuscaloosa",
  "plant_state": "Alabama",
  "plant_country": "United States",
  "gvwr": "6062",
  "curb_weight": "4630"
}
```

### Online Mode (Full NHTSA Data)
All of the above PLUS safety ratings, recalls, NCAP data, and more.

## 💻 Quick Start

### Java - Offline Mode (No Internet)
```java
import io.github.vindecoder.offline.OfflineVINDecoder;
import io.github.vindecoder.nhtsa.VehicleData;

OfflineVINDecoder decoder = new OfflineVINDecoder();
VehicleData vehicle = decoder.decode("4JGDA5HB7JB158144");

System.out.println("Vehicle: " + vehicle.getModelYear() + " " +
                   vehicle.getMake() + " " + vehicle.getModel());
// Output: Vehicle: 2018 Mercedes-Benz GLE-Class

System.out.println("Trim: " + vehicle.getTrim());
// Output: Trim: GLE 350 4MATIC

System.out.println("Engine: " + vehicle.getEngineModel());
// Output: Engine: 3.5L V6
```

### Java - With NHTSA API
```java
VINDecoderService decoder = VINDecoderService.getInstance();

decoder.decodeVIN("4JGDA5HB7JB158144", new VINDecoderCallback() {
    @Override
    public void onSuccess(VehicleData vehicle) {
        // Full NHTSA data returned
        System.out.println("Vehicle: " + vehicle.getDisplayName());
    }

    @Override
    public void onError(String error) {
        // Automatically falls back to offline decoder
        VehicleData vehicle = new OfflineVINDecoder().decode(vin);
    }
});
```

## 🧪 Testing

### Java (Year Decoding Test)
- Using Gradle (recommended):
```
./gradlew test
```
This runs JUnit tests validating VIN model year decoding across the 30-year cycle, including 2031–2039 (digit codes with position 7 letter heuristic).

### Python (Year Decoding Test)
- Run tests:
```
python3 tests/test_year.py
```

This validates the Python WMI fallback year-decoding heuristic matches the Java logic.

### Python
```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("4JGDA5HB7JB158144")

print(f"Vehicle: {vehicle.year} {vehicle.make} {vehicle.model}")
# Output: Vehicle: 2018 Mercedes-Benz GLE-Class
```

## 📊 Offline Decoder Coverage

### Global Manufacturer Support (948+ WMI Codes)

**North America** (Complete)
- United States: Ford, GM, Tesla, Rivian, Lucid
- Canada: All manufacturers
- Mexico: All manufacturers

**Europe** (Comprehensive)
- Germany: Mercedes-Benz, BMW, Audi, Porsche, Volkswagen
- Italy: Ferrari, Lamborghini, Alfa Romeo, Maserati
- UK: Jaguar, Land Rover, Aston Martin, Bentley, Rolls-Royce
- France: Renault, Peugeot, Citroën, Bugatti

**Asia** (Extensive)
- Japan: Toyota, Honda, Nissan, Mazda, Subaru, Mitsubishi
- Korea: Hyundai, Kia, Genesis
- China: BYD, NIO, XPeng, Geely, and 200+ manufacturers

**Special Vehicle Types**
- Motorcycles: Harley-Davidson, Yamaha, Honda, Ducati, BMW
- Commercial: Freightliner, Kenworth, Peterbilt, Mack
- Buses: Blue Bird, Thomas, Gillig
- Agricultural: John Deere
- Electric: Tesla, Rivian, Lucid, Polestar, Fisker

### Manufacturer-Specific Decoders

Currently implemented:
- **Mercedes-Benz**: 115+ model variants with full specs
- **Ford**: 100+ model codes including F-Series, Mustang, Explorer, Edge
- **GM**: Chevrolet, Cadillac, Buick, GMC with RPO engine codes
- **Toyota/Lexus**: Comprehensive model and engine coverage

Easily extensible for:
- Honda, BMW, Nissan, etc.
- See [ADDING_DECODERS.md](docs/ADDING_DECODERS.md) for implementation guide

## 🔍 Comparison

### Current Capabilities (What We Actually Have)

| Feature | Our Offline Decoder | NHTSA API (Online) | Basic WMI Only |
|---------|-------------------|-------------------|----------------|
| **Manufacturer** | ✓ 948+ codes | ✓ All | ~100-300 codes |
| **Make** | ✓ All from WMI | ✓ All | ✓ Limited |
| **Model** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Year** | ✓ 1980-2039 | ✓ All | ✗ |
| **Trim/Series** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Engine Details** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Transmission** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Body Style** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Drive Type** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **Plant Location** | ✓ Basic all | ✓ Detailed | ✗ |
| **Weight Specs** | ✓ Ford, GM, Toyota, Mercedes* | ✓ All | ✗ |
| **VIN Validation** | ✓ ISO 3779 | ✓ | Basic |
| **Check Digit** | ✓ Full validation | ✓ | ✗ |
| **Region/Country** | ✓ All | ✓ All | ✓ Basic |
| **Safety Data** | ✗ | ✓ NCAP ratings | ✗ |
| **Recall Data** | ✗ | ✓ | ✗ |
| **Works Offline** | ✓ Always | ✗ Needs internet | ✓ |
| **Speed** | <1ms | 200-500ms | <1ms |
| **Free** | ✓ | ✓ | ✓ |

*Can be extended to other manufacturers by adding decoders (see [ADDING_DECODERS.md](docs/ADDING_DECODERS.md))

## 📈 Recent Improvements

### Version 2.0 (October 2025)
- **3x More Coverage**: Increased from 311 to 948+ WMI codes
- **Fixed Year Bug**: Now correctly decodes 2010+ model years
- **4 Manufacturer Decoders**: Mercedes-Benz, Ford, GM, Toyota/Lexus with full specs
- **Enhanced Validation**: ISO 3779 check digit verification
- **Extensible Architecture**: Easy to add new manufacturers
- **Reference Data**: CSV sources included for transparency

## 🛠️ Installation

### Java/Android
```gradle
// Add as submodule
git submodule add https://github.com/Wal33D/nhtsa-vin-decoder.git modules/nhtsa-vin-decoder

// For NHTSA API support, add:
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

### Python
```python
# No external dependencies for offline mode
from python.wmi_database import WMIDatabase

# For API support:
pip install requests  # Optional, uses urllib by default
```

## 📚 Documentation

- [ADDING_DECODERS.md](docs/ADDING_DECODERS.md) - Add manufacturer-specific decoders
- [VIN_DECODER_RESOURCES.md](docs/VIN_DECODER_RESOURCES.md) - External data sources
- [API Reference](docs/API.md) - Complete API documentation
- [WMI Database](docs/WMI_DATABASE.md) - Offline database details

## 🔧 Extending the Decoder

### Adding a New Manufacturer Decoder
See [ADDING_DECODERS.md](docs/ADDING_DECODERS.md) for complete guide.

Quick example for Ford:
```java
public class FordDecoder {
    public static VehicleInfo decode(String vin) {
        // Extract model codes, engine, transmission
        // See MercedesBenzDecoder.java for reference
    }
}
```

### Updating WMI Database
```bash
cd data/
# Edit CSV files to add new codes
python3 process_wmi.py
# Copy generated code to WMIDatabase.java
```

## 🧩 Using As a Gradle Submodule

- Add this repo as a Git submodule, e.g. under `modules/nhtsa-vin-decoder`.
- In your root `settings.gradle` (or `settings.gradle.kts`) include the project:
```
include ':nhtsa-vin-decoder'
project(':nhtsa-vin-decoder').projectDir = new File(rootDir, 'modules/nhtsa-vin-decoder')
```
- In your app module `build.gradle` add a dependency:
```
implementation project(':nhtsa-vin-decoder')
```
- If you use the online NHTSA API classes, add runtime dependencies in your app:
```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.google.code.gson:gson:2.10.1'
```

## ⚡ Performance

- **Offline Decode**: <1ms (HashMap lookup)
- **Online Decode**: ~200-500ms (API call)
- **Memory**: ~100KB for WMI database
- **No File I/O**: All codes compiled into binary

## 🎯 Use Cases

- **OBD-II Apps** - Vehicle context without internet
- **Fleet Management** - Offline vehicle identification
- **Insurance** - Quick VIN validation
- **Parts Lookup** - Accurate model/engine matching
- **Automotive Tools** - Professional diagnostic apps
- **Classic Cars** - Decode vintage VINs (1980+)

## 🤝 Contributing

Found a missing WMI code or want to add a manufacturer decoder?
1. Fork the repository
2. Add codes to `data/*.csv` or create new decoder in `java/io/github/vindecoder/offline/`
3. Submit PR with test results

## 📄 License

MIT License - Free for commercial and non-commercial use

## 📧 Contact

**Wal33D** - aquataze@yahoo.com
**Repository**: https://github.com/Wal33D/nhtsa-vin-decoder

## 🙏 Credits

- NHTSA for providing the free vPIC API
- WALL-E/vin-decoder for WMI CSV data
- ISO for VIN standards (ISO 3779:2009)
- US Department of Transportation

## 🔮 Roadmap

- [x] Ford decoder (positions 4-8 patterns)
- [x] GM/Chevrolet decoder
- [x] Toyota/Lexus decoder
- [ ] Honda/Acura decoder
- [ ] BMW decoder (17-character patterns)
- [ ] Nissan/Infiniti decoder
- [ ] Recall integration
