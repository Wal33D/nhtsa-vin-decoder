package com.obddroid.api.nhtsa;

import com.google.gson.annotations.SerializedName;

/**
 * Vehicle data model containing all decoded VIN information from NHTSA API
 *
 * This class contains all possible fields returned by the NHTSA VIN decoder.
 * Not all fields will be populated for every VIN.
 */
public class VehicleData {

    // Core Vehicle Information
    @SerializedName("VIN")
    public String vin;

    @SerializedName("Make")
    public String make;

    @SerializedName("Manufacturer")
    public String manufacturer;

    @SerializedName("Model")
    public String model;

    @SerializedName("ModelYear")
    public String modelYear;

    @SerializedName("PlantCity")
    public String plantCity;

    @SerializedName("PlantCountry")
    public String plantCountry;

    @SerializedName("PlantState")
    public String plantState;

    @SerializedName("VehicleType")
    public String vehicleType;

    // Body and Structure
    @SerializedName("BodyClass")
    public String bodyClass;

    @SerializedName("Doors")
    public String doors;

    @SerializedName("Windows")
    public String windows;

    @SerializedName("WheelBase")
    public String wheelBase;

    @SerializedName("TrailerLength")
    public String trailerLength;

    // Engine Information
    @SerializedName("EngineCylinders")
    public String engineCylinders;

    @SerializedName("DisplacementCC")
    public String displacementCC;

    @SerializedName("DisplacementCI")
    public String displacementCI;

    @SerializedName("DisplacementL")
    public String displacementL;

    @SerializedName("EngineModel")
    public String engineModel;

    @SerializedName("EngineManufacturer")
    public String engineManufacturer;

    @SerializedName("FuelTypePrimary")
    public String fuelTypePrimary;

    @SerializedName("FuelTypeSecondary")
    public String fuelTypeSecondary;

    // Drive and Transmission
    @SerializedName("DriveType")
    public String driveType;

    @SerializedName("TransmissionStyle")
    public String transmissionStyle;

    @SerializedName("TransmissionSpeeds")
    public String transmissionSpeeds;

    // Safety Features
    @SerializedName("ABS")
    public String abs;

    @SerializedName("AirBagLocCurtain")
    public String airBagLocCurtain;

    @SerializedName("AirBagLocFront")
    public String airBagLocFront;

    @SerializedName("AirBagLocKnee")
    public String airBagLocKnee;

    @SerializedName("AirBagLocSeatCushion")
    public String airBagLocSeatCushion;

    @SerializedName("AirBagLocSide")
    public String airBagLocSide;

    @SerializedName("SeatBeltsAll")
    public String seatBeltsAll;

    // Electric Vehicle Information
    @SerializedName("ElectrificationLevel")
    public String electrificationLevel;

    @SerializedName("ChargerLevel")
    public String chargerLevel;

    @SerializedName("BatteryType")
    public String batteryType;

    @SerializedName("BatteryKWh")
    public String batteryKWh;

    // Weight and Size
    @SerializedName("GVWR")
    public String gvwr;

    @SerializedName("CurbWeight")
    public String curbWeight;

    // Additional Information
    @SerializedName("Series")
    public String series;

    @SerializedName("Series2")
    public String series2;

    @SerializedName("Trim")
    public String trim;

    @SerializedName("Trim2")
    public String trim2;

    @SerializedName("Note")
    public String note;

    // Error Information
    @SerializedName("ErrorCode")
    public String errorCode;

    @SerializedName("ErrorText")
    public String errorText;

    @SerializedName("SuggestedVIN")
    public String suggestedVIN;

    /**
     * Get a formatted display string for the vehicle
     * @return Formatted string with make, model, and year
     */
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();

        if (modelYear != null && !modelYear.isEmpty()) {
            sb.append(modelYear).append(" ");
        }

        if (make != null && !make.isEmpty()) {
            sb.append(make).append(" ");
        }

        if (model != null && !model.isEmpty()) {
            sb.append(model);
        }

        if (trim != null && !trim.isEmpty() && !trim.equals("Not Applicable")) {
            sb.append(" ").append(trim);
        }

        return sb.toString().trim();
    }

    /**
     * Get engine description
     * @return Formatted engine information
     */
    public String getEngineDescription() {
        StringBuilder sb = new StringBuilder();

        if (displacementL != null && !displacementL.isEmpty()) {
            sb.append(displacementL).append("L");
        }

        if (engineCylinders != null && !engineCylinders.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(engineCylinders).append("-cylinder");
        }

        if (fuelTypePrimary != null && !fuelTypePrimary.isEmpty()
                && !fuelTypePrimary.equals("Not Applicable")) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(fuelTypePrimary);
        }

        return sb.toString();
    }

    /**
     * Check if this is a valid decode result
     * @return true if essential fields are present
     */
    public boolean isValid() {
        return vin != null && !vin.isEmpty()
                && (make != null || manufacturer != null)
                && errorCode != null && errorCode.equals("0");
    }
}