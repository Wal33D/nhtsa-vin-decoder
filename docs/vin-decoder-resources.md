# VIN Decoder Resources & Data Sources

This document lists comprehensive GitHub repositories and resources for building a world-class offline VIN decoder.

## 🌟 Top Repositories with VIN Data

### 1. **[WALL-E/vin-decoder](https://github.com/WALL-E/vin-decoder)**
**Best for:** Comprehensive WMI CSV databases
- **Files:**
  - `csv/wmi-from-wiki.csv` - 300+ WMI codes from Wikipedia
  - `csv/wmi-from-github.csv` - GitHub-sourced WMI codes
  - `csv/wmi-from-offline.csv` - Additional offline data
  - `csv/wmi.csv` - Main consolidated database
- **Coverage:** Global manufacturers including Asia, Europe, Americas
- **Format:** CSV files, easy to parse and import

### 2. **[idlesign/vininfo](https://github.com/idlesign/vininfo)**
**Best for:** Python implementation with extensible WMI dictionary
- **Features:**
  - Comprehensive WMI dictionary (`dicts/wmi.py`)
  - Manufacturer-specific decoders (Nissan, Opel, Renault, AvtoVAZ)
  - Checksum validation
  - Modular architecture with `Brand` subclasses
- **Useful patterns:** Detail descriptors for extracting specific info

### 3. **[peyo/dtc-and-vin-data](https://github.com/peyo/dtc-and-vin-data)**
**Best for:** JSON formatted VIN data
- **Format:** JSON with WMI-VIN pairs
- **Source:** Web scraped data using Python/Scrapy
- **Bonus:** Also includes DTC (Diagnostic Trouble Code) data

### 4. **[ApelSYN/node-vin-lite](https://github.com/ApelSYN/node-vin-lite)**
**Best for:** JavaScript/Node.js implementation
- **Coverage:** ~1000 WMI codes for major manufacturers
- **Standards:** ISO 3779:2009 compliant
- **Note:** Version 2.1.0 added all USSR manufacturer codes
- **Important:** References that complete SAE database has 33,000+ codes

### 5. **[adaptant-labs/vin-decoder-dart](https://github.com/adaptant-labs/vin-decoder-dart)**
**Best for:** Dart/Flutter implementation
- **Standards:** ISO 3779:2009 & ISO 3780:2009 compliant
- **Features:** VIN validation and synthetic VIN generation
- **Architecture:** Good reference for mobile app design

### 6. **[opencars/vin-decoder-api](https://github.com/opencars/vin-decoder-api)**
**Best for:** API architecture reference
- **Language:** Go
- **Features:** REST API for VIN decoding
- **Data:** Includes Tesla and other modern manufacturers

### 7. **[h3/python-libvin](https://github.com/h3/python-libvin)**
**Best for:** Most complete open-source implementation
- **Accuracy:** Claims ~90% accuracy for model lookup
- **Standards:** Supports FMVSS 115 (US/Canada) and ISO 3779 (Europe)
- **Coverage:** Comprehensive parsing including region, country, year, make

### 8. **[phillipsdata/vin](https://github.com/phillipsdata/vin)**
**Best for:** PHP implementation with validation
- **Language:** PHP
- **Features:** VIN validation and diagnostic library
- **Architecture:** Good OOP design patterns

## 📊 Data Format Examples

### WMI CSV Format (from WALL-E/vin-decoder)
```csv
WMI,Manufacturer,Country
1FA,Ford,United States
1G1,Chevrolet,United States
JH,Honda,Japan
JT,Toyota,Japan
WDB,Mercedes-Benz,Germany
```

### JSON Format (typical structure)
```json
{
  "wmi": "1FA",
  "manufacturer": "Ford",
  "country": "United States",
  "region": "North America",
  "vehicleType": "Passenger Car"
}
```

## 🔍 Manufacturer-Specific Resources

### Ford VINs
- WMIs: 1FA, 1FB, 1FC, 1FD, 1FM, 1FT
- Position 4-7: Model/body codes
- Position 8: Engine code

### General Motors VINs
- WMIs: 1G1, 1G2, 1G3, 1G4, 1G6, 1GC
- Position 4-5: Car line/series
- Position 6-7: Body style

### Toyota VINs
- WMIs: 4T1, 4T3, 5TB, 5TD, 5TE, JT
- Position 4-5: Model line
- Position 6: Series/grade
- Position 7: Body type

### Mercedes-Benz VINs
- WMIs: 4JG (USA), WDB, WDC, WDD (Germany)
- Position 4-6: Model codes (e.g., DA5 = GLE 350)
- Position 8: Engine type

## 🎯 Implementation Strategy

### For Android/Java (Our Use Case)

1. **Extract Data from WALL-E/vin-decoder CSVs:**
   ```bash
   # Download the CSV files
   wget https://raw.githubusercontent.com/WALL-E/vin-decoder/master/csv/wmi.csv
   wget https://raw.githubusercontent.com/WALL-E/vin-decoder/master/csv/wmi-from-github.csv
   ```

2. **Convert to Java HashMaps:**
   ```java
   // Parse CSV and build WMI database
   Map<String, String> wmiMap = new HashMap<>();
   // Load from CSV files
   ```

3. **Study vininfo's modular approach:**
   - Separate classes for each manufacturer
   - Detail descriptors for specific fields
   - Extensible Brand subclasses

4. **Reference python-libvin for decoding logic:**
   - Year calculation algorithms
   - Check digit validation
   - Region determination

## 📚 Additional Resources

### Official Documentation
- **NHTSA vPIC**: https://vpic.nhtsa.dot.gov/
- **SAE WMI Database**: https://www.sae.org (33,000+ codes, paid)
- **ISO 3779:2009**: VIN structure standard
- **ISO 3780:2009**: WMI standard

### Useful Tools
- **Online VIN Decoder**: https://vpic.nhtsa.dot.gov/decoder/
- **VIN Validator**: https://vindecoder.eu/check-vin
- **WMI Lookup**: https://en.wikibooks.org/wiki/Vehicle_Identification_Numbers_(VIN_codes)/World_Manufacturer_Identifier_(WMI)

## 🚀 Quick Start

To quickly populate our offline decoder:

1. **Clone WALL-E/vin-decoder:**
   ```bash
   git clone https://github.com/WALL-E/vin-decoder.git
   cd vin-decoder/csv
   ```

2. **Process the CSV files to extract:**
   - WMI → Manufacturer mappings
   - Country codes
   - Regional classifications

3. **Study idlesign/vininfo for patterns:**
   ```bash
   git clone https://github.com/idlesign/vininfo.git
   # Check vininfo/dicts/wmi.py for WMI dictionary
   # Check vininfo/details/*.py for manufacturer patterns
   ```

4. **Review ApelSYN/node-vin-lite for validation:**
   ```bash
   git clone https://github.com/ApelSYN/node-vin-lite.git
   # Check index.js for validation logic
   ```

## 💡 Key Insights

1. **Most comprehensive open-source**: ~1000-3000 WMI codes
2. **Complete coverage**: Requires SAE subscription (33,000+ codes)
3. **Best approach**: Combine multiple sources
4. **Validation is critical**: Check digit, valid characters, length
5. **Manufacturer-specific**: Each brand has unique patterns

## 🔧 Contributing

To enhance our offline decoder:
1. Extract data from these repositories
2. Add manufacturer-specific decoders
3. Validate against NHTSA API when possible
4. Submit PRs with new WMI codes and patterns

## License Note
Most VIN data is publicly available. However, always check individual repository licenses before using their code or data.