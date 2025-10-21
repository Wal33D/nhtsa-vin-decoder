# NHTSA VIN Decoder

[![Build Status](https://github.com/Wal33D/nhtsa-vin-decoder/workflows/CI/badge.svg)](https://github.com/Wal33D/nhtsa-vin-decoder/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Android](https://img.shields.io/badge/Android-API%2021+-green.svg)](https://developer.android.com/)
[![Python](https://img.shields.io/badge/Python-3.6+-blue.svg)](https://www.python.org/)

World-class VIN decoder with comprehensive offline database (2,015+ WMI codes) and NHTSA vPIC API integration

**Author**: Wal33D
**Email**: aquataze@yahoo.com

## ⚡ Quick Start

### Java - Offline Mode (No Internet Required)
```java
import io.github.vindecoder.offline.OfflineVINDecoder;
import io.github.vindecoder.nhtsa.VehicleData;

OfflineVINDecoder decoder = new OfflineVINDecoder();
VehicleData vehicle = decoder.decode("1HGCM82633A004352");

System.out.println(vehicle.getModelYear() + " " +
                   vehicle.getMake() + " " +
                   vehicle.getModel());
// Output: 2003 Honda Accord
```

### Android - With Async Callbacks
```java
import io.github.vindecoder.android.VINDecoderAndroid;
import io.github.vindecoder.nhtsa.VehicleData;

VINDecoderAndroid decoder = new VINDecoderAndroid(context);
decoder.decodeAsync("1HGCM82633A004352", new VINDecoderAndroid.DecodeCallback() {
    @Override
    public void onSuccess(VehicleData vehicle) {
        // Called on main thread
        Log.d("VIN", vehicle.getModelYear() + " " + vehicle.getMake());
        // Output: 2003 Honda
    }

    @Override
    public void onError(String error) {
        Log.e("VIN", "Decode failed: " + error);
    }
});
```

### Python - With NHTSA API
```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")

print(f"{vehicle.year} {vehicle.make} {vehicle.model}")
# Output: 2003 Honda Accord
```

### Python - Offline Mode
```python
from python.wmi_database import WMIDatabase

manufacturer = WMIDatabase.get_manufacturer("1HGCM82633A004352")
year = WMIDatabase.get_year("1HGCM82633A004352")

print(f"{year} {manufacturer}")
# Output: 2003 Honda
```

### Python - VIN Recall Lookup
```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle, recalls = decoder.get_recalls_by_vin("5J6RW2H89KA000000")

print(vehicle.get_display_name())
for recall in recalls:
    print(f"{recall.nhtsa_campaign_number}: {recall.summary}")
```

### Java - VIN Recall Lookup (New!)
```java
import io.github.vindecoder.nhtsa.*;

VINDecoderService decoder = VINDecoderService.getInstance();

// Decode VIN and get recalls
decoder.getRecallsByVin("5J6RW2H89KA000000", new VINDecoderCallback() {
    @Override
    public void onSuccess(VehicleData vehicle) {
        System.out.println(vehicle.getDisplayName());
        if (vehicle.hasRecalls()) {
            for (RecallRecord recall : vehicle.getRecalls()) {
                System.out.println(recall.getNhtsaCampaignNumber() + ": " + recall.getSummary());
            }
        }
    }

    @Override
    public void onError(String error) {
        System.err.println("Error: " + error);
    }
});

// Or get recalls by make/model/year
decoder.getRecallsForVehicle("Honda", "CR-V", "2019", new RecallCallback() {
    @Override
    public void onSuccess(List<RecallRecord> recalls) {
        System.out.println("Found " + recalls.size() + " recalls");
    }

    @Override
    public void onError(String error) {
        System.err.println("Error: " + error);
    }
});
```

### Android - VIN Recall Lookup (New!)
```java
VINDecoderAndroid decoder = new VINDecoderAndroid(context);

// Decode VIN with recalls - runs on main thread
decoder.decodeWithRecalls("5J6RW2H89KA000000", new DecodeCallback() {
    @Override
    public void onSuccess(VehicleData vehicle) {
        // Safe to update UI
        textView.setText(vehicle.getDisplayName());
        if (vehicle.hasRecalls()) {
            recallCountView.setText("⚠ " + vehicle.getRecallCount() + " recalls");
        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
});

// Or get recalls by make/model
decoder.getRecallsForVehicle("Honda", "CR-V", "2019", new RecallCallback() {
    @Override
    public void onSuccess(List<RecallRecord> recalls) {
        // Update UI with recall list
        updateRecallsList(recalls);
    }

    @Override
    public void onError(String error) {
        Log.e("Recalls", error);
    }
});
```

## 🎯 Overview

Advanced VIN decoder featuring both enhanced offline decoding capabilities and official NHTSA vPIC API integration. Provides complete vehicle specifications through manufacturer-specific decoders and a comprehensive WMI database with **2,015+ manufacturer codes**.

## ✨ Features

- **Enhanced Offline Decoder** - Full VIN decoding without internet
- **2,015+ Manufacturer Codes** - Comprehensive WMI database (6x industry standard)
- **Manufacturer-Specific Decoders** - Detailed model/trim/engine extraction
- **VIN Validation** - Check digit verification per ISO 3779
- **Year Decoding** - Accurate model year extraction (1980-2039)
- **Official NHTSA API** - Falls back to government database when online
- **Recall Lookup** - Pull active NHTSA campaigns by VIN, make/model/year (All platforms)
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
│       ├── VINDecoderService.java  # Main service with recall support
│       ├── VehicleData.java        # Vehicle data with recall field
│       ├── NHTSAApiService.java    # VIN API interface
│       ├── RecallRecord.java       # Recall campaign data model (NEW)
│       ├── RecallResponse.java     # Recall API response wrapper (NEW)
│       └── NHTSARecallApiService.java # Recall API interface (NEW)
├── android/                        # Android wrapper library
│   └── nhtsa-vin-decoder-android/
│       └── src/main/java/io/github/vindecoder/android/
│           └── VINDecoderAndroid.java  # Android-friendly async API
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

### Android - Complete Example
```java
import io.github.vindecoder.android.VINDecoderAndroid;
import io.github.vindecoder.nhtsa.VehicleData;

public class MainActivity extends AppCompatActivity {
    private VINDecoderAndroid decoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decoder = new VINDecoderAndroid(this);

        // Async decode with main thread callbacks
        decoder.decodeAsync("4JGDA5HB7JB158144", new VINDecoderAndroid.DecodeCallback() {
            @Override
            public void onSuccess(VehicleData vehicle) {
                // Safe to update UI - callback runs on main thread
                textView.setText(vehicle.getModelYear() + " " +
                               vehicle.getMake() + " " +
                               vehicle.getModel());
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        // Or use synchronous methods on background thread
        new Thread(() -> {
            VehicleData vehicle = decoder.decode("4JGDA5HB7JB158144");
            runOnUiThread(() -> {
                // Update UI
            });
        }).start();
    }
}
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

### Global Manufacturer Support (2,015+ WMI Codes)

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
| **Manufacturer** | ✓ 2,015+ codes | ✓ All | ~100-300 codes |
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

### Version 2.1 (October 2025)
- **Recall Lookup for Java/Android**: Added NHTSA recall campaign lookup to Java and Android implementations
- **RecallRecord Class**: Comprehensive recall data model with safety flags
- **Async Recall Methods**: Android-optimized recall lookups with main thread callbacks
- **Recall Caching**: Built-in cache for recall API responses

### Version 2.0 (October 2025)
- **6x More Coverage**: Increased from 311 to 2,015+ WMI codes
- **Fixed Year Bug**: Now correctly decodes 2010+ model years
- **4 Manufacturer Decoders**: Mercedes-Benz, Ford, GM, Toyota/Lexus with full specs
- **Enhanced Validation**: ISO 3779 check digit verification
- **Extensible Architecture**: Easy to add new manufacturers
- **Reference Data**: CSV sources included for transparency

## 🛠️ Installation

### Java
```gradle
// Add as submodule
git submodule add https://github.com/Wal33D/nhtsa-vin-decoder.git modules/nhtsa-vin-decoder

// In your root settings.gradle:
include ':nhtsa-vin-decoder'
project(':nhtsa-vin-decoder').projectDir = new File(rootDir, 'modules/nhtsa-vin-decoder')

// In your app build.gradle:
implementation project(':nhtsa-vin-decoder')

// For NHTSA API support, add:
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

### Android
```gradle
// In your root settings.gradle:
include ':nhtsa-vin-decoder-android'
include ':nhtsa-vin-decoder-java'
project(':nhtsa-vin-decoder-android').projectDir = new File(rootDir, 'modules/nhtsa-vin-decoder/android/nhtsa-vin-decoder-android')
project(':nhtsa-vin-decoder-java').projectDir = new File(rootDir, 'modules/nhtsa-vin-decoder')

// In your app build.gradle:
implementation project(':nhtsa-vin-decoder-android')

// The Android module already includes Retrofit dependencies
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

### Speed Comparison

| Operation | Time | Throughput | Use Case |
|-----------|------|------------|----------|
| **Offline Decode** | <1ms | 1,000+ VINs/sec | Real-time apps, mobile |
| **Online Decode** | 200-500ms | ~2-5 VINs/sec | Complete data needed |
| **Batch Offline** | 0.5s/1000 VINs | 2,000 VINs/sec | Fleet management |
| **Batch Online (parallel)** | 10s/100 VINs | ~10 VINs/sec | Background processing |

### Why Offline Mode is 500x Faster

```
Offline:  VIN → HashMap lookup → Result (0.8ms)
Online:   VIN → HTTP request → NHTSA server → Response parsing → Result (350ms)
```

### Resource Usage

- **Memory**: ~100KB for WMI database (2,015+ codes)
- **Storage**: ~500KB total (includes manufacturer decoders)
- **CPU**: Negligible (<1% for typical usage)
- **Network**: Zero for offline mode, ~5KB per VIN for online

### Real-World Performance Test

```python
# Test: Decode 1,000 VINs
# Hardware: MacBook Pro M1, 16GB RAM

Offline mode:  0.534 seconds (1,873 VINs/sec)
Online mode:   342.8 seconds (2.9 VINs/sec)
Speedup:       642x faster
```

### When to Use Each Mode

**Use Offline Mode When:**
- Speed is critical (<1ms response time)
- Working without internet connection
- Processing large batches (1,000+ VINs)
- Building mobile/embedded apps
- Need basic info (manufacturer, year, region)

**Use Online Mode When:**
- Need complete vehicle specifications
- Want safety ratings and recall data
- Require NCAP test results
- Need detailed engine/transmission specs
- Accuracy is more important than speed

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
- Claude (Anthropic) for assistance with GitHub release and PyPI publication

## 🔮 Roadmap

- [x] Ford decoder (positions 4-8 patterns)
- [x] GM/Chevrolet decoder
- [x] Toyota/Lexus decoder
- [ ] Honda/Acura decoder
- [ ] BMW decoder (17-character patterns)
- [ ] Nissan/Infiniti decoder
