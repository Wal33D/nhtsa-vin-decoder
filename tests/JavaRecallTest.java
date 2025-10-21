import io.github.vindecoder.nhtsa.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Test class for NHTSA Recall functionality
 *
 * @author Wal33D
 */
public class JavaRecallTest {

    private static final String TEST_VIN_HONDA = "1HGCM82633A004352"; // Honda Accord
    private static final String TEST_VIN_MERCEDES = "4JGDA5HB7JB158144"; // Mercedes GLE
    private static final String TEST_VIN_BMW = "WBA3B5C50EJ841989"; // BMW 3 Series

    public static void main(String[] args) {
        JavaRecallTest test = new JavaRecallTest();

        System.out.println("Starting NHTSA Recall Tests...\n");

        // Test 1: Get recalls for specific make/model/year
        test.testGetRecallsForVehicle();

        // Test 2: Decode VIN and get recalls
        test.testGetRecallsByVin();

        // Test 3: Test recall cache
        test.testRecallCache();

        System.out.println("\nAll tests completed!");
    }

    /**
     * Test getting recalls for a specific vehicle
     */
    public void testGetRecallsForVehicle() {
        System.out.println("Test 1: Get Recalls for Vehicle (Honda Accord 2020)");
        System.out.println("================================================");

        VINDecoderService service = VINDecoderService.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);

        service.getRecallsForVehicle("Honda", "Accord", "2020",
            new VINDecoderService.RecallCallback() {
                @Override
                public void onSuccess(List<RecallRecord> recalls) {
                    System.out.println("Found " + recalls.size() + " recalls for 2020 Honda Accord");

                    for (int i = 0; i < Math.min(3, recalls.size()); i++) {
                        RecallRecord recall = recalls.get(i);
                        System.out.println("\nRecall #" + (i + 1) + ":");
                        System.out.println("  Campaign: " + recall.getNhtsaCampaignNumber());
                        System.out.println("  Component: " + recall.getComponent());
                        System.out.println("  Summary: " + truncate(recall.getSummary(), 100));
                        System.out.println("  Critical Safety: " + recall.isCriticalSafety());
                        System.out.println("  OTA Update: " + recall.isOverTheAir());
                    }

                    if (recalls.size() > 3) {
                        System.out.println("\n... and " + (recalls.size() - 3) + " more recalls");
                    }

                    latch.countDown();
                }

                @Override
                public void onError(String error) {
                    System.err.println("Error getting recalls: " + error);
                    latch.countDown();
                }
            });

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n");
    }

    /**
     * Test decoding VIN and enriching with recalls
     */
    public void testGetRecallsByVin() {
        System.out.println("Test 2: Decode VIN and Get Recalls");
        System.out.println("===================================");

        VINDecoderService service = VINDecoderService.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);

        System.out.println("Testing VIN: " + TEST_VIN_HONDA);

        service.getRecallsByVin(TEST_VIN_HONDA,
            new VINDecoderService.VINDecoderCallback() {
                @Override
                public void onSuccess(VehicleData vehicleData) {
                    System.out.println("\nVehicle Details:");
                    System.out.println("  " + vehicleData.getDisplayName());
                    System.out.println("  Manufacturer: " + vehicleData.getManufacturer());
                    System.out.println("  VIN: " + vehicleData.getVin());

                    if (vehicleData.hasRecalls()) {
                        System.out.println("\nRecalls Found: " + vehicleData.getRecallCount());
                        List<RecallRecord> recalls = vehicleData.getRecalls();

                        for (int i = 0; i < Math.min(2, recalls.size()); i++) {
                            RecallRecord recall = recalls.get(i);
                            System.out.println("\n  Recall #" + (i + 1) + ":");
                            System.out.println("    Campaign: " + recall.getNhtsaCampaignNumber());
                            System.out.println("    Component: " + recall.getComponent());
                        }
                    } else {
                        System.out.println("\nNo recalls found for this vehicle");
                    }

                    latch.countDown();
                }

                @Override
                public void onError(String error) {
                    System.err.println("Error decoding VIN: " + error);
                    latch.countDown();
                }
            });

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n");
    }

    /**
     * Test recall cache functionality
     */
    public void testRecallCache() {
        System.out.println("Test 3: Recall Cache Test");
        System.out.println("=========================");

        VINDecoderService service = VINDecoderService.getInstance();
        final CountDownLatch latch1 = new CountDownLatch(1);
        final CountDownLatch latch2 = new CountDownLatch(1);

        final long[] firstCallTime = new long[1];
        final long[] secondCallTime = new long[1];

        // First call - should hit API
        System.out.println("First call (API)...");
        long start1 = System.currentTimeMillis();

        service.getRecallsForVehicle("Toyota", "Camry", "2019",
            new VINDecoderService.RecallCallback() {
                @Override
                public void onSuccess(List<RecallRecord> recalls) {
                    firstCallTime[0] = System.currentTimeMillis() - start1;
                    System.out.println("  Time: " + firstCallTime[0] + "ms");
                    System.out.println("  Recalls found: " + recalls.size());
                    latch1.countDown();
                }

                @Override
                public void onError(String error) {
                    System.err.println("  Error: " + error);
                    latch1.countDown();
                }
            });

        try {
            latch1.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Second call - should hit cache
        System.out.println("\nSecond call (Cache)...");
        long start2 = System.currentTimeMillis();

        service.getRecallsForVehicle("Toyota", "Camry", "2019",
            new VINDecoderService.RecallCallback() {
                @Override
                public void onSuccess(List<RecallRecord> recalls) {
                    secondCallTime[0] = System.currentTimeMillis() - start2;
                    System.out.println("  Time: " + secondCallTime[0] + "ms");
                    System.out.println("  Recalls found: " + recalls.size());
                    latch2.countDown();
                }

                @Override
                public void onError(String error) {
                    System.err.println("  Error: " + error);
                    latch2.countDown();
                }
            });

        try {
            latch2.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (secondCallTime[0] < firstCallTime[0]) {
            System.out.println("\n✓ Cache working! Second call was " +
                (firstCallTime[0] - secondCallTime[0]) + "ms faster");
        } else {
            System.out.println("\n⚠ Cache may not be working properly");
        }

        System.out.println("\n");
    }

    /**
     * Helper method to truncate strings
     */
    private String truncate(String str, int maxLength) {
        if (str == null) return "N/A";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
    }
}