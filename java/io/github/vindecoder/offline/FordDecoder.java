package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

/**
 * Ford VIN Decoder
 *
 * Decodes Ford specific VIN patterns to extract model, engine, and body information
 * Based on Ford VIN structure and model codes
 *
 * @author Wal33D
 */
public class FordDecoder {

    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();
    private static final Map<String, String> GVWR_CODES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
        initializeGVWRCodes();
    }

    public static class ModelInfo {
        public String model;
        public String series;
        public String bodyClass;
        public String driveType;
        public String doors;

        public ModelInfo(String model, String series, String bodyClass, String driveType, String doors) {
            this.model = model;
            this.series = series;
            this.bodyClass = bodyClass;
            this.driveType = driveType;
            this.doors = doors;
        }
    }

    private static void initializeModelCodes() {
        // F-150 variants (positions 5-7)
        MODEL_CODES.put("F10", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F14", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F1C", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F1E", new ModelInfo("F-150", "SuperCab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("F1F", new ModelInfo("F-150", "SuperCrew", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("W1C", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("W1E", new ModelInfo("F-150", "SuperCab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("W1F", new ModelInfo("F-150", "SuperCrew", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("X1C", new ModelInfo("F-150", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("X1E", new ModelInfo("F-150", "SuperCab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("X1F", new ModelInfo("F-150", "SuperCrew", "Pickup Truck", "4WD", "4"));

        // F-250/F-350 Super Duty
        MODEL_CODES.put("F20", new ModelInfo("F-250", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F21", new ModelInfo("F-250", "SuperCab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("F25", new ModelInfo("F-250", "SuperCrew", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("X20", new ModelInfo("F-250", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("X21", new ModelInfo("F-250", "SuperCab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("X25", new ModelInfo("F-250", "SuperCrew", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("F30", new ModelInfo("F-350", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("F31", new ModelInfo("F-350", "SuperCab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("F35", new ModelInfo("F-350", "SuperCrew", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("W30", new ModelInfo("F-350", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("W31", new ModelInfo("F-350", "SuperCab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("W35", new ModelInfo("F-350", "SuperCrew", "Pickup Truck", "4WD", "4"));

        // Mustang variants
        MODEL_CODES.put("P8A", new ModelInfo("Mustang", "Fastback", "Coupe", "RWD", "2"));
        MODEL_CODES.put("P8C", new ModelInfo("Mustang", "Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("P8T", new ModelInfo("Mustang", "Shelby GT350", "Coupe", "RWD", "2"));
        MODEL_CODES.put("P8F", new ModelInfo("Mustang", "Shelby GT500", "Coupe", "RWD", "2"));
        MODEL_CODES.put("P8J", new ModelInfo("Mustang", "Mach 1", "Coupe", "RWD", "2"));
        MODEL_CODES.put("P8K", new ModelInfo("Mustang", "Bullitt", "Coupe", "RWD", "2"));

        // Explorer variants
        MODEL_CODES.put("K8D", new ModelInfo("Explorer", "Base", "SUV", "RWD", "4"));
        MODEL_CODES.put("K8B", new ModelInfo("Explorer", "XLT", "SUV", "RWD", "4"));
        MODEL_CODES.put("K8C", new ModelInfo("Explorer", "XLT", "SUV", "4WD", "4"));
        MODEL_CODES.put("K8F", new ModelInfo("Explorer", "Limited", "SUV", "4WD", "4"));
        MODEL_CODES.put("K8G", new ModelInfo("Explorer", "ST", "SUV", "4WD", "4"));
        MODEL_CODES.put("K8H", new ModelInfo("Explorer", "Platinum", "SUV", "4WD", "4"));

        // Expedition
        MODEL_CODES.put("K1C", new ModelInfo("Expedition", "Base", "SUV", "RWD", "4"));
        MODEL_CODES.put("K1E", new ModelInfo("Expedition", "XLT", "SUV", "4WD", "4"));
        MODEL_CODES.put("K1F", new ModelInfo("Expedition", "Limited", "SUV", "4WD", "4"));
        MODEL_CODES.put("K1G", new ModelInfo("Expedition", "King Ranch", "SUV", "4WD", "4"));
        MODEL_CODES.put("K1H", new ModelInfo("Expedition", "Platinum", "SUV", "4WD", "4"));

        // Escape
        MODEL_CODES.put("K8V", new ModelInfo("Escape", "S", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("K8W", new ModelInfo("Escape", "SE", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("K8X", new ModelInfo("Escape", "SE", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("K8Y", new ModelInfo("Escape", "Titanium", "SUV/Crossover", "AWD", "4"));

        // Edge
        MODEL_CODES.put("K2B", new ModelInfo("Edge", "SE", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("K2C", new ModelInfo("Edge", "SEL", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("K2D", new ModelInfo("Edge", "Titanium", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("K2E", new ModelInfo("Edge", "ST", "SUV/Crossover", "AWD", "4"));

        // Bronco
        MODEL_CODES.put("K4E", new ModelInfo("Bronco", "Base", "SUV", "4WD", "2"));
        MODEL_CODES.put("K4F", new ModelInfo("Bronco", "Base", "SUV", "4WD", "4"));
        MODEL_CODES.put("K4G", new ModelInfo("Bronco", "Big Bend", "SUV", "4WD", "4"));
        MODEL_CODES.put("K4H", new ModelInfo("Bronco", "Black Diamond", "SUV", "4WD", "4"));
        MODEL_CODES.put("K4J", new ModelInfo("Bronco", "Outer Banks", "SUV", "4WD", "4"));
        MODEL_CODES.put("K4K", new ModelInfo("Bronco", "Wildtrak", "SUV", "4WD", "4"));
        MODEL_CODES.put("K4L", new ModelInfo("Bronco", "Badlands", "SUV", "4WD", "4"));

        // Ranger
        MODEL_CODES.put("R1A", new ModelInfo("Ranger", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("R1C", new ModelInfo("Ranger", "SuperCab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("R1E", new ModelInfo("Ranger", "SuperCrew", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("R1X", new ModelInfo("Ranger", "SuperCrew", "Pickup Truck", "4WD", "4"));

        // Focus (discontinued but still relevant for used cars)
        MODEL_CODES.put("P71", new ModelInfo("Focus", "S", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P72", new ModelInfo("Focus", "SE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P73", new ModelInfo("Focus", "SEL", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P74", new ModelInfo("Focus", "Titanium", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P3J", new ModelInfo("Focus", "ST", "Hatchback", "FWD", "4"));
        MODEL_CODES.put("P3K", new ModelInfo("Focus", "RS", "Hatchback", "AWD", "4"));

        // Fusion (discontinued but still relevant)
        MODEL_CODES.put("P3F", new ModelInfo("Fusion", "S", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P3G", new ModelInfo("Fusion", "SE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P3H", new ModelInfo("Fusion", "SEL", "Sedan", "FWD", "4"));
        MODEL_CODES.put("P3J", new ModelInfo("Fusion", "Titanium", "Sedan", "AWD", "4"));
        MODEL_CODES.put("P3L", new ModelInfo("Fusion", "Sport", "Sedan", "AWD", "4"));
        MODEL_CODES.put("P3N", new ModelInfo("Fusion", "Hybrid", "Sedan/Hybrid", "FWD", "4"));
        MODEL_CODES.put("P3P", new ModelInfo("Fusion", "Energi", "Sedan/Plug-in Hybrid", "FWD", "4"));

        // E-Series/Econoline vans
        MODEL_CODES.put("E11", new ModelInfo("E-150", "Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("E14", new ModelInfo("E-150", "Passenger Van", "Van", "RWD", "4"));
        MODEL_CODES.put("E24", new ModelInfo("E-250", "Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("E31", new ModelInfo("E-350", "Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("E34", new ModelInfo("E-350", "Passenger Van", "Van", "RWD", "4"));

        // Transit
        MODEL_CODES.put("T11", new ModelInfo("Transit", "150 Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("T15", new ModelInfo("Transit", "250 Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("T35", new ModelInfo("Transit", "350 Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("T3H", new ModelInfo("Transit", "350HD Cargo Van", "Van", "RWD", "3"));
        MODEL_CODES.put("T14", new ModelInfo("Transit", "150 Passenger Van", "Van", "RWD", "4"));
        MODEL_CODES.put("T34", new ModelInfo("Transit", "350 Passenger Van", "Van", "RWD", "4"));
    }

    private static void initializeEngineCodes() {
        // Position 8 engine codes - Passenger Cars
        ENGINE_CODES.put("1", "2.0L EcoBoost I4");
        ENGINE_CODES.put("2", "2.3L EcoBoost I4");
        ENGINE_CODES.put("3", "3.0L Duratec V6");
        ENGINE_CODES.put("4", "2.5L Duratec I4");
        ENGINE_CODES.put("5", "1.5L EcoBoost I3");
        ENGINE_CODES.put("6", "1.6L EcoBoost I4");
        ENGINE_CODES.put("7", "3.5L EcoBoost V6");
        ENGINE_CODES.put("8", "5.2L Voodoo V8");
        ENGINE_CODES.put("9", "1.0L EcoBoost I3");
        ENGINE_CODES.put("H", "5.2L Predator V8");  // GT500
        ENGINE_CODES.put("Z", "2.0L Ti-VCT I4");

        // Light Truck/SUV engine codes
        ENGINE_CODES.put("B", "3.5L PowerBoost Hybrid V6");
        ENGINE_CODES.put("C", "3.7L Ti-VCT V6");
        ENGINE_CODES.put("D", "3.5L PowerBoost Full Hybrid V6");
        ENGINE_CODES.put("E", "6.0L Power Stroke Diesel V8");
        ENGINE_CODES.put("F", "5.0L Coyote V8");
        ENGINE_CODES.put("G", "3.5L EcoBoost V6");
        ENGINE_CODES.put("J", "3.3L Ti-VCT V6");
        ENGINE_CODES.put("K", "5.2L Supercharged V8");
        ENGINE_CODES.put("L", "5.4L Triton V8");
        ENGINE_CODES.put("M", "2.7L EcoBoost V6");
        ENGINE_CODES.put("N", "5.0L Ti-VCT V8");
        ENGINE_CODES.put("P", "3.0L Power Stroke Diesel V6");
        ENGINE_CODES.put("S", "6.2L Boss V8");
        ENGINE_CODES.put("T", "3.5L EcoBoost HO V6");  // Raptor
        ENGINE_CODES.put("U", "2.3L EcoBoost I4");
        ENGINE_CODES.put("V", "6.0L Vortec V8");
        ENGINE_CODES.put("W", "4.6L 2V V8");
        ENGINE_CODES.put("X", "2.7L EcoBoost V6");
        ENGINE_CODES.put("Y", "3.3L Ti-VCT V6");

        // Heavy Duty Truck Engines
        ENGINE_CODES.put("A", "7.3L Godzilla V8");
        ENGINE_CODES.put("R", "6.4L Power Stroke Diesel V8");
        ENGINE_CODES.put("0", "6.7L Power Stroke Diesel V8");
        ENGINE_CODES.put("Q", "6.8L Triton V10");
    }

    private static void initializeGVWRCodes() {
        // Position 4 GVWR/Safety codes for trucks
        GVWR_CODES.put("A", "Under 3,000 lbs");
        GVWR_CODES.put("B", "3,001-4,000 lbs");
        GVWR_CODES.put("C", "4,001-5,000 lbs");
        GVWR_CODES.put("D", "5,001-6,000 lbs");
        GVWR_CODES.put("E", "6,001-7,000 lbs");
        GVWR_CODES.put("F", "7,001-8,000 lbs");
        GVWR_CODES.put("G", "8,001-8,500 lbs");
        GVWR_CODES.put("H", "8,501-9,000 lbs");
        GVWR_CODES.put("J", "9,001-10,000 lbs");
        GVWR_CODES.put("K", "10,001-14,000 lbs");
        GVWR_CODES.put("L", "14,001-16,000 lbs");
        GVWR_CODES.put("M", "16,001-19,500 lbs");
        GVWR_CODES.put("N", "19,501-26,000 lbs");
        GVWR_CODES.put("P", "26,001-33,000 lbs");
        GVWR_CODES.put("R", "Over 33,000 lbs");
    }

    /**
     * Decode Ford specific information from VIN
     */
    public static VehicleInfo decode(String vin) {
        if (vin == null || vin.length() < 17) {
            return null;
        }

        VehicleInfo info = new VehicleInfo();

        // Extract model code (positions 5-7)
        String modelCode = vin.substring(4, 7).toUpperCase();
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
        info.engineDescription = ENGINE_CODES.get(engineCode.toUpperCase());

        // Extract GVWR for trucks (position 4)
        if (modelInfo != null && modelInfo.bodyClass != null &&
            (modelInfo.bodyClass.contains("Truck") || modelInfo.bodyClass.contains("Van"))) {
            String gvwrCode = String.valueOf(vin.charAt(3));
            String gvwrRange = GVWR_CODES.get(gvwrCode.toUpperCase());
            if (gvwrRange != null) {
                info.gvwr = gvwrRange;
            }
        }

        // Determine transmission based on model and engine
        if (info.model != null && info.engineDescription != null) {
            if (info.model.contains("F-150") || info.model.contains("F-250") || info.model.contains("F-350")) {
                info.transmissionStyle = "Automatic";
                info.transmissionSpeeds = "10";  // Most modern F-Series have 10-speed
                if (info.engineDescription.contains("Diesel")) {
                    info.transmissionSpeeds = "10";  // TorqShift 10-speed
                }
            } else if (info.model.contains("Mustang")) {
                // Mustang can have manual or automatic
                if (info.series != null && (info.series.contains("GT350") || info.series.contains("GT500"))) {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/10";  // TREMEC 6-speed manual or 10-speed auto
                } else if (engineCode.equals("2") || engineCode.equals("F")) {  // EcoBoost or V8
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/10";
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "10";
                }
            } else if (info.model.contains("Explorer") || info.model.contains("Edge") || info.model.contains("Escape")) {
                info.transmissionStyle = "Automatic";
                if (info.model.contains("Escape")) {
                    info.transmissionSpeeds = "8";  // 8-speed on newer models
                } else {
                    info.transmissionSpeeds = "10";  // 10-speed on Explorer/Edge
                }
            } else if (info.model.contains("Bronco")) {
                if (engineCode.equals("U")) {  // 2.3L EcoBoost
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "7/10";  // 7-speed manual or 10-speed auto
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "10";
                }
            } else if (info.model.contains("Transit")) {
                info.transmissionStyle = "Automatic";
                info.transmissionSpeeds = "10";  // 10-speed on newer Transits
            }
        }

        // Set manufacturer info
        info.manufacturer = "Ford";
        info.manufacturerName = "Ford Motor Company";

        // Determine plant location based on position 11
        char plantCode = vin.charAt(10);
        switch (plantCode) {
            case 'A':
                info.plantCity = "Wayne";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'B':
                info.plantCity = "Oakville";
                info.plantState = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'C':
                info.plantCity = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'D':
            case 'E':
                info.plantCity = "Dearborn";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'F':
                info.plantCity = "Flat Rock";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'G':
                info.plantCity = "Chicago";
                info.plantState = "Illinois";
                info.plantCountry = "United States";
                break;
            case 'H':
                info.plantCity = "Lorain";
                info.plantState = "Ohio";
                info.plantCountry = "United States";
                break;
            case 'J':
                info.plantCity = "Claycomo";
                info.plantState = "Missouri";
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
            case 'M':
                info.plantCity = "Wayne";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'N':
                info.plantCity = "Norfolk";
                info.plantState = "Virginia";
                info.plantCountry = "United States";
                break;
            case 'P':
                info.plantCity = "Twin Cities";
                info.plantState = "Minnesota";
                info.plantCountry = "United States";
                break;
            case 'R':
                info.plantCity = "Hermosillo";
                info.plantCountry = "Mexico";
                break;
            case 'S':
                info.plantCity = "Allen Park";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'T':
                info.plantCity = "Edison";
                info.plantState = "New Jersey";
                info.plantCountry = "United States";
                break;
            case 'U':
                info.plantCity = "Louisville";
                info.plantState = "Kentucky";
                info.plantCountry = "United States";
                break;
            case 'W':
                info.plantCity = "Wayne";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'X':
                info.plantCity = "St. Thomas";
                info.plantState = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'Y':
                info.plantCity = "Wixom";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'Z':
                info.plantCity = "Hazelwood";
                info.plantState = "Missouri";
                info.plantCountry = "United States";
                break;
        }

        // Add weight estimates based on model
        if (info.model != null) {
            if (info.model.contains("F-150")) {
                info.curbWeight = "4500-5000";
            } else if (info.model.contains("F-250")) {
                info.curbWeight = "5500-6500";
            } else if (info.model.contains("F-350")) {
                info.curbWeight = "6500-7500";
            } else if (info.model.contains("Explorer")) {
                info.curbWeight = "4400-4900";
            } else if (info.model.contains("Mustang")) {
                info.curbWeight = "3500-3900";
            } else if (info.model.contains("Escape")) {
                info.curbWeight = "3300-3600";
            } else if (info.model.contains("Bronco")) {
                info.curbWeight = "4300-4800";
            } else if (info.model.contains("Transit")) {
                info.curbWeight = "4600-5200";
            }
        }

        return info;
    }

    /**
     * Vehicle information structure
     */
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
