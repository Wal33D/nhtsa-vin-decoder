package com.obddroid.api.nhtsa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * NHTSA (National Highway Traffic Safety Administration) VIN Decoder API Service
 *
 * This interface defines the API endpoints for decoding Vehicle Identification Numbers (VINs)
 * using the official NHTSA vPIC (Product Information Catalog and Vehicle Listing) API.
 *
 * API Documentation: https://vpic.nhtsa.dot.gov/api/
 */
public interface NHTSAApiService {

    /**
     * Decode a VIN and return all available vehicle information
     *
     * This method decodes the VIN and returns decoded output in Key-value pairs.
     * Supports partial VIN decoding (VINs less than 17 characters).
     *
     * @param vin The Vehicle Identification Number to decode (17 characters for full VIN)
     * @param format Response format - should be "json" for JSON response
     * @return Call object containing the VIN response with all decoded vehicle information
     */
    @GET("vehicles/DecodeVinValues/{vin}")
    Call<VINResponse> decodeVIN(
        @Path("vin") String vin,
        @Query("format") String format
    );

    /**
     * Decode VIN with model year specification
     *
     * Allows decoding to specifically be done in the current, or older (pre-1980),
     * model year ranges. Recommended to always send in the model year when known.
     *
     * @param vin The Vehicle Identification Number to decode
     * @param modelYear The model year of the vehicle (optional but recommended)
     * @param format Response format - should be "json"
     * @return Call object containing the VIN response
     */
    @GET("vehicles/DecodeVinValues/{vin}")
    Call<VINResponse> decodeVINWithYear(
        @Path("vin") String vin,
        @Query("modelyear") String modelYear,
        @Query("format") String format
    );

    /**
     * Get all manufacturers
     *
     * Returns a list of all the Manufacturers available in vPIC Dataset.
     *
     * @param format Response format - should be "json"
     * @return Call object containing manufacturer list
     */
    @GET("vehicles/GetAllManufacturers")
    Call<ManufacturerResponse> getAllManufacturers(
        @Query("format") String format
    );

    /**
     * Get all makes for a specific manufacturer
     *
     * @param manufacturerId The manufacturer ID
     * @param format Response format - should be "json"
     * @return Call object containing makes list
     */
    @GET("vehicles/GetMakeForManufacturer/{manufacturerId}")
    Call<MakeResponse> getMakesForManufacturer(
        @Path("manufacturerId") int manufacturerId,
        @Query("format") String format
    );
}