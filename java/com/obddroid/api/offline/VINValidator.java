package com.obddroid.api.offline;

/**
 * VIN Validator
 *
 * Validates VIN format and calculates check digits according to
 * ISO 3779 and NHTSA standards
 */
public class VINValidator {

    private static final String VALID_CHARS = "0123456789ABCDEFGHJKLMNPRSTUVWXYZ";
    private static final int[] CHAR_VALUES = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  // 0-9
        1, 2, 3, 4, 5, 6, 7, 8,         // A-H
        1, 2, 3, 4, 5,                  // J-N
        7, 8, 9,                        // P-R
        2, 3, 4, 5, 6, 7, 8, 9          // S-Z
    };
    private static final int[] POSITION_WEIGHTS = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * Validates VIN format and structure
     * @param vin Vehicle Identification Number
     * @return true if VIN is valid
     */
    public static boolean isValidVIN(String vin) {
        if (vin == null || vin.length() != 17) {
            return false;
        }

        vin = vin.toUpperCase();

        // Check for invalid characters (I, O, Q not allowed)
        for (char c : vin.toCharArray()) {
            if (VALID_CHARS.indexOf(c) == -1) {
                return false;
            }
        }

        // Validate check digit for North American VINs (position 9)
        char checkDigit = vin.charAt(8);
        if (isNorthAmericanVIN(vin)) {
            char calculatedCheckDigit = calculateCheckDigit(vin);
            if (checkDigit != calculatedCheckDigit) {
                // Allow 'X' as valid check digit
                if (!(checkDigit == 'X' && calculatedCheckDigit == 'X')) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Determines if VIN is North American (requires check digit)
     */
    public static boolean isNorthAmericanVIN(String vin) {
        if (vin == null || vin.isEmpty()) return false;

        char firstChar = vin.charAt(0);
        // North American VINs start with 1-5
        return firstChar >= '1' && firstChar <= '5';
    }

    /**
     * Calculates check digit for VIN validation
     */
    private static char calculateCheckDigit(String vin) {
        int sum = 0;

        for (int i = 0; i < 17; i++) {
            if (i == 8) continue; // Skip check digit position

            char c = vin.charAt(i);
            int value = getCharValue(c);
            sum += value * POSITION_WEIGHTS[i];
        }

        int remainder = sum % 11;
        return remainder == 10 ? 'X' : Character.forDigit(remainder, 10);
    }

    /**
     * Gets numeric value for VIN character
     */
    private static int getCharValue(char c) {
        int index = VALID_CHARS.indexOf(c);
        return index >= 0 ? CHAR_VALUES[index] : 0;
    }

    /**
     * Extracts WMI (World Manufacturer Identifier) from VIN
     */
    public static String getWMI(String vin) {
        if (vin == null || vin.length() < 3) return null;
        return vin.substring(0, 3).toUpperCase();
    }

    /**
     * Extracts VDS (Vehicle Descriptor Section) from VIN
     */
    public static String getVDS(String vin) {
        if (vin == null || vin.length() < 9) return null;
        return vin.substring(3, 9).toUpperCase();
    }

    /**
     * Extracts VIS (Vehicle Identifier Section) from VIN
     */
    public static String getVIS(String vin) {
        if (vin == null || vin.length() < 17) return null;
        return vin.substring(9, 17).toUpperCase();
    }

    /**
     * Gets model year from VIN (position 10)
     */
    public static Integer getModelYear(String vin) {
        if (vin == null || vin.length() < 10) return null;

        char yearCode = vin.charAt(9);
        char seventhChar = vin.charAt(6);

        // Determine if 2010+ or 1980-2009 based on position 7
        // If position 7 is a letter (not digit), it's 2010+
        // If position 7 is a digit, it's 1980-2009
        boolean is2010Plus = !Character.isDigit(seventhChar);

        return decodeModelYear(yearCode, is2010Plus);
    }

    /**
     * Decodes model year from year code character
     */
    private static Integer decodeModelYear(char yearCode, boolean is2010Plus) {
        // Year codes repeat every 30 years
        switch (yearCode) {
            case 'A': return is2010Plus ? 2010 : 1980;
            case 'B': return is2010Plus ? 2011 : 1981;
            case 'C': return is2010Plus ? 2012 : 1982;
            case 'D': return is2010Plus ? 2013 : 1983;
            case 'E': return is2010Plus ? 2014 : 1984;
            case 'F': return is2010Plus ? 2015 : 1985;
            case 'G': return is2010Plus ? 2016 : 1986;
            case 'H': return is2010Plus ? 2017 : 1987;
            case 'J': return is2010Plus ? 2018 : 1988;
            case 'K': return is2010Plus ? 2019 : 1989;
            case 'L': return is2010Plus ? 2020 : 1990;
            case 'M': return is2010Plus ? 2021 : 1991;
            case 'N': return is2010Plus ? 2022 : 1992;
            case 'P': return is2010Plus ? 2023 : 1993;
            case 'R': return is2010Plus ? 2024 : 1994;
            case 'S': return is2010Plus ? 2025 : 1995;
            case 'T': return is2010Plus ? 2026 : 1996;
            case 'V': return is2010Plus ? 2027 : 1997;
            case 'W': return is2010Plus ? 2028 : 1998;
            case 'X': return is2010Plus ? 2029 : 1999;
            case 'Y': return is2010Plus ? 2030 : 2000;
            case '1': return 2001;
            case '2': return 2002;
            case '3': return 2003;
            case '4': return 2004;
            case '5': return 2005;
            case '6': return 2006;
            case '7': return 2007;
            case '8': return 2008;
            case '9': return 2009;
            default: return null;
        }
    }

    /**
     * Gets manufacturing plant code from VIN (position 11)
     */
    public static String getPlantCode(String vin) {
        if (vin == null || vin.length() < 11) return null;
        return String.valueOf(vin.charAt(10));
    }

    /**
     * Gets sequential production number from VIN (positions 12-17)
     */
    public static String getProductionNumber(String vin) {
        if (vin == null || vin.length() < 17) return null;
        return vin.substring(11, 17);
    }

    /**
     * Determines manufacturing region from VIN
     */
    public static String getRegion(String vin) {
        if (vin == null || vin.isEmpty()) return "Unknown";

        char firstChar = vin.charAt(0);

        if (firstChar >= '1' && firstChar <= '5') {
            return "North America";
        } else if (firstChar >= '6' && firstChar <= '7') {
            return "Oceania";
        } else if (firstChar >= '8' && firstChar <= '9') {
            return "South America";
        } else if (firstChar >= 'A' && firstChar <= 'H') {
            return "Africa";
        } else if (firstChar >= 'J' && firstChar <= 'R') {
            return "Asia";
        } else if (firstChar >= 'S' && firstChar <= 'Z') {
            return "Europe";
        }

        return "Unknown";
    }

    /**
     * Gets country of origin from VIN
     */
    public static String getCountry(String vin) {
        if (vin == null || vin.length() < 2) return "Unknown";

        String firstTwo = vin.substring(0, 2).toUpperCase();
        char first = firstTwo.charAt(0);

        // North America
        if (first >= '1' && first <= '5') {
            if (first == '1' || first == '4' || first == '5') return "United States";
            if (first == '2') return "Canada";
            if (first == '3') {
                char second = firstTwo.charAt(1);
                if (second >= 'A' && second <= 'W') return "Mexico";
                if (second >= 'X' && second <= '9') return "Costa Rica";
            }
        }

        // Europe
        if (firstTwo.startsWith("W")) {
            if (firstTwo.equals("WA") || firstTwo.equals("WB")) return "Germany";
            if (firstTwo.equals("WF")) return "Germany";
            if (firstTwo.equals("W0")) return "Germany";
        }
        if (firstTwo.startsWith("V")) {
            if (firstTwo.equals("VF")) return "France";
            if (firstTwo.equals("VS")) return "Spain";
            if (firstTwo.equals("VR")) return "France";
        }
        if (firstTwo.startsWith("Z")) {
            if (firstTwo.equals("ZA")) return "Italy";
            if (firstTwo.equals("ZF")) return "Italy";
        }
        if (firstTwo.startsWith("S")) {
            if (firstTwo.equals("SA")) return "United Kingdom";
            if (firstTwo.equals("SC")) return "United Kingdom";
            if (firstTwo.equals("SH")) return "United Kingdom";
        }

        // Asia
        if (firstTwo.startsWith("J")) {
            if (first == 'J') return "Japan";
        }
        if (firstTwo.startsWith("K")) {
            if (firstTwo.equals("KL") || firstTwo.equals("KM") || firstTwo.equals("KN")) return "South Korea";
        }
        if (firstTwo.startsWith("L")) return "China";

        // Oceania
        if (first == '6') return "Australia";
        if (first == '7') return "New Zealand";

        // South America
        if (first == '8') {
            if (firstTwo.equals("8A")) return "Argentina";
            if (firstTwo.equals("8B")) return "Argentina";
            if (firstTwo.equals("8X")) return "Venezuela";
        }
        if (first == '9') {
            if (firstTwo.equals("93")) return "Brazil";
            if (firstTwo.equals("9B")) return "Brazil";
            if (firstTwo.equals("9F")) return "Colombia";
        }

        return "Unknown";
    }
}