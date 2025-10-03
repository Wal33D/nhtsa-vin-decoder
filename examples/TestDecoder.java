package examples;

import io.github.vindecoder.offline.OfflineVINDecoder;
import io.github.vindecoder.nhtsa.VehicleData;

/**
 * Example demonstrating offline VIN decoding
 *
 * @author Wal33D
 */
public class TestDecoder {

    public static void main(String[] args) {
        // Create decoder instance
        OfflineVINDecoder decoder = new OfflineVINDecoder();

        // Test VINs
        String[] testVINs = {
            "4JGDA5HB7JB158144", // Mercedes GLE
            "1FTFW1ET5DFC10312", // Ford F-150
            "1G1ZD5ST0LF123456", // Chevrolet Malibu
            "5YJ3E1EA5JF037274", // Tesla Model 3
            "4T1BF1FK5CU123456", // Toyota Camry
            "1HD1KEM14DB652351"  // Harley-Davidson
        };

        // Decode each VIN
        for (String vin : testVINs) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("VIN: " + vin);
            System.out.println("=".repeat(50));

            // Validate VIN
            boolean isValid = decoder.validate(vin);
            System.out.println("Valid: " + isValid);

            if (isValid) {
                // Decode VIN
                VehicleData vehicle = decoder.decode(vin);

                // Display results
                System.out.println("Manufacturer: " + vehicle.getManufacturerName());
                System.out.println("Make: " + vehicle.getMake());
                System.out.println("Model: " + vehicle.getModel());
                System.out.println("Year: " + vehicle.getModelYear());
                System.out.println("Type: " + vehicle.getVehicleType());

                // Display additional info if available
                if (vehicle.getTrim() != null) {
                    System.out.println("Trim: " + vehicle.getTrim());
                }
                if (vehicle.getEngineModel() != null) {
                    System.out.println("Engine: " + vehicle.getEngineModel());
                }
                if (vehicle.getTransmissionStyle() != null) {
                    System.out.println("Transmission: " + vehicle.getTransmissionStyle() +
                                     " " + vehicle.getTransmissionSpeeds() + "-speed");
                }
                if (vehicle.getPlantCity() != null) {
                    System.out.println("Plant: " + vehicle.getPlantCity() +
                                     ", " + vehicle.getPlantState());
                }
            }
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Test Complete");
        System.out.println("=".repeat(50));
    }

    /**
     * Quick validation test
     */
    public static void testValidation() {
        OfflineVINDecoder decoder = new OfflineVINDecoder();

        // Test valid VIN
        String validVIN = "1HGCM82633A004352";
        System.out.println(validVIN + " is valid: " + decoder.validate(validVIN));

        // Test invalid VIN (wrong check digit)
        String invalidVIN = "1HGCM82633A004353";
        System.out.println(invalidVIN + " is valid: " + decoder.validate(invalidVIN));
    }

    /**
     * Performance test
     */
    public static void performanceTest() {
        OfflineVINDecoder decoder = new OfflineVINDecoder();
        String vin = "4JGDA5HB7JB158144";

        // Warm up
        for (int i = 0; i < 100; i++) {
            decoder.decode(vin);
        }

        // Measure time
        long start = System.currentTimeMillis();
        int iterations = 10000;

        for (int i = 0; i < iterations; i++) {
            decoder.decode(vin);
        }

        long elapsed = System.currentTimeMillis() - start;
        double avgTime = (double) elapsed / iterations;

        System.out.println("Performance Test Results:");
        System.out.println("Iterations: " + iterations);
        System.out.println("Total time: " + elapsed + "ms");
        System.out.println("Average time per decode: " + avgTime + "ms");
    }
}
