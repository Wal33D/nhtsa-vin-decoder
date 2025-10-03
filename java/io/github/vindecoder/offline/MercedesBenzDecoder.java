package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

/**
 * Mercedes-Benz VIN Decoder
 *
 * Decodes Mercedes-Benz specific VIN patterns to extract model, engine, and body information
 * Based on Mercedes-Benz VIN structure and model codes
 */
public class MercedesBenzDecoder {

    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();
    private static final Map<String, String> BODY_STYLES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
        initializeBodyStyles();
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
        // Mercedes-Benz model codes (positions 4-6 of VIN)
        // GLE Class
        MODEL_CODES.put("DA5", new ModelInfo("GLE-Class", "GLE 350", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("DA4", new ModelInfo("GLE-Class", "GLE 350", "Sport Utility Vehicle (SUV)", "RWD", "4"));
        MODEL_CODES.put("DB5", new ModelInfo("GLE-Class", "GLE 450", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("DC5", new ModelInfo("GLE-Class", "AMG GLE 43", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("DD5", new ModelInfo("GLE-Class", "AMG GLE 63", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("DE5", new ModelInfo("GLE-Class", "AMG GLE 63 S", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("DH5", new ModelInfo("GLE-Class", "GLE 550e", "Sport Utility Vehicle (SUV)/Hybrid", "4MATIC", "4"));

        // GLC Class
        MODEL_CODES.put("CF4", new ModelInfo("GLC-Class", "GLC 300", "Sport Utility Vehicle (SUV)", "RWD", "4"));
        MODEL_CODES.put("CF5", new ModelInfo("GLC-Class", "GLC 300", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("CG5", new ModelInfo("GLC-Class", "AMG GLC 43", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("CH5", new ModelInfo("GLC-Class", "AMG GLC 63", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));

        // C-Class
        MODEL_CODES.put("BB4", new ModelInfo("C-Class", "C 300", "Sedan", "RWD", "4"));
        MODEL_CODES.put("BB5", new ModelInfo("C-Class", "C 300", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("BC5", new ModelInfo("C-Class", "AMG C 43", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("BD5", new ModelInfo("C-Class", "AMG C 63", "Sedan", "RWD", "4"));
        MODEL_CODES.put("BE5", new ModelInfo("C-Class", "AMG C 63 S", "Sedan", "RWD", "4"));

        // E-Class
        MODEL_CODES.put("EA4", new ModelInfo("E-Class", "E 300", "Sedan", "RWD", "4"));
        MODEL_CODES.put("EA5", new ModelInfo("E-Class", "E 300", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("EB4", new ModelInfo("E-Class", "E 350", "Sedan", "RWD", "4"));
        MODEL_CODES.put("EB5", new ModelInfo("E-Class", "E 350", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("EC5", new ModelInfo("E-Class", "E 450", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("ED5", new ModelInfo("E-Class", "AMG E 43", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("EE5", new ModelInfo("E-Class", "AMG E 53", "Sedan", "4MATIC", "4"));
        MODEL_CODES.put("EF5", new ModelInfo("E-Class", "AMG E 63 S", "Sedan", "4MATIC", "4"));

        // S-Class
        MODEL_CODES.put("FA4", new ModelInfo("S-Class", "S 450", "Sedan/Luxury", "RWD", "4"));
        MODEL_CODES.put("FA5", new ModelInfo("S-Class", "S 450", "Sedan/Luxury", "4MATIC", "4"));
        MODEL_CODES.put("FB5", new ModelInfo("S-Class", "S 560", "Sedan/Luxury", "4MATIC", "4"));
        MODEL_CODES.put("FC5", new ModelInfo("S-Class", "AMG S 63", "Sedan/Luxury", "4MATIC", "4"));
        MODEL_CODES.put("FD5", new ModelInfo("S-Class", "AMG S 65", "Sedan/Luxury", "RWD", "4"));
        MODEL_CODES.put("FE5", new ModelInfo("S-Class", "Maybach S 560", "Sedan/Luxury", "4MATIC", "4"));
        MODEL_CODES.put("FF5", new ModelInfo("S-Class", "Maybach S 650", "Sedan/Luxury", "RWD", "4"));

        // G-Class
        MODEL_CODES.put("GB5", new ModelInfo("G-Class", "G 550", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("GC5", new ModelInfo("G-Class", "AMG G 63", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));
        MODEL_CODES.put("GD5", new ModelInfo("G-Class", "AMG G 65", "Sport Utility Vehicle (SUV)", "4MATIC", "4"));

        // CLA Class
        MODEL_CODES.put("AA4", new ModelInfo("CLA-Class", "CLA 250", "Coupe", "FWD", "4"));
        MODEL_CODES.put("AA5", new ModelInfo("CLA-Class", "CLA 250", "Coupe", "4MATIC", "4"));
        MODEL_CODES.put("AB5", new ModelInfo("CLA-Class", "AMG CLA 35", "Coupe", "4MATIC", "4"));
        MODEL_CODES.put("AC5", new ModelInfo("CLA-Class", "AMG CLA 45", "Coupe", "4MATIC", "4"));

        // SL Class
        MODEL_CODES.put("KA4", new ModelInfo("SL-Class", "SL 450", "Convertible/Roadster", "RWD", "2"));
        MODEL_CODES.put("KB4", new ModelInfo("SL-Class", "SL 550", "Convertible/Roadster", "RWD", "2"));
        MODEL_CODES.put("KC4", new ModelInfo("SL-Class", "AMG SL 63", "Convertible/Roadster", "RWD", "2"));
        MODEL_CODES.put("KD4", new ModelInfo("SL-Class", "AMG SL 65", "Convertible/Roadster", "RWD", "2"));

        // AMG GT
        MODEL_CODES.put("HA4", new ModelInfo("AMG GT", "AMG GT", "Coupe", "RWD", "2"));
        MODEL_CODES.put("HB4", new ModelInfo("AMG GT", "AMG GT S", "Coupe", "RWD", "2"));
        MODEL_CODES.put("HC4", new ModelInfo("AMG GT", "AMG GT C", "Coupe", "RWD", "2"));
        MODEL_CODES.put("HD4", new ModelInfo("AMG GT", "AMG GT R", "Coupe", "RWD", "2"));

        // EQS (Electric)
        MODEL_CODES.put("VA5", new ModelInfo("EQS", "EQS 450+", "Sedan/Electric", "RWD", "4"));
        MODEL_CODES.put("VB5", new ModelInfo("EQS", "EQS 580", "Sedan/Electric", "4MATIC", "4"));
        MODEL_CODES.put("VC5", new ModelInfo("EQS", "AMG EQS 53", "Sedan/Electric", "4MATIC", "4"));

        // EQE (Electric)
        MODEL_CODES.put("WA5", new ModelInfo("EQE", "EQE 350", "Sedan/Electric", "RWD", "4"));
        MODEL_CODES.put("WB5", new ModelInfo("EQE", "EQE 350", "Sedan/Electric", "4MATIC", "4"));
        MODEL_CODES.put("WC5", new ModelInfo("EQE", "AMG EQE 53", "Sedan/Electric", "4MATIC", "4"));
    }

    private static void initializeEngineCodes() {
        // Engine codes (position 8 of VIN)
        ENGINE_CODES.put("B", "2.0L Turbocharged I4");
        ENGINE_CODES.put("C", "3.0L Turbocharged I6");
        ENGINE_CODES.put("D", "3.0L Twin-Turbo V6");
        ENGINE_CODES.put("E", "3.5L V6");
        ENGINE_CODES.put("F", "4.0L Twin-Turbo V8");
        ENGINE_CODES.put("G", "4.7L Twin-Turbo V8");
        ENGINE_CODES.put("H", "3.0L Turbo I6 + Hybrid");
        ENGINE_CODES.put("J", "5.5L Twin-Turbo V8");
        ENGINE_CODES.put("K", "6.0L Twin-Turbo V12");
        ENGINE_CODES.put("L", "6.3L V8 AMG");
        ENGINE_CODES.put("M", "Electric Motor");
        ENGINE_CODES.put("N", "2.0L Turbo I4 + Hybrid");
        ENGINE_CODES.put("P", "3.0L Diesel V6");
        ENGINE_CODES.put("Q", "2.0L Diesel I4");
        ENGINE_CODES.put("R", "3.0L Diesel I6");
        ENGINE_CODES.put("7", "3.0L Turbo I6 Mild Hybrid");
        ENGINE_CODES.put("8", "4.0L V8 Mild Hybrid");
        ENGINE_CODES.put("9", "2.0L Turbo I4 Mild Hybrid");
    }

    private static void initializeBodyStyles() {
        // Body style codes (sometimes position 7)
        BODY_STYLES.put("A", "Sedan");
        BODY_STYLES.put("B", "Long Wheelbase");
        BODY_STYLES.put("C", "Coupe");
        BODY_STYLES.put("D", "Cabriolet/Convertible");
        BODY_STYLES.put("E", "Estate/Wagon");
        BODY_STYLES.put("F", "SUV/Crossover");
        BODY_STYLES.put("G", "SUV Long");
        BODY_STYLES.put("H", "Hatchback");
        BODY_STYLES.put("K", "Roadster");
        BODY_STYLES.put("L", "Limousine");
        BODY_STYLES.put("S", "Sport");
        BODY_STYLES.put("V", "Van");
        BODY_STYLES.put("X", "Pickup Truck");
    }

    /**
     * Decode Mercedes-Benz specific information from VIN
     */
    public static VehicleInfo decode(String vin) {
        if (vin == null || vin.length() < 17) {
            return null;
        }

        VehicleInfo info = new VehicleInfo();

        // Extract model code (positions 4-6)
        String modelCode = vin.substring(3, 6).toUpperCase();
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

        // Extract body style (position 7)
        String bodyCode = String.valueOf(vin.charAt(6));
        String bodyStyle = BODY_STYLES.get(bodyCode);
        if (bodyStyle != null && info.bodyClass != null) {
            // Enhance body class with style info
            info.bodyClass = info.bodyClass + " / " + bodyStyle;
        }

        // Determine transmission (Mercedes typically uses automatic)
        if (info.driveType != null) {
            info.transmissionStyle = "Automatic";
            info.transmissionSpeeds = "9";  // Most modern Mercedes use 9-speed

            if (info.model != null && info.model.contains("AMG")) {
                info.transmissionSpeeds = "9";  // AMG Speedshift
            }
            if (info.engineDescription != null && info.engineDescription.contains("Electric")) {
                info.transmissionStyle = "Direct Drive";
                info.transmissionSpeeds = "1";
            }
        }

        // Set manufacturer details
        info.manufacturer = "Mercedes-Benz";
        info.manufacturerName = "Mercedes-Benz (Daimler AG)";

        // Set assembly plant info for US-built vehicles
        if (vin.startsWith("4JG")) {
            info.plantCity = "Tuscaloosa";
            info.plantState = "Alabama";
            info.plantCountry = "United States";
        } else if (vin.startsWith("WDB") || vin.startsWith("WDD")) {
            info.plantCountry = "Germany";
            info.plantCity = "Sindelfingen";
        } else if (vin.startsWith("WDC")) {
            info.plantCountry = "Germany";
            info.plantCity = "Bremen";
        }

        // Estimate weight based on vehicle type
        if (info.model != null) {
            if (info.model.contains("S-Class")) {
                info.gvwr = "6000";
                info.curbWeight = "4500";
            } else if (info.model.contains("GLE")) {
                info.gvwr = "6062";
                info.curbWeight = "4630";
            } else if (info.model.contains("GLC")) {
                info.gvwr = "5500";
                info.curbWeight = "3900";
            } else if (info.model.contains("C-Class")) {
                info.gvwr = "4800";
                info.curbWeight = "3500";
            } else if (info.model.contains("E-Class")) {
                info.gvwr = "5300";
                info.curbWeight = "3900";
            } else if (info.model.contains("G-Class")) {
                info.gvwr = "7000";
                info.curbWeight = "5500";
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
