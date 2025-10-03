package com.obddroid.api.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response model for vehicle makes API calls
 */
public class MakeResponse {

    @SerializedName("Count")
    public int count;

    @SerializedName("Message")
    public String message;

    @SerializedName("Results")
    public List<Make> results;

    public static class Make {
        @SerializedName("Make_ID")
        public int makeId;

        @SerializedName("Make_Name")
        public String makeName;

        @SerializedName("Mfr_Name")
        public String manufacturerName;
    }
}