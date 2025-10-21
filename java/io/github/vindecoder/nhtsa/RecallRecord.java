package io.github.vindecoder.nhtsa;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 * NHTSA recall campaign record
 * Contains detailed information about a vehicle safety recall
 *
 * @author Wal33D
 */
public class RecallRecord {

    @SerializedName("Manufacturer")
    private String manufacturer;

    @SerializedName("NHTSACampaignNumber")
    private String nhtsaCampaignNumber;

    @SerializedName("NHTSAActionNumber")
    private String nhtsaActionNumber;

    @SerializedName("ReportReceivedDate")
    private String reportReceivedDate;

    @SerializedName("Component")
    private String component;

    @SerializedName("Summary")
    private String summary;

    @SerializedName("Consequence")
    private String consequence;

    @SerializedName("Remedy")
    private String remedy;

    @SerializedName("Notes")
    private String notes;

    @SerializedName("ModelYear")
    private String modelYear;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName(value = "mfrRecallNumber", alternate = {"MfrRecallNumber"})
    private String mfrRecallNumber;

    @SerializedName(value = "overTheAirUpdate", alternate = {"overTheAirUpdateYn"})
    private Boolean overTheAirUpdate;

    @SerializedName("parkIt")
    private Boolean parkIt;

    @SerializedName(value = "parkOutSide", alternate = {"parkOutside", "parkOutsideYn"})
    private Boolean parkOutside;

    // Store the complete raw data
    private transient Map<String, Object> additionalFields;

    // Getters
    public String getManufacturer() {
        return manufacturer;
    }

    public String getNhtsaCampaignNumber() {
        return nhtsaCampaignNumber;
    }

    public String getNhtsaActionNumber() {
        return nhtsaActionNumber;
    }

    public String getReportReceivedDate() {
        return reportReceivedDate;
    }

    public String getComponent() {
        return component;
    }

    public String getSummary() {
        return summary;
    }

    public String getConsequence() {
        return consequence;
    }

    public String getRemedy() {
        return remedy;
    }

    public String getNotes() {
        return notes;
    }

    public String getModelYear() {
        return modelYear;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getMfrRecallNumber() {
        return mfrRecallNumber;
    }

    public Boolean getOverTheAirUpdate() {
        return overTheAirUpdate;
    }

    public Boolean getParkIt() {
        return parkIt;
    }

    public Boolean getParkOutside() {
        return parkOutside;
    }

    public Map<String, Object> getAdditionalFields() {
        return additionalFields;
    }

    // Setters
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setNhtsaCampaignNumber(String nhtsaCampaignNumber) {
        this.nhtsaCampaignNumber = nhtsaCampaignNumber;
    }

    public void setNhtsaActionNumber(String nhtsaActionNumber) {
        this.nhtsaActionNumber = nhtsaActionNumber;
    }

    public void setReportReceivedDate(String reportReceivedDate) {
        this.reportReceivedDate = reportReceivedDate;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMfrRecallNumber(String mfrRecallNumber) {
        this.mfrRecallNumber = mfrRecallNumber;
    }

    public void setOverTheAirUpdate(Boolean overTheAirUpdate) {
        this.overTheAirUpdate = overTheAirUpdate;
    }

    public void setParkIt(Boolean parkIt) {
        this.parkIt = parkIt;
    }

    public void setParkOutside(Boolean parkOutside) {
        this.parkOutside = parkOutside;
    }

    public void setAdditionalFields(Map<String, Object> additionalFields) {
        this.additionalFields = additionalFields;
    }

    /**
     * Check if this is a critical safety recall requiring immediate attention
     * @return true if parkIt or parkOutside is true
     */
    public boolean isCriticalSafety() {
        return Boolean.TRUE.equals(parkIt) || Boolean.TRUE.equals(parkOutside);
    }

    /**
     * Check if this recall can be resolved via over-the-air update
     * @return true if overTheAirUpdate is true
     */
    public boolean isOverTheAir() {
        return Boolean.TRUE.equals(overTheAirUpdate);
    }

    @Override
    public String toString() {
        return "RecallRecord{" +
                "campaignNumber='" + nhtsaCampaignNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + modelYear + '\'' +
                ", component='" + component + '\'' +
                ", critical=" + isCriticalSafety() +
                ", ota=" + isOverTheAir() +
                '}';
    }
}