package io.github.vindecoder.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.List;

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

    @SerializedName("ManufacturerName")
    public String manufacturerName;

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

    @SerializedName("PlantCode")
    public String plantCode;

    @SerializedName("VehicleType")
    public String vehicleType;

    // VIN Structure Information
    @SerializedName("WMI")
    public String wmi;

    @SerializedName("VDS")
    public String vds;

    @SerializedName("VIS")
    public String vis;

    @SerializedName("SequentialNumber")
    public String sequentialNumber;

    // Region Information
    @SerializedName("Region")
    public String region;

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

    // API Response Metadata
    @SerializedName("Count")
    public Integer count;

    @SerializedName("Message")
    public String message;

    @SerializedName("SearchCriteria")
    public String searchCriteria;

    @SerializedName("Results")
    public List<Result> results;

    // Validation status
    public boolean valid;

    // Error Information
    @SerializedName("ErrorCode")
    public String errorCode;

    @SerializedName("ErrorText")
    public String errorText;

    @SerializedName("SuggestedVIN")
    public String suggestedVIN;

    // Recall Information (added)
    private transient List<RecallRecord> recalls;

    /**
     * Result class for API response
     */
    public static class Result {
        @SerializedName("Value")
        public String value;

        @SerializedName("Variable")
        public String variable;

        @SerializedName("VariableId")
        public String variableId;

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }

        public String getVariable() { return variable; }
        public void setVariable(String variable) { this.variable = variable; }

        public String getVariableId() { return variableId; }
        public void setVariableId(String variableId) { this.variableId = variableId; }
    }

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
        return valid || (vin != null && !vin.isEmpty()
                && (make != null || manufacturer != null)
                && errorCode != null && errorCode.equals("0"));
    }

    // Getter/Setter methods for compatibility

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getManufacturerName() { return manufacturerName; }
    public void setManufacturerName(String name) { this.manufacturerName = name; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getModelYear() { return modelYear; }
    public void setModelYear(String modelYear) { this.modelYear = modelYear; }

    public String getPlantCity() { return plantCity; }
    public void setPlantCity(String plantCity) { this.plantCity = plantCity; }

    public String getPlantCountry() { return plantCountry; }
    public void setPlantCountry(String plantCountry) { this.plantCountry = plantCountry; }

    public String getPlantCode() { return plantCode; }
    public void setPlantCode(String plantCode) { this.plantCode = plantCode; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getWmi() { return wmi; }
    public void setWmi(String wmi) { this.wmi = wmi; }

    public String getVds() { return vds; }
    public void setVds(String vds) { this.vds = vds; }

    public String getVis() { return vis; }
    public void setVis(String vis) { this.vis = vis; }

    public String getSequentialNumber() { return sequentialNumber; }
    public void setSequentialNumber(String sequentialNumber) { this.sequentialNumber = sequentialNumber; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getErrorText() { return errorText; }
    public void setErrorText(String errorText) { this.errorText = errorText; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }

    public void setValid(boolean valid) { this.valid = valid; }

    // Additional setters for offline decoder support
    public void setPlantState(String plantState) { this.plantState = plantState; }
    public void setBodyClass(String bodyClass) { this.bodyClass = bodyClass; }
    public void setDoors(String doors) { this.doors = doors; }
    public void setDriveType(String driveType) { this.driveType = driveType; }
    public void setEngineModel(String engineModel) { this.engineModel = engineModel; }
    public void setEngineCylinders(String engineCylinders) { this.engineCylinders = engineCylinders; }
    public void setDisplacementL(String displacementL) { this.displacementL = displacementL; }
    public void setFuelTypePrimary(String fuelTypePrimary) { this.fuelTypePrimary = fuelTypePrimary; }
    public void setTransmissionStyle(String transmissionStyle) { this.transmissionStyle = transmissionStyle; }
    public void setTransmissionSpeeds(String transmissionSpeeds) { this.transmissionSpeeds = transmissionSpeeds; }
    public void setTrim(String trim) { this.trim = trim; }
    public void setGvwr(String gvwr) { this.gvwr = gvwr; }
    public void setCurbWeight(String curbWeight) { this.curbWeight = curbWeight; }

    // Recall Information methods
    public List<RecallRecord> getRecalls() { return recalls; }
    public void setRecalls(List<RecallRecord> recalls) { this.recalls = recalls; }

    /**
     * Check if vehicle has any recalls
     * @return true if recalls list is not null and not empty
     */
    public boolean hasRecalls() {
        return recalls != null && !recalls.isEmpty();
    }

    /**
     * Count the number of recalls
     * @return number of recalls, or 0 if none
     */
    public int getRecallCount() {
        return recalls != null ? recalls.size() : 0;
    }
}
