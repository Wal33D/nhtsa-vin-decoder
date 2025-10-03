package io.github.vindecoder.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response model for NHTSA VIN Decoder API
 *
 * Contains the complete response structure from the NHTSA vPIC API
 * when decoding a Vehicle Identification Number.
 */
public class VINResponse {

    @SerializedName("Count")
    public int count;

    @SerializedName("Message")
    public String message;

    @SerializedName("SearchCriteria")
    public String searchCriteria;

    @SerializedName("Results")
    public List<VehicleData> results;

    /**
     * Check if the response contains valid vehicle data
     * @return true if results exist and contain at least one vehicle
     */
    public boolean hasValidData() {
        return results != null && !results.isEmpty() && results.get(0) != null;
    }

    /**
     * Get the first (and typically only) vehicle data result
     * @return VehicleData object or null if no results
     */
    public VehicleData getVehicleData() {
        return hasValidData() ? results.get(0) : null;
    }

    /**
     * Check if there were any errors in decoding
     * @return true if error codes exist
     */
    public boolean hasErrors() {
        if (hasValidData()) {
            VehicleData vehicle = results.get(0);
            return vehicle.errorCode != null && !vehicle.errorCode.equals("0");
        }
        return true;
    }

    /**
     * Get error text if any
     * @return Error text or null if no errors
     */
    public String getErrorText() {
        if (hasValidData()) {
            VehicleData vehicle = results.get(0);
            if (vehicle.errorText != null && !vehicle.errorText.isEmpty()) {
                return vehicle.errorText;
            }
        }
        return null;
    }
}
