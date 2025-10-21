package io.github.vindecoder.nhtsa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VIN Decoder Service using NHTSA vPIC API
 *
 * This service provides VIN decoding functionality using the official
 * National Highway Traffic Safety Administration (NHTSA) API.
 *
 * Features:
 * - Free, official government API
 * - No API key required
 * - Comprehensive vehicle information
 * - Supports partial VINs
 * - Caching to reduce API calls
 */
public class VINDecoderService {

    private static final String TAG = "VINDecoder";
    private static final String BASE_URL = "https://vpic.nhtsa.dot.gov/api/";
    private static final String RECALLS_BASE_URL = "https://api.nhtsa.gov/recalls/";

    private static VINDecoderService instance;
    private final NHTSAApiService apiService;
    private final NHTSARecallApiService recallApiService;

    // Cache for decoded VINs to reduce API calls
    private final Map<String, VehicleData> cache = new HashMap<>();
    // Cache for recall data
    private final Map<String, List<RecallRecord>> recallCache = new HashMap<>();

    /**
     * Callback interface for VIN decoding results
     */
    public interface VINDecoderCallback {
        /**
         * Called when VIN decoding is successful
         * @param vehicleData The decoded vehicle information
         */
        void onSuccess(VehicleData vehicleData);

        /**
         * Called when VIN decoding fails
         * @param error Error message
         */
        void onError(String error);
    }

    /**
     * Callback interface for recall lookup results
     */
    public interface RecallCallback {
        /**
         * Called when recall lookup is successful
         * @param recalls List of recall records
         */
        void onSuccess(List<RecallRecord> recalls);

        /**
         * Called when recall lookup fails
         * @param error Error message
         */
        void onError(String error);
    }

    /**
     * Private constructor for singleton pattern
     */
    private VINDecoderService() {
        // Create Retrofit instance for VIN API
        Retrofit vinRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = vinRetrofit.create(NHTSAApiService.class);

        // Create Retrofit instance for Recall API
        Retrofit recallRetrofit = new Retrofit.Builder()
                .baseUrl(RECALLS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recallApiService = recallRetrofit.create(NHTSARecallApiService.class);
    }

    /**
     * Get singleton instance of VIN decoder service
     * @return VINDecoderService instance
     */
    public static synchronized VINDecoderService getInstance() {
        if (instance == null) {
            instance = new VINDecoderService();
        }
        return instance;
    }

    /**
     * Decode a VIN asynchronously
     *
     * @param vin The VIN to decode (17 characters for full VIN)
     * @param callback Callback for results
     */
    public void decodeVIN(String vin, VINDecoderCallback callback) {
        if (vin == null || vin.trim().isEmpty()) {
            callback.onError("VIN cannot be empty");
            return;
        }

        final String normalizedVin = vin.trim().toUpperCase();

        // Check cache first
        if (cache.containsKey(normalizedVin)) {
            VehicleData cached = cache.get(normalizedVin);
            if (cached != null) {
                System.out.println(TAG + ": Returning cached VIN data for: " + normalizedVin);
                callback.onSuccess(cached);
                return;
            }
        }

        // Make API call
        System.out.println(TAG + ": Decoding VIN: " + normalizedVin);
        Call<VINResponse> call = apiService.decodeVIN(normalizedVin, "json");

        call.enqueue(new Callback<VINResponse>() {
            @Override
            public void onResponse(Call<VINResponse> call, Response<VINResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    VINResponse vinResponse = response.body();

                    if (vinResponse.hasValidData()) {
                        VehicleData vehicleData = vinResponse.getVehicleData();

                        // Check for errors in the response
                        if (vehicleData.isValid()) {
                            // Cache the result
                            cache.put(normalizedVin, vehicleData);

                            System.out.println(TAG + ": Successfully decoded VIN: " + vehicleData.getDisplayName());
                            callback.onSuccess(vehicleData);
                        } else {
                            String error = vinResponse.getErrorText();
                            if (error == null || error.isEmpty()) {
                                error = "Invalid VIN or no data available";
                            }
                            System.err.println(TAG + ": VIN decode error: " + error);
                            callback.onError(error);
                        }
                    } else {
                        System.err.println(TAG + ": No valid data in response");
                        callback.onError("No vehicle data found for this VIN");
                    }
                } else {
                    System.err.println(TAG + ": API call unsuccessful: " + response.code());
                    callback.onError("Failed to decode VIN. HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<VINResponse> call, Throwable t) {
                System.err.println(TAG + ": API call failed: " + t.getMessage());
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Decode a VIN with a specific model year
     *
     * @param vin The VIN to decode
     * @param modelYear The model year (helps with ambiguous decoding)
     * @param callback Callback for results
     */
    public void decodeVINWithYear(String vin, String modelYear, VINDecoderCallback callback) {
        if (vin == null || vin.trim().isEmpty()) {
            callback.onError("VIN cannot be empty");
            return;
        }

        final String normalizedVin = vin.trim().toUpperCase();
        final String cacheKey = normalizedVin + "_" + modelYear;

        // Check cache first
        if (cache.containsKey(cacheKey)) {
            VehicleData cached = cache.get(cacheKey);
            if (cached != null) {
                System.out.println(TAG + ": Returning cached VIN data for: " + normalizedVin + " year: " + modelYear);
                callback.onSuccess(cached);
                return;
            }
        }

        // Make API call with year
        System.out.println(TAG + ": Decoding VIN with year: " + normalizedVin + " " + modelYear);
        Call<VINResponse> call = apiService.decodeVINWithYear(normalizedVin, modelYear, "json");

        call.enqueue(new Callback<VINResponse>() {
            @Override
            public void onResponse(Call<VINResponse> call, Response<VINResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    VINResponse vinResponse = response.body();

                    if (vinResponse.hasValidData()) {
                        VehicleData vehicleData = vinResponse.getVehicleData();

                        if (vehicleData.isValid()) {
                            // Cache the result
                            cache.put(cacheKey, vehicleData);
                            cache.put(normalizedVin, vehicleData); // Also cache without year

                            System.out.println(TAG + ": Successfully decoded VIN: " + vehicleData.getDisplayName());
                            callback.onSuccess(vehicleData);
                        } else {
                            String error = vinResponse.getErrorText();
                            if (error == null || error.isEmpty()) {
                                error = "Invalid VIN or no data available";
                            }
                            System.err.println(TAG + ": VIN decode error: " + error);
                            callback.onError(error);
                        }
                    } else {
                        System.err.println(TAG + ": No valid data in response");
                        callback.onError("No vehicle data found for this VIN");
                    }
                } else {
                    System.err.println(TAG + ": API call unsuccessful: " + response.code());
                    callback.onError("Failed to decode VIN. HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<VINResponse> call, Throwable t) {
                System.err.println(TAG + ": API call failed: " + t.getMessage());
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Clear the VIN cache
     */
    public void clearCache() {
        cache.clear();
        recallCache.clear();
        System.out.println(TAG + ": VIN and recall cache cleared");
    }

    /**
     * Check if a VIN is cached
     *
     * @param vin The VIN to check
     * @return true if cached
     */
    public boolean isCached(String vin) {
        return vin != null && cache.containsKey(vin.trim().toUpperCase());
    }

    /**
     * Get cached vehicle data if available
     *
     * @param vin The VIN to get from cache
     * @return VehicleData or null if not cached
     */
    public VehicleData getCached(String vin) {
        if (vin == null) return null;
        return cache.get(vin.trim().toUpperCase());
    }

    /**
     * Get recall campaigns for a vehicle by make, model, and optional year
     *
     * @param make Vehicle make (e.g., "Honda")
     * @param model Vehicle model (e.g., "Accord")
     * @param modelYear Optional model year
     * @param callback Callback for results
     */
    public void getRecallsForVehicle(String make, String model, String modelYear, RecallCallback callback) {
        if (make == null || make.trim().isEmpty() || model == null || model.trim().isEmpty()) {
            callback.onError("Make and model are required");
            return;
        }

        final String normalizedMake = make.trim();
        final String normalizedModel = model.trim();
        final String cacheKey = normalizedMake + "_" + normalizedModel + "_" + (modelYear != null ? modelYear : "ALL");

        // Check cache first
        if (recallCache.containsKey(cacheKey)) {
            List<RecallRecord> cached = recallCache.get(cacheKey);
            if (cached != null) {
                System.out.println(TAG + ": Returning cached recall data for: " + cacheKey);
                callback.onSuccess(cached);
                return;
            }
        }

        // Make API call
        System.out.println(TAG + ": Fetching recalls for: " + normalizedMake + " " + normalizedModel + " " + modelYear);
        Call<RecallResponse> call;
        if (modelYear != null && !modelYear.trim().isEmpty()) {
            call = recallApiService.getRecallsByVehicle(normalizedMake, normalizedModel, modelYear);
        } else {
            call = recallApiService.getRecallsByVehicle(normalizedMake, normalizedModel);
        }

        call.enqueue(new Callback<RecallResponse>() {
            @Override
            public void onResponse(Call<RecallResponse> call, Response<RecallResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RecallResponse recallResponse = response.body();
                    List<RecallRecord> recalls = recallResponse.getResults();

                    if (recalls != null) {
                        // Cache the result
                        recallCache.put(cacheKey, recalls);

                        System.out.println(TAG + ": Found " + recalls.size() + " recalls");
                        callback.onSuccess(recalls);
                    } else {
                        System.out.println(TAG + ": No recalls found");
                        callback.onSuccess(new java.util.ArrayList<>());
                    }
                } else {
                    System.err.println(TAG + ": Recall API call unsuccessful: " + response.code());
                    callback.onError("Failed to fetch recalls. HTTP " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RecallResponse> call, Throwable t) {
                System.err.println(TAG + ": Recall API call failed: " + t.getMessage());
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Decode a VIN and enrich with recall information
     *
     * @param vin The VIN to decode
     * @param callback Callback for results with recalls included
     */
    public void getRecallsByVin(String vin, final VINDecoderCallback callback) {
        // First decode the VIN
        decodeVIN(vin, new VINDecoderCallback() {
            @Override
            public void onSuccess(final VehicleData vehicleData) {
                // Then get recalls if we have make and model
                if (vehicleData.getMake() != null && vehicleData.getModel() != null) {
                    getRecallsForVehicle(vehicleData.getMake(), vehicleData.getModel(),
                            vehicleData.getModelYear(), new RecallCallback() {
                        @Override
                        public void onSuccess(List<RecallRecord> recalls) {
                            // Add recalls to vehicle data
                            vehicleData.setRecalls(recalls);
                            System.out.println(TAG + ": VIN decoded with " + recalls.size() + " recalls");
                            callback.onSuccess(vehicleData);
                        }

                        @Override
                        public void onError(String error) {
                            // Return vehicle data without recalls on error
                            System.err.println(TAG + ": Failed to get recalls: " + error);
                            callback.onSuccess(vehicleData);
                        }
                    });
                } else {
                    // No make/model to look up recalls
                    System.out.println(TAG + ": No make/model for recall lookup");
                    callback.onSuccess(vehicleData);
                }
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Get recall campaigns for a vehicle by make and model (all years)
     *
     * @param make Vehicle make
     * @param model Vehicle model
     * @param callback Callback for results
     */
    public void getRecallsForVehicle(String make, String model, RecallCallback callback) {
        getRecallsForVehicle(make, model, null, callback);
    }
}
