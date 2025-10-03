package com.obddroid.api.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response model for manufacturer list API calls
 */
public class ManufacturerResponse {

    @SerializedName("Count")
    public int count;

    @SerializedName("Message")
    public String message;

    @SerializedName("Results")
    public List<Manufacturer> results;

    public static class Manufacturer {
        @SerializedName("Country")
        public String country;

        @SerializedName("Mfr_CommonName")
        public String commonName;

        @SerializedName("Mfr_ID")
        public int manufacturerId;

        @SerializedName("Mfr_Name")
        public String name;
    }
}