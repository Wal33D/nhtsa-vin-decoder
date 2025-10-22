package io.github.vindecoder.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import io.github.vindecoder.nhtsa.VehicleData;
import io.github.vindecoder.offline.OfflineVINDecoder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Android wrapper for NHTSA VIN Decoder
 *
 * Provides easy-to-use Android interface for VIN decoding with:
 * - Offline decoding (no internet required)
 * - Main thread callbacks
 * - Context-aware lifecycle management
 * - Simple async API
 *
 * Example usage:
 * <pre>
 * VINDecoderAndroid decoder = new VINDecoderAndroid(context);
 * decoder.decodeAsync("1HGCM82633A004352", new VINDecoderAndroid.DecodeCallback() {
 *     {@literal @}Override
 *     public void onSuccess(VehicleData vehicle) {
 *         // Handle successful decode on main thread
 *         Log.d("VIN", vehicle.getModelYear() + " " + vehicle.getMake());
 *     }
 *
 *     {@literal @}Override
 *     public void onError(String error) {
 *         // Handle error on main thread
 *         Log.e("VIN", "Decode failed: " + error);
 *     }
 * });
 * </pre>
 */
public class VINDecoderAndroid {

    private final Context context;
    private final OfflineVINDecoder offlineDecoder;
    private final Executor executor;
    private final Handler mainHandler;

    /**
     * Callback interface for async VIN decoding
     */
    public interface DecodeCallback {
        /**
         * Called on success with decoded vehicle data (runs on main thread)
         */
        void onSuccess(VehicleData vehicle);

        /**
         * Called on error with error message (runs on main thread)
         */
        void onError(String error);
    }

    /**
     * Creates a new VINDecoderAndroid instance
     * @param context Android context
     */
    public VINDecoderAndroid(Context context) {
        this.context = context.getApplicationContext();
        this.offlineDecoder = new OfflineVINDecoder();
        this.executor = Executors.newFixedThreadPool(4);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Decodes a VIN synchronously (blocking call - use on background thread)
     *
     * @param vin The Vehicle Identification Number to decode
     * @return VehicleData with decoded information
     */
    public VehicleData decode(String vin) {
        return offlineDecoder.decode(vin);
    }

    /**
     * Decodes a VIN asynchronously with callback on main thread
     *
     * @param vin The Vehicle Identification Number to decode
     * @param callback Callback for result (called on main thread)
     */
    public void decodeAsync(String vin, DecodeCallback callback) {
        executor.execute(() -> {
            try {
                VehicleData result = offlineDecoder.decode(vin);
                mainHandler.post(() -> {
                    if (result != null && result.getMake() != null) {
                        callback.onSuccess(result);
                    } else {
                        callback.onError("Failed to decode VIN: " +
                            (result != null ? result.getErrorText() : "Unknown error"));
                    }
                });
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Exception during decode: " + e.getMessage()));
            }
        });
    }

    /**
     * Validates a VIN synchronously
     *
     * @param vin The VIN to validate
     * @return true if VIN is valid according to ISO 3779 standard
     */
    public boolean validate(String vin) {
        return offlineDecoder.validate(vin);
    }

    /**
     * Validates a VIN asynchronously
     *
     * @param vin The VIN to validate
     * @param callback Callback with validation result (called on main thread)
     */
    public void validateAsync(String vin, ValidationCallback callback) {
        executor.execute(() -> {
            boolean isValid = offlineDecoder.validate(vin);
            mainHandler.post(() -> callback.onValidationResult(isValid));
        });
    }

    /**
     * Gets manufacturer from VIN synchronously
     *
     * @param vin The VIN
     * @return Manufacturer name or null if not found
     */
    public String getManufacturer(String vin) {
        return offlineDecoder.getManufacturer(vin);
    }

    /**
     * Gets manufacturer from VIN asynchronously
     *
     * @param vin The VIN
     * @param callback Callback with manufacturer name
     */
    public void getManufacturerAsync(String vin, ManufacturerCallback callback) {
        executor.execute(() -> {
            String manufacturer = offlineDecoder.getManufacturer(vin);
            mainHandler.post(() -> callback.onManufacturerResult(manufacturer));
        });
    }

    /**
     * Gets model year from VIN synchronously
     *
     * @param vin The VIN
     * @return Model year (1980-2039) or null if cannot be determined
     */
    public Integer getModelYear(String vin) {
        return offlineDecoder.getModelYear(vin);
    }

    /**
     * Gets model year from VIN asynchronously
     *
     * @param vin The VIN
     * @param callback Callback with model year
     */
    public void getModelYearAsync(String vin, YearCallback callback) {
        executor.execute(() -> {
            Integer year = offlineDecoder.getModelYear(vin);
            mainHandler.post(() -> callback.onYearResult(year));
        });
    }

    /**
     * Gets country of manufacture synchronously
     *
     * @param vin The VIN
     * @return Country name or null
     */
    public String getCountry(String vin) {
        return offlineDecoder.getCountry(vin);
    }

    /**
     * Gets region (e.g., "North America") synchronously
     *
     * @param vin The VIN
     * @return Region name or null
     */
    public String getRegion(String vin) {
        return offlineDecoder.getRegion(vin);
    }

    /**
     * Gets formatted VIN information as a string
     *
     * @param vin The VIN
     * @return Formatted string with all decoded information
     */
    public String getFormattedInfo(String vin) {
        return offlineDecoder.getFormattedInfo(vin);
    }

    /**
     * Checks if this is a North American VIN (WMI starts with 1-5)
     *
     * @param vin The VIN
     * @return true if North American VIN
     */
    public boolean isNorthAmericanVIN(String vin) {
        return offlineDecoder.isNorthAmericanVIN(vin);
    }

    /**
     * Callback for validation result
     */
    public interface ValidationCallback {
        void onValidationResult(boolean isValid);
    }

    /**
     * Callback for manufacturer lookup
     */
    public interface ManufacturerCallback {
        void onManufacturerResult(String manufacturer);
    }

    /**
     * Callback for model year lookup
     */
    public interface YearCallback {
        void onYearResult(Integer year);
    }
}
