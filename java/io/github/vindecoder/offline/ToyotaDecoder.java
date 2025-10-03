package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

/**
 * Toyota VIN Decoder
 *
 * Decodes Toyota/Lexus specific VIN patterns to extract model, engine, and body information
 * Based on Toyota VIN structure
 *
 * @author Wal33D
 */
public class ToyotaDecoder {

    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();
    private static final Map<String, String> SERIES_CODES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
        initializeSeriesCodes();
    }

    public static class ModelInfo {
        public String make;
        public String model;
        public String series;
        public String bodyClass;
        public String driveType;
        public String doors;

        public ModelInfo(String make, String model, String series, String bodyClass, String driveType, String doors) {
            this.make = make;
            this.model = model;
            this.series = series;
            this.bodyClass = bodyClass;
            this.driveType = driveType;
            this.doors = doors;
        }
    }

    private static void initializeModelCodes() {
        // Toyota model codes based on position 8 (model/platform)
        // Combined with series codes for full identification

        // Camry variants
        MODEL_CODES.put("K1", new ModelInfo("Toyota", "Camry", "L", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K2", new ModelInfo("Toyota", "Camry", "LE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K3", new ModelInfo("Toyota", "Camry", "SE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K4", new ModelInfo("Toyota", "Camry", "XLE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K5", new ModelInfo("Toyota", "Camry", "XSE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K6", new ModelInfo("Toyota", "Camry", "TRD", "Sedan", "FWD", "4"));
        MODEL_CODES.put("K7", new ModelInfo("Toyota", "Camry", "Hybrid LE", "Sedan/Hybrid", "FWD", "4"));
        MODEL_CODES.put("K8", new ModelInfo("Toyota", "Camry", "Hybrid SE", "Sedan/Hybrid", "FWD", "4"));
        MODEL_CODES.put("K9", new ModelInfo("Toyota", "Camry", "Hybrid XLE", "Sedan/Hybrid", "FWD", "4"));

        // Corolla variants
        MODEL_CODES.put("E1", new ModelInfo("Toyota", "Corolla", "L", "Sedan", "FWD", "4"));
        MODEL_CODES.put("E2", new ModelInfo("Toyota", "Corolla", "LE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("E3", new ModelInfo("Toyota", "Corolla", "SE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("E4", new ModelInfo("Toyota", "Corolla", "XLE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("E5", new ModelInfo("Toyota", "Corolla", "XSE", "Sedan", "FWD", "4"));
        MODEL_CODES.put("E6", new ModelInfo("Toyota", "Corolla", "Hatchback SE", "Hatchback", "FWD", "4"));
        MODEL_CODES.put("E7", new ModelInfo("Toyota", "Corolla", "Hatchback XSE", "Hatchback", "FWD", "4"));
        MODEL_CODES.put("E8", new ModelInfo("Toyota", "Corolla", "Hybrid LE", "Sedan/Hybrid", "FWD", "4"));
        MODEL_CODES.put("E9", new ModelInfo("Toyota", "Corolla", "GR Corolla", "Hatchback", "AWD", "4"));

        // RAV4 variants
        MODEL_CODES.put("V1", new ModelInfo("Toyota", "RAV4", "LE", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("V2", new ModelInfo("Toyota", "RAV4", "LE", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V3", new ModelInfo("Toyota", "RAV4", "XLE", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("V4", new ModelInfo("Toyota", "RAV4", "XLE", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V5", new ModelInfo("Toyota", "RAV4", "XLE Premium", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V6", new ModelInfo("Toyota", "RAV4", "Adventure", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V7", new ModelInfo("Toyota", "RAV4", "TRD Off-Road", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V8", new ModelInfo("Toyota", "RAV4", "Limited", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("V9", new ModelInfo("Toyota", "RAV4", "Hybrid", "SUV/Crossover/Hybrid", "AWD", "4"));
        MODEL_CODES.put("VA", new ModelInfo("Toyota", "RAV4", "Prime", "SUV/Crossover/Plug-in Hybrid", "AWD", "4"));

        // Highlander variants
        MODEL_CODES.put("U1", new ModelInfo("Toyota", "Highlander", "L", "SUV", "FWD", "4"));
        MODEL_CODES.put("U2", new ModelInfo("Toyota", "Highlander", "LE", "SUV", "FWD", "4"));
        MODEL_CODES.put("U3", new ModelInfo("Toyota", "Highlander", "LE", "SUV", "AWD", "4"));
        MODEL_CODES.put("U4", new ModelInfo("Toyota", "Highlander", "XLE", "SUV", "AWD", "4"));
        MODEL_CODES.put("U5", new ModelInfo("Toyota", "Highlander", "Limited", "SUV", "AWD", "4"));
        MODEL_CODES.put("U6", new ModelInfo("Toyota", "Highlander", "Platinum", "SUV", "AWD", "4"));
        MODEL_CODES.put("U7", new ModelInfo("Toyota", "Highlander", "Hybrid LE", "SUV/Hybrid", "AWD", "4"));
        MODEL_CODES.put("U8", new ModelInfo("Toyota", "Highlander", "Hybrid XLE", "SUV/Hybrid", "AWD", "4"));
        MODEL_CODES.put("U9", new ModelInfo("Toyota", "Highlander", "Hybrid Limited", "SUV/Hybrid", "AWD", "4"));
        MODEL_CODES.put("UA", new ModelInfo("Toyota", "Highlander", "Hybrid Platinum", "SUV/Hybrid", "AWD", "4"));

        // 4Runner
        MODEL_CODES.put("N1", new ModelInfo("Toyota", "4Runner", "SR5", "SUV", "RWD", "4"));
        MODEL_CODES.put("N2", new ModelInfo("Toyota", "4Runner", "SR5", "SUV", "4WD", "4"));
        MODEL_CODES.put("N3", new ModelInfo("Toyota", "4Runner", "SR5 Premium", "SUV", "4WD", "4"));
        MODEL_CODES.put("N4", new ModelInfo("Toyota", "4Runner", "TRD Off-Road", "SUV", "4WD", "4"));
        MODEL_CODES.put("N5", new ModelInfo("Toyota", "4Runner", "TRD Off-Road Premium", "SUV", "4WD", "4"));
        MODEL_CODES.put("N6", new ModelInfo("Toyota", "4Runner", "Limited", "SUV", "4WD", "4"));
        MODEL_CODES.put("N7", new ModelInfo("Toyota", "4Runner", "TRD Pro", "SUV", "4WD", "4"));

        // Tacoma
        MODEL_CODES.put("M1", new ModelInfo("Toyota", "Tacoma", "SR", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("M2", new ModelInfo("Toyota", "Tacoma", "SR", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("M3", new ModelInfo("Toyota", "Tacoma", "SR5", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("M4", new ModelInfo("Toyota", "Tacoma", "SR5", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("M5", new ModelInfo("Toyota", "Tacoma", "TRD Sport", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("M6", new ModelInfo("Toyota", "Tacoma", "TRD Sport", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("M7", new ModelInfo("Toyota", "Tacoma", "TRD Off-Road", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("M8", new ModelInfo("Toyota", "Tacoma", "Limited", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("M9", new ModelInfo("Toyota", "Tacoma", "TRD Pro", "Pickup Truck", "4WD", "4"));

        // Tundra
        MODEL_CODES.put("Y1", new ModelInfo("Toyota", "Tundra", "SR", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("Y2", new ModelInfo("Toyota", "Tundra", "SR", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("Y3", new ModelInfo("Toyota", "Tundra", "SR5", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("Y4", new ModelInfo("Toyota", "Tundra", "SR5", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("Y5", new ModelInfo("Toyota", "Tundra", "Limited", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("Y6", new ModelInfo("Toyota", "Tundra", "Platinum", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("Y7", new ModelInfo("Toyota", "Tundra", "1794 Edition", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("Y8", new ModelInfo("Toyota", "Tundra", "TRD Pro", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("Y9", new ModelInfo("Toyota", "Tundra", "Capstone", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("YA", new ModelInfo("Toyota", "Tundra", "Hybrid", "Pickup Truck/Hybrid", "4WD", "4"));

        // Prius
        MODEL_CODES.put("P1", new ModelInfo("Toyota", "Prius", "L Eco", "Hatchback/Hybrid", "FWD", "4"));
        MODEL_CODES.put("P2", new ModelInfo("Toyota", "Prius", "LE", "Hatchback/Hybrid", "FWD", "4"));
        MODEL_CODES.put("P3", new ModelInfo("Toyota", "Prius", "XLE", "Hatchback/Hybrid", "FWD", "4"));
        MODEL_CODES.put("P4", new ModelInfo("Toyota", "Prius", "Limited", "Hatchback/Hybrid", "FWD", "4"));
        MODEL_CODES.put("P5", new ModelInfo("Toyota", "Prius", "LE AWD", "Hatchback/Hybrid", "AWD", "4"));
        MODEL_CODES.put("P6", new ModelInfo("Toyota", "Prius", "XLE AWD", "Hatchback/Hybrid", "AWD", "4"));
        MODEL_CODES.put("P7", new ModelInfo("Toyota", "Prius Prime", "LE", "Hatchback/Plug-in Hybrid", "FWD", "4"));
        MODEL_CODES.put("P8", new ModelInfo("Toyota", "Prius Prime", "XLE", "Hatchback/Plug-in Hybrid", "FWD", "4"));
        MODEL_CODES.put("P9", new ModelInfo("Toyota", "Prius Prime", "Limited", "Hatchback/Plug-in Hybrid", "FWD", "4"));

        // Sienna
        MODEL_CODES.put("L1", new ModelInfo("Toyota", "Sienna", "LE", "Minivan", "FWD", "4"));
        MODEL_CODES.put("L2", new ModelInfo("Toyota", "Sienna", "LE", "Minivan", "AWD", "4"));
        MODEL_CODES.put("L3", new ModelInfo("Toyota", "Sienna", "XLE", "Minivan", "FWD", "4"));
        MODEL_CODES.put("L4", new ModelInfo("Toyota", "Sienna", "XLE", "Minivan", "AWD", "4"));
        MODEL_CODES.put("L5", new ModelInfo("Toyota", "Sienna", "XSE", "Minivan", "FWD", "4"));
        MODEL_CODES.put("L6", new ModelInfo("Toyota", "Sienna", "Limited", "Minivan", "FWD", "4"));
        MODEL_CODES.put("L7", new ModelInfo("Toyota", "Sienna", "Limited", "Minivan", "AWD", "4"));
        MODEL_CODES.put("L8", new ModelInfo("Toyota", "Sienna", "Platinum", "Minivan", "AWD", "4"));

        // Supra
        MODEL_CODES.put("DB1", new ModelInfo("Toyota", "Supra", "2.0", "Coupe", "RWD", "2"));
        MODEL_CODES.put("DB2", new ModelInfo("Toyota", "Supra", "3.0", "Coupe", "RWD", "2"));
        MODEL_CODES.put("DB3", new ModelInfo("Toyota", "Supra", "3.0 Premium", "Coupe", "RWD", "2"));
        MODEL_CODES.put("DB4", new ModelInfo("Toyota", "Supra", "A91-MT Edition", "Coupe", "RWD", "2"));

        // GR86
        MODEL_CODES.put("J1", new ModelInfo("Toyota", "GR86", "Base", "Coupe", "RWD", "2"));
        MODEL_CODES.put("J2", new ModelInfo("Toyota", "GR86", "Premium", "Coupe", "RWD", "2"));

        // Lexus models
        MODEL_CODES.put("GS1", new ModelInfo("Lexus", "IS", "250", "Sedan", "RWD", "4"));
        MODEL_CODES.put("GS2", new ModelInfo("Lexus", "IS", "300", "Sedan", "RWD", "4"));
        MODEL_CODES.put("GS3", new ModelInfo("Lexus", "IS", "350", "Sedan", "RWD", "4"));
        MODEL_CODES.put("GS4", new ModelInfo("Lexus", "IS", "350 F Sport", "Sedan", "AWD", "4"));
        MODEL_CODES.put("GS5", new ModelInfo("Lexus", "IS", "500 F Sport", "Sedan", "RWD", "4"));

        MODEL_CODES.put("ES1", new ModelInfo("Lexus", "ES", "250", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ES2", new ModelInfo("Lexus", "ES", "300h", "Sedan/Hybrid", "FWD", "4"));
        MODEL_CODES.put("ES3", new ModelInfo("Lexus", "ES", "350", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ES4", new ModelInfo("Lexus", "ES", "350 F Sport", "Sedan", "FWD", "4"));

        MODEL_CODES.put("RX1", new ModelInfo("Lexus", "RX", "350", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RX2", new ModelInfo("Lexus", "RX", "350", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RX3", new ModelInfo("Lexus", "RX", "350 F Sport", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RX4", new ModelInfo("Lexus", "RX", "450h", "SUV/Crossover/Hybrid", "AWD", "4"));
        MODEL_CODES.put("RX5", new ModelInfo("Lexus", "RX", "450h+", "SUV/Crossover/Plug-in Hybrid", "AWD", "4"));
        MODEL_CODES.put("RX6", new ModelInfo("Lexus", "RX", "500h F Sport", "SUV/Crossover/Hybrid", "AWD", "4"));

        MODEL_CODES.put("NX1", new ModelInfo("Lexus", "NX", "250", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("NX2", new ModelInfo("Lexus", "NX", "350", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("NX3", new ModelInfo("Lexus", "NX", "350h", "SUV/Crossover/Hybrid", "AWD", "4"));
        MODEL_CODES.put("NX4", new ModelInfo("Lexus", "NX", "450h+", "SUV/Crossover/Plug-in Hybrid", "AWD", "4"));

        MODEL_CODES.put("GX1", new ModelInfo("Lexus", "GX", "460", "SUV", "4WD", "4"));
        MODEL_CODES.put("GX2", new ModelInfo("Lexus", "GX", "460 Premium", "SUV", "4WD", "4"));
        MODEL_CODES.put("GX3", new ModelInfo("Lexus", "GX", "460 Luxury", "SUV", "4WD", "4"));
        MODEL_CODES.put("GX4", new ModelInfo("Lexus", "GX", "550", "SUV", "4WD", "4"));
        MODEL_CODES.put("GX5", new ModelInfo("Lexus", "GX", "550 Overtrail", "SUV", "4WD", "4"));

        MODEL_CODES.put("LX1", new ModelInfo("Lexus", "LX", "570", "SUV", "4WD", "4"));
        MODEL_CODES.put("LX2", new ModelInfo("Lexus", "LX", "600", "SUV", "4WD", "4"));
    }

    private static void initializeEngineCodes() {
        // Position 5 engine codes for Toyota
        // 4-Cylinder
        ENGINE_CODES.put("R", "1.8L I4 (1ZZ-FE/2ZR-FE)");  // Corolla
        ENGINE_CODES.put("K", "2.4L I4 (2AZ-FE)");        // Old Camry/RAV4
        ENGINE_CODES.put("F", "2.5L I4 (2AR-FE/A25A-FKS)"); // Current Camry/RAV4
        ENGINE_CODES.put("A", "2.0L I4 (3ZR-FAE/M20A-FKS)"); // Corolla
        ENGINE_CODES.put("B", "2.0L Turbo I4");            // Supra 2.0
        ENGINE_CODES.put("U", "2.4L Turbo I4");            // Highlander/Tacoma

        // 6-Cylinder
        ENGINE_CODES.put("G", "3.5L V6 (2GR-FE/2GR-FKS)"); // Tacoma/Highlander/Camry V6
        ENGINE_CODES.put("E", "3.5L V6 (2GR-FXS)");       // Hybrid systems
        ENGINE_CODES.put("D", "3.0L I6 Turbo");           // Supra 3.0
        ENGINE_CODES.put("T", "3.4L V6 Twin-Turbo");      // Tundra/Sequoia
        ENGINE_CODES.put("M", "3.5L V6 Twin-Turbo");      // LS 500/Land Cruiser

        // 8-Cylinder
        ENGINE_CODES.put("Y", "5.7L V8 (3UR-FE)");        // Tundra/Sequoia/Land Cruiser
        ENGINE_CODES.put("J", "4.6L V8 (1UR-FE)");        // GX 460/Land Cruiser
        ENGINE_CODES.put("S", "5.0L V8 (2UR-GSE)");       // Lexus F models

        // Hybrid codes
        ENGINE_CODES.put("N", "2.5L I4 Hybrid (A25A-FXS)");
        ENGINE_CODES.put("H", "1.8L I4 Hybrid (2ZR-FXE)");
        ENGINE_CODES.put("P", "1.5L I3 Hybrid");
        ENGINE_CODES.put("X", "2.5L I4 Plug-in Hybrid");
        ENGINE_CODES.put("Z", "2.4L I4 Hybrid");

        // Diesel
        ENGINE_CODES.put("L", "2.8L Turbo Diesel I4");
        ENGINE_CODES.put("V", "4.5L Turbo Diesel V8");
    }

    private static void initializeSeriesCodes() {
        // Position 6 series/grade codes
        SERIES_CODES.put("1", "Base/L");
        SERIES_CODES.put("2", "LE/S");
        SERIES_CODES.put("3", "SE/SR");
        SERIES_CODES.put("4", "XLE/SL");
        SERIES_CODES.put("5", "XSE/SR5");
        SERIES_CODES.put("6", "Limited");
        SERIES_CODES.put("7", "Platinum/TRD Sport");
        SERIES_CODES.put("8", "TRD Off-Road");
        SERIES_CODES.put("9", "TRD Pro");
        SERIES_CODES.put("A", "Adventure");
        SERIES_CODES.put("B", "Nightshade");
        SERIES_CODES.put("C", "Hybrid");
        SERIES_CODES.put("D", "Prime/Plug-in Hybrid");
    }

    /**
     * Decode Toyota specific information from VIN
     */
    public static VehicleInfo decode(String vin) {
        if (vin == null || vin.length() < 17) {
            return null;
        }

        VehicleInfo info = new VehicleInfo();

        // Determine make from WMI
        String wmi = vin.substring(0, 3).toUpperCase();
        if (wmi.startsWith("JT") || wmi.startsWith("4T") || wmi.startsWith("5T")) {
            info.make = "Toyota";
        } else if (wmi.startsWith("JTH") || wmi.startsWith("JTJ") || wmi.startsWith("2T")) {
            info.make = "Lexus";
        } else if (wmi.equals("JF1") || wmi.equals("JF2") || wmi.equals("4S")) {
            info.make = "Subaru";  // Sometimes Toyota sells Subaru models
        }

        // Extract engine code (position 5 for Toyota)
        String engineCode = String.valueOf(vin.charAt(4));
        info.engineDescription = ENGINE_CODES.get(engineCode.toUpperCase());

        // Extract series code (position 6)
        String seriesCode = String.valueOf(vin.charAt(5));
        String series = SERIES_CODES.get(seriesCode.toUpperCase());

        // Extract model/platform code (position 8)
        char modelChar = vin.charAt(7);
        String modelCode = String.valueOf(modelChar).toUpperCase();

        // Try to match with series for more specific model
        String combinedCode = modelCode + seriesCode;
        ModelInfo modelInfo = MODEL_CODES.get(combinedCode);

        if (modelInfo == null) {
            // Try just model code
            modelInfo = MODEL_CODES.get(modelCode + "1");  // Try with base trim
        }

        if (modelInfo == null) {
            // Fallback to basic model identification
            switch (modelChar) {
                case 'E':
                    modelInfo = new ModelInfo("Toyota", "Corolla", series, "Sedan", "FWD", "4");
                    break;
                case 'K':
                    modelInfo = new ModelInfo("Toyota", "Camry", series, "Sedan", "FWD", "4");
                    break;
                case 'V':
                    modelInfo = new ModelInfo("Toyota", "RAV4", series, "SUV/Crossover", "AWD", "4");
                    break;
                case 'U':
                    modelInfo = new ModelInfo("Toyota", "Highlander", series, "SUV", "AWD", "4");
                    break;
                case 'N':
                    modelInfo = new ModelInfo("Toyota", "4Runner", series, "SUV", "4WD", "4");
                    break;
                case 'M':
                    modelInfo = new ModelInfo("Toyota", "Tacoma", series, "Pickup Truck", "4WD", "4");
                    break;
                case 'Y':
                    modelInfo = new ModelInfo("Toyota", "Tundra", series, "Pickup Truck", "4WD", "4");
                    break;
                case 'P':
                    modelInfo = new ModelInfo("Toyota", "Prius", series, "Hatchback/Hybrid", "FWD", "4");
                    break;
                case 'L':
                    modelInfo = new ModelInfo("Toyota", "Sienna", series, "Minivan", "FWD", "4");
                    break;
                case 'B':
                    modelInfo = new ModelInfo("Toyota", "Avalon", series, "Sedan", "FWD", "4");
                    break;
                case 'D':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "GS", series, "Sedan", "RWD", "4");
                    }
                    break;
                case 'A':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "ES", series, "Sedan", "FWD", "4");
                    }
                    break;
                case 'H':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "LS", series, "Sedan", "RWD", "4");
                    }
                    break;
                case 'J':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "GX", series, "SUV", "4WD", "4");
                    }
                    break;
                case 'T':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "RX", series, "SUV/Crossover", "AWD", "4");
                    }
                    break;
                case 'Z':
                    if (info.make != null && info.make.equals("Lexus")) {
                        modelInfo = new ModelInfo("Lexus", "NX", series, "SUV/Crossover", "AWD", "4");
                    }
                    break;
            }
        }

        if (modelInfo != null) {
            if (modelInfo.make != null) info.make = modelInfo.make;
            info.model = modelInfo.model;
            info.series = modelInfo.series != null ? modelInfo.series : series;
            info.bodyClass = modelInfo.bodyClass;
            info.driveType = modelInfo.driveType;
            info.doors = modelInfo.doors;
        }

        // Determine transmission
        if (info.model != null && info.engineDescription != null) {
            if (info.model.contains("Supra") || info.model.contains("GR86")) {
                if (info.model.contains("MT Edition")) {
                    info.transmissionStyle = "Manual";
                    info.transmissionSpeeds = "6";
                } else {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/8";  // 6-speed manual or 8-speed auto
                }
            } else if (info.model.contains("Tacoma")) {
                if (engineCode.equals("G")) {  // V6
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6";
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "6";
                }
            } else if (info.model.contains("Tundra")) {
                info.transmissionStyle = "Automatic";
                if (info.engineDescription != null && info.engineDescription.contains("Twin-Turbo")) {
                    info.transmissionSpeeds = "10";  // New Tundra with 10-speed
                } else {
                    info.transmissionSpeeds = "6";  // Older Tundra
                }
            } else if (info.engineDescription != null && info.engineDescription.contains("Hybrid")) {
                info.transmissionStyle = "CVT";
                info.transmissionSpeeds = "Electronic CVT";
            } else if (info.model.contains("Corolla") && info.model.contains("GR")) {
                info.transmissionStyle = "Manual";
                info.transmissionSpeeds = "6";
            } else if (info.model.contains("Camry") || info.model.contains("RAV4") ||
                      info.model.contains("Highlander") || info.model.contains("Corolla")) {
                info.transmissionStyle = "Automatic";
                if (engineCode.equals("F") || engineCode.equals("G")) {  // 2.5L or V6
                    info.transmissionSpeeds = "8";
                } else {
                    info.transmissionStyle = "CVT";
                    info.transmissionSpeeds = "CVT";
                }
            } else if (info.make != null && info.make.equals("Lexus")) {
                info.transmissionStyle = "Automatic";
                if (info.model != null && (info.model.contains("LS") || info.model.contains("LC"))) {
                    info.transmissionSpeeds = "10";
                } else {
                    info.transmissionSpeeds = "8";
                }
            } else {
                // Default sensible automatic transmission for recent Toyota/Lexus
                info.transmissionStyle = "Automatic";
                info.transmissionSpeeds = "8";
            }
        }

        // Set manufacturer info
        info.manufacturer = "Toyota";
        info.manufacturerName = info.make != null ? "Toyota Motor Corporation (" + info.make + ")" : "Toyota Motor Corporation";

        // Determine plant location based on position 11
        char plantCode = vin.charAt(10);
        switch (plantCode) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
                info.plantCity = "Toyota City";
                info.plantCountry = "Japan";
                break;
            case 'A':
            case 'B':
                info.plantCity = "Aichi";
                info.plantCountry = "Japan";
                break;
            case 'C':
                info.plantCity = "Cambridge";
                info.plantState = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'D':
                info.plantCity = "Derbyshire";
                info.plantCountry = "United Kingdom";
                break;
            case 'E':
            case 'F':
                info.plantCity = "Fukuoka";
                info.plantCountry = "Japan";
                break;
            case 'G':
            case 'H':
                info.plantCity = "Hamura";
                info.plantCountry = "Japan";
                break;
            case 'J':
            case 'K':
                info.plantCity = "Kyushu";
                info.plantCountry = "Japan";
                break;
            case 'M':
                info.plantCity = "Miyagi";
                info.plantCountry = "Japan";
                break;
            case 'N':
                info.plantCity = "Nagoya";
                info.plantCountry = "Japan";
                break;
            case 'P':
                info.plantCity = "Princeton";
                info.plantState = "Indiana";
                info.plantCountry = "United States";
                break;
            case 'R':
                info.plantCity = "Lafayette";
                info.plantState = "Indiana";
                info.plantCountry = "United States";
                break;
            case 'S':
                info.plantCity = "Princeton";
                info.plantState = "Indiana";
                info.plantCountry = "United States";
                break;
            case 'T':
                info.plantCity = "Tahara";
                info.plantCountry = "Japan";
                break;
            case 'U':
                info.plantCity = "Georgetown";
                info.plantState = "Kentucky";
                info.plantCountry = "United States";
                break;
            case 'V':
                info.plantCity = "Valenciennes";
                info.plantCountry = "France";
                break;
            case 'W':
                info.plantCity = "Woodstock";
                info.plantState = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'X':
                info.plantCity = "San Antonio";
                info.plantState = "Texas";
                info.plantCountry = "United States";
                break;
            case 'Y':
                info.plantCity = "Onnaing";
                info.plantCountry = "France";
                break;
            case 'Z':
                info.plantCity = "Fremont";
                info.plantState = "California";
                info.plantCountry = "United States";
                break;
        }

        // Add weight estimates based on model
        if (info.model != null) {
            if (info.model.contains("Corolla")) {
                info.curbWeight = "2800-3150";
            } else if (info.model.contains("Camry")) {
                info.curbWeight = "3300-3600";
            } else if (info.model.contains("RAV4")) {
                info.curbWeight = "3300-3700";
            } else if (info.model.contains("Highlander")) {
                info.curbWeight = "4100-4500";
            } else if (info.model.contains("4Runner")) {
                info.curbWeight = "4400-4700";
            } else if (info.model.contains("Tacoma")) {
                info.curbWeight = "3900-4500";
            } else if (info.model.contains("Tundra")) {
                info.curbWeight = "5200-5800";
            } else if (info.model.contains("Prius")) {
                info.curbWeight = "3000-3200";
            } else if (info.model.contains("Sienna")) {
                info.curbWeight = "4600-4900";
            } else if (info.model.contains("Supra")) {
                info.curbWeight = "3200-3400";
            } else if (info.model.contains("GR86")) {
                info.curbWeight = "2800-2900";
            } else if (info.make != null && info.make.equals("Lexus")) {
                if (info.model != null) {
                    if (info.model.contains("IS")) {
                        info.curbWeight = "3500-3900";
                    } else if (info.model.contains("ES")) {
                        info.curbWeight = "3600-3900";
                    } else if (info.model.contains("RX")) {
                        info.curbWeight = "4200-4700";
                    } else if (info.model.contains("NX")) {
                        info.curbWeight = "3900-4200";
                    } else if (info.model.contains("GX")) {
                        info.curbWeight = "5100-5400";
                    } else if (info.model.contains("LX")) {
                        info.curbWeight = "5700-6000";
                    }
                }
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
        public String make;
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
