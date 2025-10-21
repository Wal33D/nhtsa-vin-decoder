package io.github.vindecoder.nhtsa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * NHTSA Recall API service interface for Retrofit
 * Uses the NHTSA Recall API v1
 *
 * @author Wal33D
 */
public interface NHTSARecallApiService {

    /**
     * Get recall campaigns by vehicle (make, model, and optional year)
     *
     * @param make      Vehicle make (e.g., "Honda")
     * @param model     Vehicle model (e.g., "Accord")
     * @param modelYear Optional model year (e.g., "2020")
     * @return Call<RecallResponse> containing list of recalls
     */
    @GET("recallsByVehicle")
    Call<RecallResponse> getRecallsByVehicle(
            @Query("make") String make,
            @Query("model") String model,
            @Query("modelYear") String modelYear
    );

    /**
     * Get recall campaigns by vehicle (make and model only)
     *
     * @param make  Vehicle make
     * @param model Vehicle model
     * @return Call<RecallResponse> containing list of recalls
     */
    @GET("recallsByVehicle")
    Call<RecallResponse> getRecallsByVehicle(
            @Query("make") String make,
            @Query("model") String model
    );

    /**
     * Get recall campaigns by NHTSA campaign number
     *
     * @param campaignNumber NHTSA campaign number
     * @return Call<RecallResponse> containing recall details
     */
    @GET("recalls/campaignNumber")
    Call<RecallResponse> getRecallByCampaignNumber(
            @Query("campaignNumber") String campaignNumber
    );
}