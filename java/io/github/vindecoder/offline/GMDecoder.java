package io.github.vindecoder.offline;

import java.util.HashMap;
import java.util.Map;

/**
 * General Motors (GM) VIN Decoder
 *
 * Decodes GM/Chevrolet/GMC/Cadillac/Buick specific VIN patterns
 * Based on GM VIN structure and RPO codes
 *
 * @author Wal33D
 */
public class GMDecoder {

    private static final Map<String, ModelInfo> MODEL_CODES = new HashMap<>();
    private static final Map<String, String> ENGINE_CODES = new HashMap<>();
    private static final Map<String, String> BODY_STYLES = new HashMap<>();

    static {
        initializeModelCodes();
        initializeEngineCodes();
        initializeBodyStyles();
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
        // Chevrolet Silverado 1500 (positions 4-7 or model line indicators)
        MODEL_CODES.put("CC10", new ModelInfo("Chevrolet", "Silverado 1500", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("CC20", new ModelInfo("Chevrolet", "Silverado 1500", "Double Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("CC30", new ModelInfo("Chevrolet", "Silverado 1500", "Crew Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("CK10", new ModelInfo("Chevrolet", "Silverado 1500", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("CK20", new ModelInfo("Chevrolet", "Silverado 1500", "Double Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("CK30", new ModelInfo("Chevrolet", "Silverado 1500", "Crew Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("T10", new ModelInfo("Chevrolet", "Silverado 1500", "Regular Cab WT", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("T20", new ModelInfo("Chevrolet", "Silverado 1500", "Double Cab LT", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("T30", new ModelInfo("Chevrolet", "Silverado 1500", "Crew Cab LTZ", "Pickup Truck", "4WD", "4"));

        // Silverado HD
        MODEL_CODES.put("CC21", new ModelInfo("Chevrolet", "Silverado 2500HD", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("CC22", new ModelInfo("Chevrolet", "Silverado 2500HD", "Double Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("CC23", new ModelInfo("Chevrolet", "Silverado 2500HD", "Crew Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("CK21", new ModelInfo("Chevrolet", "Silverado 2500HD", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("CK22", new ModelInfo("Chevrolet", "Silverado 2500HD", "Double Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("CK23", new ModelInfo("Chevrolet", "Silverado 2500HD", "Crew Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("CC31", new ModelInfo("Chevrolet", "Silverado 3500HD", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("CC32", new ModelInfo("Chevrolet", "Silverado 3500HD", "Double Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("CC33", new ModelInfo("Chevrolet", "Silverado 3500HD", "Crew Cab", "Pickup Truck", "RWD", "4"));

        // GMC Sierra
        MODEL_CODES.put("TC10", new ModelInfo("GMC", "Sierra 1500", "Regular Cab", "Pickup Truck", "RWD", "2"));
        MODEL_CODES.put("TC20", new ModelInfo("GMC", "Sierra 1500", "Double Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("TC30", new ModelInfo("GMC", "Sierra 1500", "Crew Cab", "Pickup Truck", "RWD", "4"));
        MODEL_CODES.put("TK10", new ModelInfo("GMC", "Sierra 1500", "Regular Cab", "Pickup Truck", "4WD", "2"));
        MODEL_CODES.put("TK20", new ModelInfo("GMC", "Sierra 1500", "Double Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("TK30", new ModelInfo("GMC", "Sierra 1500", "Crew Cab", "Pickup Truck", "4WD", "4"));
        MODEL_CODES.put("TK40", new ModelInfo("GMC", "Sierra 1500", "Crew Cab Denali", "Pickup Truck", "4WD", "4"));

        // Chevrolet Corvette
        MODEL_CODES.put("Y07", new ModelInfo("Chevrolet", "Corvette", "Stingray", "Coupe", "RWD", "2"));
        MODEL_CODES.put("Y17", new ModelInfo("Chevrolet", "Corvette", "Stingray Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("Y27", new ModelInfo("Chevrolet", "Corvette", "Grand Sport", "Coupe", "RWD", "2"));
        MODEL_CODES.put("Y37", new ModelInfo("Chevrolet", "Corvette", "Grand Sport Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("Y47", new ModelInfo("Chevrolet", "Corvette", "Z06", "Coupe", "RWD", "2"));
        MODEL_CODES.put("Y57", new ModelInfo("Chevrolet", "Corvette", "Z06 Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("Y67", new ModelInfo("Chevrolet", "Corvette", "ZR1", "Coupe", "RWD", "2"));

        // C8 Corvette (2020+)
        MODEL_CODES.put("Y2Y", new ModelInfo("Chevrolet", "Corvette", "Stingray", "Coupe", "RWD", "2"));
        MODEL_CODES.put("Y2Z", new ModelInfo("Chevrolet", "Corvette", "Z06", "Coupe", "RWD", "2"));

        // Chevrolet Camaro
        MODEL_CODES.put("CC1", new ModelInfo("Chevrolet", "Camaro", "LS/LT", "Coupe", "RWD", "2"));
        MODEL_CODES.put("CC2", new ModelInfo("Chevrolet", "Camaro", "LS/LT Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("CD1", new ModelInfo("Chevrolet", "Camaro", "SS", "Coupe", "RWD", "2"));
        MODEL_CODES.put("CD2", new ModelInfo("Chevrolet", "Camaro", "SS Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("CE1", new ModelInfo("Chevrolet", "Camaro", "ZL1", "Coupe", "RWD", "2"));
        MODEL_CODES.put("CE2", new ModelInfo("Chevrolet", "Camaro", "ZL1 Convertible", "Convertible", "RWD", "2"));
        MODEL_CODES.put("CF1", new ModelInfo("Chevrolet", "Camaro", "1LE", "Coupe", "RWD", "2"));

        // Chevrolet Tahoe/Suburban
        MODEL_CODES.put("CK05", new ModelInfo("Chevrolet", "Tahoe", "LS", "SUV", "RWD", "4"));
        MODEL_CODES.put("CC05", new ModelInfo("Chevrolet", "Tahoe", "LS", "SUV", "RWD", "4"));
        MODEL_CODES.put("CK06", new ModelInfo("Chevrolet", "Tahoe", "LT", "SUV", "4WD", "4"));
        MODEL_CODES.put("CK07", new ModelInfo("Chevrolet", "Tahoe", "Premier", "SUV", "4WD", "4"));
        MODEL_CODES.put("CK08", new ModelInfo("Chevrolet", "Tahoe", "High Country", "SUV", "4WD", "4"));
        MODEL_CODES.put("CK15", new ModelInfo("Chevrolet", "Suburban", "LS", "SUV", "RWD", "4"));
        MODEL_CODES.put("CK16", new ModelInfo("Chevrolet", "Suburban", "LT", "SUV", "4WD", "4"));
        MODEL_CODES.put("CK17", new ModelInfo("Chevrolet", "Suburban", "Premier", "SUV", "4WD", "4"));
        MODEL_CODES.put("CK18", new ModelInfo("Chevrolet", "Suburban", "High Country", "SUV", "4WD", "4"));

        // GMC Yukon/Yukon XL
        MODEL_CODES.put("TK05", new ModelInfo("GMC", "Yukon", "SLE", "SUV", "RWD", "4"));
        MODEL_CODES.put("TK06", new ModelInfo("GMC", "Yukon", "SLT", "SUV", "4WD", "4"));
        MODEL_CODES.put("TK07", new ModelInfo("GMC", "Yukon", "Denali", "SUV", "4WD", "4"));
        MODEL_CODES.put("TK15", new ModelInfo("GMC", "Yukon XL", "SLE", "SUV", "RWD", "4"));
        MODEL_CODES.put("TK16", new ModelInfo("GMC", "Yukon XL", "SLT", "SUV", "4WD", "4"));
        MODEL_CODES.put("TK17", new ModelInfo("GMC", "Yukon XL", "Denali", "SUV", "4WD", "4"));

        // Cadillac Escalade
        MODEL_CODES.put("YK05", new ModelInfo("Cadillac", "Escalade", "Luxury", "SUV", "RWD", "4"));
        MODEL_CODES.put("YK06", new ModelInfo("Cadillac", "Escalade", "Premium Luxury", "SUV", "4WD", "4"));
        MODEL_CODES.put("YK07", new ModelInfo("Cadillac", "Escalade", "Sport", "SUV", "4WD", "4"));
        MODEL_CODES.put("YK08", new ModelInfo("Cadillac", "Escalade", "Platinum", "SUV", "4WD", "4"));
        MODEL_CODES.put("YK15", new ModelInfo("Cadillac", "Escalade ESV", "Luxury", "SUV", "RWD", "4"));
        MODEL_CODES.put("YK16", new ModelInfo("Cadillac", "Escalade ESV", "Premium Luxury", "SUV", "4WD", "4"));
        MODEL_CODES.put("YK17", new ModelInfo("Cadillac", "Escalade ESV", "Sport", "SUV", "4WD", "4"));
        MODEL_CODES.put("YK18", new ModelInfo("Cadillac", "Escalade ESV", "Platinum", "SUV", "4WD", "4"));

        // Chevrolet Equinox
        MODEL_CODES.put("LM1", new ModelInfo("Chevrolet", "Equinox", "L", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("LM2", new ModelInfo("Chevrolet", "Equinox", "LS", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("LM3", new ModelInfo("Chevrolet", "Equinox", "LT", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("LM4", new ModelInfo("Chevrolet", "Equinox", "Premier", "SUV/Crossover", "AWD", "4"));

        // Chevrolet Traverse
        MODEL_CODES.put("RV1", new ModelInfo("Chevrolet", "Traverse", "L", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RV2", new ModelInfo("Chevrolet", "Traverse", "LS", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RV3", new ModelInfo("Chevrolet", "Traverse", "LT", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RV4", new ModelInfo("Chevrolet", "Traverse", "RS", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RV5", new ModelInfo("Chevrolet", "Traverse", "Premier", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RV6", new ModelInfo("Chevrolet", "Traverse", "High Country", "SUV/Crossover", "AWD", "4"));

        // Chevrolet Blazer
        MODEL_CODES.put("RS1", new ModelInfo("Chevrolet", "Blazer", "L", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RS2", new ModelInfo("Chevrolet", "Blazer", "LT", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RS3", new ModelInfo("Chevrolet", "Blazer", "RS", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RS4", new ModelInfo("Chevrolet", "Blazer", "Premier", "SUV/Crossover", "AWD", "4"));

        // Chevrolet Malibu
        MODEL_CODES.put("ZD1", new ModelInfo("Chevrolet", "Malibu", "L", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ZD2", new ModelInfo("Chevrolet", "Malibu", "LS", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ZD3", new ModelInfo("Chevrolet", "Malibu", "LT", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ZD4", new ModelInfo("Chevrolet", "Malibu", "Premier", "Sedan", "FWD", "4"));
        MODEL_CODES.put("ZD5", new ModelInfo("Chevrolet", "Malibu", "Hybrid", "Sedan/Hybrid", "FWD", "4"));

        // Cadillac CT4/CT5
        MODEL_CODES.put("6DM", new ModelInfo("Cadillac", "CT4", "Luxury", "Sedan", "RWD", "4"));
        MODEL_CODES.put("6DN", new ModelInfo("Cadillac", "CT4", "Premium Luxury", "Sedan", "AWD", "4"));
        MODEL_CODES.put("6DP", new ModelInfo("Cadillac", "CT4", "V-Series", "Sedan", "RWD", "4"));
        MODEL_CODES.put("6DQ", new ModelInfo("Cadillac", "CT4-V", "Blackwing", "Sedan", "RWD", "4"));
        MODEL_CODES.put("6DS", new ModelInfo("Cadillac", "CT5", "Luxury", "Sedan", "RWD", "4"));
        MODEL_CODES.put("6DT", new ModelInfo("Cadillac", "CT5", "Premium Luxury", "Sedan", "AWD", "4"));
        MODEL_CODES.put("6DU", new ModelInfo("Cadillac", "CT5", "V-Series", "Sedan", "AWD", "4"));
        MODEL_CODES.put("6DV", new ModelInfo("Cadillac", "CT5-V", "Blackwing", "Sedan", "RWD", "4"));

        // Buick Enclave
        MODEL_CODES.put("RV7", new ModelInfo("Buick", "Enclave", "Preferred", "SUV/Crossover", "FWD", "4"));
        MODEL_CODES.put("RV8", new ModelInfo("Buick", "Enclave", "Essence", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RV9", new ModelInfo("Buick", "Enclave", "Premium", "SUV/Crossover", "AWD", "4"));
        MODEL_CODES.put("RVA", new ModelInfo("Buick", "Enclave", "Avenir", "SUV/Crossover", "AWD", "4"));
    }

    private static void initializeEngineCodes() {
        // Position 8 engine codes (RPO-based)
        // LS Family
        ENGINE_CODES.put("G", "5.3L V8 (LS1/LS4)");  // Classic LS
        ENGINE_CODES.put("P", "5.3L V8 (LM7/L59)");  // Iron block LS
        ENGINE_CODES.put("T", "5.3L V8 (LMG/LC9)");  // Gen IV
        ENGINE_CODES.put("0", "5.3L V8 (L83)");      // Gen V EcoTec3
        ENGINE_CODES.put("C", "5.3L V8 (L84)");      // Gen V with DFM
        ENGINE_CODES.put("3", "5.3L V8 (LC9/LH6)");  // Active Fuel Management

        // 6.2L Engines
        ENGINE_CODES.put("6", "6.2L V8 (L92/L9H)");  // Gen IV
        ENGINE_CODES.put("8", "6.2L V8 (L94)");      // Escalade/Denali
        ENGINE_CODES.put("J", "6.2L V8 (L86)");      // Gen V EcoTec3
        ENGINE_CODES.put("E", "6.2L V8 (L87)");      // Gen V with DFM
        ENGINE_CODES.put("W", "6.2L V8 (LS3)");      // Corvette/Camaro
        ENGINE_CODES.put("Y", "6.2L V8 (LT1)");      // Gen V Corvette/Camaro
        ENGINE_CODES.put("R", "6.2L V8 (LT2)");      // C8 Corvette
        ENGINE_CODES.put("S", "6.2L V8 (LT4)");      // Supercharged
        ENGINE_CODES.put("F", "6.2L V8 (LS9)");      // Supercharged (ZR1)

        // 6.6L Engines
        ENGINE_CODES.put("L", "6.6L V8 Duramax Diesel (LML/L5P)");
        ENGINE_CODES.put("Y", "6.6L V8 (L8T)");      // Gas HD
        ENGINE_CODES.put("2", "6.6L V8 Duramax Diesel (LB7)");
        ENGINE_CODES.put("D", "6.6L V8 Duramax Diesel (LLY)");
        ENGINE_CODES.put("V", "6.6L V8 Duramax Diesel (LBZ)");
        ENGINE_CODES.put("M", "6.6L V8 Duramax Diesel (LMM)");
        ENGINE_CODES.put("H", "6.6L V8 Duramax Diesel (LGH)");

        // 5.7L LS Engines (older)
        ENGINE_CODES.put("G", "5.7L V8 (LS1)");      // Corvette/Camaro/Firebird
        ENGINE_CODES.put("S", "5.7L V8 (LS6)");      // High-performance

        // 4-Cylinder Engines
        ENGINE_CODES.put("K", "1.5L Turbo I4");
        ENGINE_CODES.put("B", "2.0L Turbo I4");
        ENGINE_CODES.put("L", "2.5L I4");
        ENGINE_CODES.put("N", "1.4L Turbo I4");
        ENGINE_CODES.put("U", "1.6L Turbo-Diesel I4");
        ENGINE_CODES.put("Q", "2.4L I4 (LE5/LEA)");
        ENGINE_CODES.put("Z", "2.4L Hybrid I4");

        // V6 Engines
        ENGINE_CODES.put("A", "3.6L V6 (LGX/LFY)");  // High Feature
        ENGINE_CODES.put("V", "3.6L V6 (LLT)");      // Direct Injection
        ENGINE_CODES.put("7", "4.3L V6 (LV3)");      // EcoTec3
        ENGINE_CODES.put("X", "4.3L V6 (LU3)");      // Vortec
        ENGINE_CODES.put("9", "3.0L Turbo-Diesel I6 (LM2)");
        ENGINE_CODES.put("M", "3.0L Turbo I6 (LGW)"); // Cadillac

        // Electric
        ENGINE_CODES.put("4", "Electric Motor (Bolt EV/EUV)");
        ENGINE_CODES.put("5", "Electric Motor (Hummer EV)");
        ENGINE_CODES.put("1", "Electric Motor (Silverado EV)");
    }

    private static void initializeBodyStyles() {
        // Body style codes (position 6 typically)
        BODY_STYLES.put("1", "2-Door Coupe");
        BODY_STYLES.put("2", "2-Door Convertible");
        BODY_STYLES.put("3", "2-Door Hatchback");
        BODY_STYLES.put("4", "4-Door Sedan");
        BODY_STYLES.put("5", "4-Door Hatchback");
        BODY_STYLES.put("6", "4-Door Wagon");
        BODY_STYLES.put("7", "4-Door SUV");
        BODY_STYLES.put("8", "2-Door SUV");
        BODY_STYLES.put("9", "Extended Cab Pickup");
        BODY_STYLES.put("0", "Crew Cab Pickup");
    }

    /**
     * Decode GM specific information from VIN
     */
    public static VehicleInfo decode(String vin) {
        if (vin == null || vin.length() < 17) {
            return null;
        }

        VehicleInfo info = new VehicleInfo();

        // GM uses different patterns for different platforms
        // Try to identify the vehicle type from positions 4-7
        String platformCode = vin.substring(3, 7).toUpperCase();

        // Try full 4-character codes first
        ModelInfo modelInfo = MODEL_CODES.get(platformCode);

        if (modelInfo == null) {
            // Try 3-character codes (positions 4-6)
            platformCode = vin.substring(3, 6).toUpperCase();
            modelInfo = MODEL_CODES.get(platformCode);
        }

        if (modelInfo == null) {
            // Try 2-character codes (positions 5-6)
            platformCode = vin.substring(4, 6).toUpperCase();
            modelInfo = MODEL_CODES.get(platformCode);
        }

        if (modelInfo != null) {
            info.make = modelInfo.make;
            info.model = modelInfo.model;
            info.series = modelInfo.series;
            info.bodyClass = modelInfo.bodyClass;
            info.driveType = modelInfo.driveType;
            info.doors = modelInfo.doors;
        } else {
            // Try to determine make from WMI
            String wmi = vin.substring(0, 3);
            if (wmi.startsWith("1G")) {
                char wmi3 = wmi.charAt(2);
                switch (wmi3) {
                    case '1':
                    case 'C':
                    case 'D':
                    case 'N':
                    case 'S':
                    case 'X':
                    case 'Y':
                        info.make = "Chevrolet";
                        break;
                    case '2':
                    case '3':
                    case 'M':
                        info.make = "Pontiac";
                        break;
                    case '4':
                    case 'B':
                    case 'K':
                        info.make = "Buick";
                        break;
                    case '6':
                    case 'E':
                        info.make = "Cadillac";
                        break;
                    case '7':
                        info.make = "Pontiac Canada";
                        break;
                    case '8':
                    case 'Z':
                        info.make = "Saturn";
                        break;
                    case 'T':
                    case 'J':
                        info.make = "GMC";
                        break;
                }
            } else if (wmi.equals("2G1")) {
                info.make = "Chevrolet Canada";
            } else if (wmi.equals("2G2")) {
                info.make = "Pontiac Canada";
            } else if (wmi.equals("3G1")) {
                info.make = "Chevrolet Mexico";
            }
        }

        // Extract engine code (position 8)
        String engineCode = String.valueOf(vin.charAt(7));
        info.engineDescription = ENGINE_CODES.get(engineCode.toUpperCase());

        // Extract body style if position 6 has relevant info
        String bodyCode = String.valueOf(vin.charAt(5));
        String bodyStyle = BODY_STYLES.get(bodyCode);
        if (bodyStyle != null && info.bodyClass == null) {
            info.bodyClass = bodyStyle;
        }

        // Determine transmission based on model and engine
        if (info.model != null && info.engineDescription != null) {
            if (info.model.contains("Corvette")) {
                if (info.model.contains("Z06") || info.model.contains("ZR1")) {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "7/8";  // 7-speed manual or 8-speed auto
                } else if (vin.charAt(9) >= 'L') {  // C8 Corvette (2020+)
                    info.transmissionStyle = "Automatic (DCT)";
                    info.transmissionSpeeds = "8";  // 8-speed dual-clutch
                } else {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/8";
                }
            } else if (info.model.contains("Camaro")) {
                if (info.model.contains("ZL1")) {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/10";  // 6-speed manual or 10-speed auto
                } else if (info.model.contains("SS")) {
                    info.transmissionStyle = "Manual/Automatic";
                    info.transmissionSpeeds = "6/8";
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "8";
                }
            } else if (info.model.contains("Silverado") || info.model.contains("Sierra")) {
                if (info.engineDescription != null && info.engineDescription.contains("Diesel")) {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "10";  // Allison 10-speed
                } else {
                    info.transmissionStyle = "Automatic";
                    info.transmissionSpeeds = "8" or "10";  // 8 or 10-speed depending on year
                }
            } else if (info.model.contains("Tahoe") || info.model.contains("Suburban") ||
                      info.model.contains("Yukon") || info.model.contains("Escalade")) {
                info.transmissionStyle = "Automatic";
                info.transmissionSpeeds = "10";  // 10-speed on newer models
            } else {
                // Most other GM vehicles
                info.transmissionStyle = "Automatic";
                if (info.model.contains("CT4") || info.model.contains("CT5")) {
                    if (info.model.contains("Blackwing")) {
                        info.transmissionStyle = "Manual/Automatic";
                        info.transmissionSpeeds = "6/10";
                    } else {
                        info.transmissionSpeeds = "10";
                    }
                } else {
                    info.transmissionSpeeds = "6" or "8" or "9";  // Varies by model
                }
            }
        }

        // Set manufacturer info
        info.manufacturer = "General Motors";
        if (info.make != null) {
            info.manufacturerName = "General Motors (" + info.make + ")";
        } else {
            info.manufacturerName = "General Motors";
        }

        // Determine plant location based on position 11
        char plantCode = vin.charAt(10);
        switch (plantCode) {
            case '0':
                info.plantCity = "Lansing";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case '1':
                info.plantCity = "Wentzville";
                info.plantState = "Missouri";
                info.plantCountry = "United States";
                break;
            case '2':
                info.plantCity = "St. Therese";
                info.plantCountry = "Canada";
                break;
            case '3':
                info.plantCity = "Detroit/Hamtramck";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case '4':
                info.plantCity = "Orion";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case '5':
                info.plantCity = "London/Bowling Green";
                info.plantState = "Kentucky/Ontario";
                info.plantCountry = "United States/Canada";
                break;
            case '6':
                info.plantCity = "Lansing Grand River";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case '7':
                info.plantCity = "Lordstown";
                info.plantState = "Ohio";
                info.plantCountry = "United States";
                break;
            case '8':
            case 'B':
                info.plantCity = "Flint/Shreveport";
                info.plantState = "Michigan/Louisiana";
                info.plantCountry = "United States";
                break;
            case '9':
                info.plantCity = "Oshawa";
                info.plantState = "Ontario";
                info.plantCountry = "Canada";
                break;
            case 'A':
                info.plantCity = "Ramos Arizpe";
                info.plantCountry = "Mexico";
                break;
            case 'C':
                info.plantCity = "Southgate";
                info.plantState = "California";
                info.plantCountry = "United States";
                break;
            case 'D':
                info.plantCity = "Doraville";
                info.plantState = "Georgia";
                info.plantCountry = "United States";
                break;
            case 'E':
                info.plantCity = "Pontiac";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'F':
                info.plantCity = "Fairfax";
                info.plantState = "Kansas";
                info.plantCountry = "United States";
                break;
            case 'G':
                info.plantCity = "Silao";
                info.plantCountry = "Mexico";
                break;
            case 'H':
                info.plantCity = "Flint";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'J':
                info.plantCity = "Janesville";
                info.plantState = "Wisconsin";
                info.plantCountry = "United States";
                break;
            case 'K':
                info.plantCity = "Linden/Leeds";
                info.plantState = "New Jersey/Missouri";
                info.plantCountry = "United States";
                break;
            case 'L':
                info.plantCity = "Van Nuys";
                info.plantState = "California";
                info.plantCountry = "United States";
                break;
            case 'N':
                info.plantCity = "Norwood";
                info.plantState = "Ohio";
                info.plantCountry = "United States";
                break;
            case 'R':
                info.plantCity = "Arlington";
                info.plantState = "Texas";
                info.plantCountry = "United States";
                break;
            case 'S':
                info.plantCity = "Ramos Arizpe/St. Louis";
                info.plantCountry = "Mexico/United States";
                break;
            case 'T':
                info.plantCity = "Tarrytown";
                info.plantState = "New York";
                info.plantCountry = "United States";
                break;
            case 'U':
                info.plantCity = "Detroit";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'V':
                info.plantCity = "Pontiac";
                info.plantState = "Michigan";
                info.plantCountry = "United States";
                break;
            case 'W':
                info.plantCity = "Wilmington";
                info.plantState = "Delaware";
                info.plantCountry = "United States";
                break;
            case 'X':
                info.plantCity = "Fairfax II";
                info.plantState = "Kansas";
                info.plantCountry = "United States";
                break;
            case 'Y':
                info.plantCity = "Wilmington";
                info.plantState = "Delaware";
                info.plantCountry = "United States";
                break;
            case 'Z':
                info.plantCity = "Fort Wayne/Fremont";
                info.plantState = "Indiana/California";
                info.plantCountry = "United States";
                break;
        }

        // Add weight estimates based on model
        if (info.model != null) {
            if (info.model.contains("Silverado 1500") || info.model.contains("Sierra 1500")) {
                info.curbWeight = "4500-5300";
            } else if (info.model.contains("Silverado 2500") || info.model.contains("Sierra 2500")) {
                info.curbWeight = "6000-6800";
            } else if (info.model.contains("Silverado 3500") || info.model.contains("Sierra 3500")) {
                info.curbWeight = "6500-7500";
            } else if (info.model.contains("Corvette")) {
                info.curbWeight = "3300-3650";
            } else if (info.model.contains("Camaro")) {
                info.curbWeight = "3400-3900";
            } else if (info.model.contains("Tahoe") || info.model.contains("Yukon")) {
                info.curbWeight = "5300-5700";
            } else if (info.model.contains("Suburban") || info.model.contains("Yukon XL")) {
                info.curbWeight = "5600-6000";
            } else if (info.model.contains("Escalade")) {
                info.curbWeight = "5700-6100";
            } else if (info.model.contains("Equinox")) {
                info.curbWeight = "3300-3500";
            } else if (info.model.contains("Traverse") || info.model.contains("Enclave")) {
                info.curbWeight = "4300-4700";
            } else if (info.model.contains("Malibu")) {
                info.curbWeight = "3100-3300";
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
