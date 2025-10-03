# Adding Manufacturer-Specific VIN Decoders

This guide explains how to add new manufacturer-specific VIN decoders to enhance the offline VIN decoding capabilities.

## Overview

The offline VIN decoder system uses a two-tier approach:
1. **Basic WMI Database** - Identifies manufacturer from first 3 characters
2. **Manufacturer-Specific Decoders** - Extract detailed model, engine, and feature information

## Structure of a Manufacturer Decoder

Each manufacturer decoder should follow this pattern:

```java
package io.github.vindecoder.offline;

public class [Manufacturer]Decoder {
    // Static maps for decoding patterns
    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();
    private static final Map<String, String> BODY_STYLES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
        initializeBodyStyles();
    }

    public static VehicleInfo decode(String vin) {
        // Decode logic here
    }
}
```

## Step-by-Step Guide to Add a New Decoder

### Step 1: Research VIN Patterns

Before coding, research the manufacturer's VIN structure:
- **Positions 1-3**: WMI (World Manufacturer Identifier)
- **Positions 4-8**: VDS (Vehicle Descriptor Section) - Model/Body/Engine codes
- **Position 9**: Check digit
- **Position 10**: Model year
- **Position 11**: Assembly plant
- **Positions 12-17**: Sequential production number

### Step 2: Create the Decoder Class

Create a new file: `[Manufacturer]Decoder.java`

Example for Ford:

```java
package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

public class FordDecoder {

    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
    }

    public static class ModelInfo {
        public String model;
        public String series;
        public String bodyClass;
        public String driveType;
        public String doors;

        public ModelInfo(String model, String series, String bodyClass,
                        String driveType, String doors) {
            this.model = model;
            this.series = series;
            this.bodyClass = bodyClass;
            this.driveType = driveType;
            this.doors = doors;
        }
    }

    private static void initializeModelCodes() {
        // F-150 variants (positions 4-7 of VIN)
        MODEL_CODES.put("F1C", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F1E", new ModelInfo("F-150", "SuperCab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("F1F", new ModelInfo("F-150", "SuperCrew", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("W1C", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("W1E", new ModelInfo("F-150", "SuperCab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("W1F", new ModelInfo("F-150", "SuperCrew", "Pickup Truck", "4WD", "4"));

        // Mustang variants
        MODEL_CODES.put("A5", new ModelInfo("Mustang", "Fastback", "Coupe", "RWD", "2"));
        MODEL_CODES.put("A6", new ModelInfo("Mustang", "Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("A8", new ModelInfo("Mustang", "Shelby GT350", "Coupe", "RWD", "2"));
        MODEL_CODES.put("A9", new ModelInfo("Mustang", "Shelby GT500", "Coupe", "RWD", "2"));

        // Explorer variants
        MODEL_CODES.put("K8D", new ModelInfo("Explorer", "Base", "SUV", "RWD", "4"));
        MODEL_CODES.put("K8E", new ModelInfo("Explorer", "XLT", "SUV", "4WD", "4"));
        MODEL_CODES.put("K8F", new ModelInfo("Explorer", "Limited", "SUV", "4WD", "4"));
        MODEL_CODES.put("K8G", new ModelInfo("Explorer", "ST", "SUV", "4WD", "4"));

        // Add more models...
    }

    private static void initializeEngineCodes() {
        // Position 8 engine codes
        ENGINE_CODES.put("2", "2.3L EcoBoost I4");
        ENGINE_CODES.put("5", "2.7L EcoBoost V6");
        ENGINE_CODES.put("B", "3.5L EcoBoost V6");
        ENGINE_CODES.put("D", "3.5L PowerBoost Hybrid V6");
        ENGINE_CODES.put("F", "5.0L Coyote V8");
        ENGINE_CODES.put("H", "5.2L Voodoo V8");
        ENGINE_CODES.put("J", "3.0L Power Stroke Diesel V6");
        ENGINE_CODES.put("M", "3.3L Ti-VCT V6");
        ENGINE_CODES.put("Y", "3.5L Ti-VCT V6");
        ENGINE_CODES.put("8", "6.7L Power Stroke Diesel V8");
        ENGINE_CODES.put("G", "7.3L Godzilla V8");
        // Add more engine codes...
    }

    public static VehicleInfo decode(String vin) {
        if (vin == null || vin.length() < 17) {
            return null;
        }

        VehicleInfo info = new VehicleInfo();

        // Extract model code (varies by manufacturer)
        // For Ford trucks: positions 4-7
        // For Ford cars: positions 4-5 or 6-7
        String modelCode;
        if (vin.charAt(3) == 'F' || vin.charAt(3) == 'W') {
            // Truck pattern
            modelCode = vin.substring(3, 6);
        } else {
            // Car pattern
            modelCode = vin.substring(5, 7);
        }

        ModelInfo modelInfo = MODEL_CODES.get(modelCode);
        if (modelInfo != null) {
            info.model = modelInfo.model;
            info.series = modelInfo.series;
            info.bodyClass = modelInfo.bodyClass;
            info.driveType = modelInfo.driveType;
            info.doors = modelInfo.doors;
        }

        // Extract engine code (position 8)
        String engineCode = String.valueOf(vin.charAt(7));
        info.engineDescription = ENGINE_CODES.get(engineCode);

        // Determine transmission
        if (info.model != null) {
            if (info.model.contains("F-150") || info.model.contains("F-250")) {
                info.transmissionStyle = "Automatic";
                info.transmissionSpeeds = "10"; // Most modern F-150s have 10-speed
            } else if (info.model.contains("Mustang")) {
                // Could be manual or automatic
                if (engineCode.equals("H") || engineCode.equals("F")) {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/10";
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "10";
                }
            }
        }

        // Set manufacturer info
        info.manufacturer = "Ford";
        info.manufacturerName = "Ford Motor Company";

        // Determine plant location based on position 11
        char plantCode = vin.charAt(10);
        switch (plantCode) {
            case 'C':
                info.plantCity = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'D':
                info.plantCity = "Dearborn";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'F':
                info.plantCity = "Flat Rock";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'K':
                info.plantCity = "Kansas City";
                info.plantState = "Missouri";
                info.plantCountry = "United States";
                break;
            case 'L':
                info.plantCity = "Louisville";
                info.plantState = "Kentucky";
                info.plantCountry = "United States";
                break;
            // Add more plant codes...
        }

        return info;
    }

    public static class VehicleInfo {
        public String manufacturer;
        public String manufacturerName;
        public String model;
        public String series;
        public String bodyClass;
        public String driveType;
        public String doors;
        public String engineDescription;
        public String transmissionStyle;
        public String transmissionSpeeds;
        public String plantCity;
        public String plantState;
        public String plantCountry;
        public String gvwr;
        public String curbWeight;
    }
}
```

### Step 3: Integrate with OfflineVINDecoder

Edit `OfflineVINDecoder.java` to use your new decoder:

```java
// In the decode() method, after getting manufacturer:

if (manufacturer != null) {
    if (manufacturer.contains("Ford") || wmi.startsWith("1F")) {
        // Use Ford-specific decoder
        FordDecoder.VehicleInfo fordInfo = FordDecoder.decode(vin);
        if (fordInfo != null) {
            // Map fordInfo fields to vehicleData
            if (fordInfo.model != null) vehicleData.setModel(fordInfo.model);
            if (fordInfo.series != null) vehicleData.setTrim(fordInfo.series);
            // ... map other fields
        }
    } else if (manufacturer.contains("General Motors") || wmi.startsWith("1G")) {
        // Use GM-specific decoder
        GMDecoder.VehicleInfo gmInfo = GMDecoder.decode(vin);
        // ... map fields
    } else if (manufacturer.contains("Toyota") || wmi.startsWith("4T") || wmi.startsWith("5T")) {
        // Use Toyota-specific decoder
        ToyotaDecoder.VehicleInfo toyotaInfo = ToyotaDecoder.decode(vin);
        // ... map fields
    }
    // Add more manufacturer checks...
}
```

## Common VIN Patterns by Manufacturer

### Ford (WMI: 1FA, 1FB, 1FC, 1FD, 1FM, 1FT)
- Position 4-7: Model/body style
- Position 8: Engine
- Position 11: Assembly plant

### General Motors (WMI: 1G1, 1G2, 1G3, 1G4, 1G6, 1GC)
- Position 4-5: Car line/series
- Position 6-7: Body style/model
- Position 8: Engine
- Position 11: Assembly plant

### Toyota (WMI: 4T1, 4T3, 5TB, 5TD, 5TE)
- Position 4-5: Model line
- Position 6: Series/grade
- Position 7: Body type/doors
- Position 8: Engine

### Honda (WMI: 1HG, 2HG, JHM)
- Position 4-5: Model
- Position 6: Body/transmission type
- Position 7: Model series
- Position 8: Engine

### Nissan (WMI: 1N4, 1N6, JN1, JN8)
- Position 4-5: Model line
- Position 6: Model change
- Position 7: Body type
- Position 8: Engine

## Testing Your Decoder

Create a test class to verify your decoder:

```java
public class TestFordDecoder {
    public static void main(String[] args) {
        // Test VINs
        String[] testVINs = {
            "1FTFW1ET5DFC12345", // F-150 SuperCrew 4WD
            "1FA6P8CF1J5123456", // Mustang GT
            "1FM5K8GC6LGA12345"  // Explorer ST
        };

        for (String vin : testVINs) {
            FordDecoder.VehicleInfo info = FordDecoder.decode(vin);
            System.out.println("VIN: " + vin);
            System.out.println("Model: " + info.model);
            System.out.println("Series: " + info.series);
            System.out.println("Engine: " + info.engineDescription);
            System.out.println("---");
        }
    }
}
```

## Resources for VIN Research

### Official Sources
- **NHTSA VIN Decoder**: https://vpic.nhtsa.dot.gov/decoder/
- **Manufacturer Media Sites**: Often have VIN guides in press kits
- **Service Manuals**: Contain detailed VIN breakdowns

### VIN Structure by Position
```
Position 1-3:  WMI (World Manufacturer Identifier)
Position 4-5:  Vehicle line/platform
Position 6-7:  Body style/model
Position 8:    Engine code
Position 9:    Check digit
Position 10:   Model year
Position 11:   Assembly plant
Position 12-17: Sequential production number
```

### Model Year Codes
```
A=2010  L=2020  Y=2030
B=2011  M=2021  1=2031
C=2012  N=2022  2=2032
D=2013  P=2023  3=2033
E=2014  R=2024  4=2034
F=2015  S=2025  5=2035
G=2016  T=2026  6=2036
H=2017  V=2027  7=2037
J=2018  W=2028  8=2038
K=2019  X=2029  9=2039
```

## Best Practices

1. **Start Simple**: Begin with common models and expand gradually
2. **Use Patterns**: Look for consistent patterns in the manufacturer's VIN structure
3. **Handle Variations**: Same model might have different codes across years
4. **Fail Gracefully**: Return partial data rather than null when possible
5. **Document Sources**: Comment where you got the decoding information
6. **Test Thoroughly**: Use real VINs from various years and models

## Example: Adding Chevrolet Decoder

```java
public class ChevroletDecoder {
    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();

    static {
        // Silverado 1500
        MODEL_CODES.put("CC10", new ModelInfo("Silverado 1500", "Regular Cab", "Pickup", "RWD", "2"));
        MODEL_CODES.put("CC20", new ModelInfo("Silverado 1500", "Double Cab", "Pickup", "RWD", "4"));
        MODEL_CODES.put("CK10", new ModelInfo("Silverado 1500", "Regular Cab", "Pickup", "4WD", "4"));
        MODEL_CODES.put("CK20", new ModelInfo("Silverado 1500", "Double Cab", "Pickup", "4WD", "4"));

        // Corvette
        MODEL_CODES.put("Y07", new ModelInfo("Corvette", "Stingray", "Coupe", "RWD", "2"));
        MODEL_CODES.put("Y17", new ModelInfo("Corvette", "Stingray Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("Y27", new ModelInfo("Corvette", "Z06", "Coupe", "RWD", "2"));

        // Camaro
        MODEL_CODES.put("CC1", new ModelInfo("Camaro", "LS/LT", "Coupe", "RWD", "2"));
        MODEL_CODES.put("CD1", new ModelInfo("Camaro", "SS", "Coupe", "RWD", "2"));
        MODEL_CODES.put("CE1", new ModelInfo("Camaro", "ZL1", "Coupe", "RWD", "2"));
    }

    // ... implement decode() method
}
```

## Contributing

When adding a new decoder:
1. Create the decoder class in `/java/io/github/vindecoder/offline/`
2. Add integration in `OfflineVINDecoder.java`
3. Test with real VINs
4. Submit a pull request with:
   - The new decoder class
   - Integration changes
   - Test results
   - Sources for VIN patterns used

## License

All decoders should be compatible with the project's open-source license.
