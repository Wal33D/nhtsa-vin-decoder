package com.obddroid.api.offline;

import com.obddroid.api.nhtsa.VehicleData;
import java.util.ArrayList;
import java.util.List;

/**
 * Offline VIN Decoder
 *
 * World-class offline VIN decoding with comprehensive manufacturer database
 * and validation according to ISO 3779 and NHTSA standards
 */
public class OfflineVINDecoder {

    private final WMIDatabase wmiDatabase;

    public OfflineVINDecoder() {
        this.wmiDatabase = new WMIDatabase();
    }

    /**
     * Decodes a VIN offline using WMI database and VIN structure analysis
     * @param vin The Vehicle Identification Number
     * @return VehicleData with decoded information
     */
    public VehicleData decode(String vin) {
        VehicleData vehicleData = new VehicleData();

        if (vin == null || vin.length() != 17) {
            vehicleData.setErrorCode("INVALID_VIN");
            vehicleData.setErrorText("VIN must be exactly 17 characters");
            return vehicleData;
        }

        vin = vin.toUpperCase();
        vehicleData.setVin(vin);

        // Validate VIN
        boolean isValid = VINValidator.isValidVIN(vin);
        vehicleData.setValid(isValid);

        if (!isValid) {
            vehicleData.setErrorCode("INVALID_FORMAT");
            vehicleData.setErrorText("VIN failed validation check");
        }

        // Extract WMI and get manufacturer
        String wmi = VINValidator.getWMI(vin);
        String manufacturer = null;

        if (wmi != null) {
            manufacturer = wmiDatabase.getManufacturer(wmi);
            if (manufacturer != null) {
                vehicleData.setMake(manufacturer);
                vehicleData.setManufacturerName(manufacturer);
            } else {
                // Try with first 2 characters for some manufacturers
                String wmi2 = wmi.substring(0, 2);
                manufacturer = wmiDatabase.getManufacturerByPrefix(wmi2);
                if (manufacturer != null) {
                    vehicleData.setMake(manufacturer);
                    vehicleData.setManufacturerName(manufacturer);
                }
            }
        }

        // Decode model year
        Integer modelYear = VINValidator.getModelYear(vin);
        if (modelYear != null) {
            vehicleData.setModelYear(String.valueOf(modelYear));
        }

        // Get region and country
        String region = VINValidator.getRegion(vin);
        vehicleData.setRegion(region);

        String country = VINValidator.getCountry(vin);
        vehicleData.setPlantCountry(country);

        // Get plant code
        String plantCode = VINValidator.getPlantCode(vin);
        vehicleData.setPlantCode(plantCode);

        // Get production number
        String productionNumber = VINValidator.getProductionNumber(vin);
        vehicleData.setSequentialNumber(productionNumber);

        // Extract VDS and VIS sections
        String vds = VINValidator.getVDS(vin);
        String vis = VINValidator.getVIS(vin);

        // Use manufacturer-specific decoder for enhanced information
        if (manufacturer != null && (manufacturer.contains("Mercedes") || wmi.startsWith("4JG") || wmi.startsWith("WD"))) {
            // Use Mercedes-Benz specific decoder
            MercedesBenzDecoder.VehicleInfo mbInfo = MercedesBenzDecoder.decode(vin);
            if (mbInfo != null) {
                // Enhanced model and series information
                if (mbInfo.model != null) vehicleData.setModel(mbInfo.model);
                if (mbInfo.series != null) vehicleData.setTrim(mbInfo.series);
                if (mbInfo.bodyClass != null) vehicleData.setBodyClass(mbInfo.bodyClass);
                if (mbInfo.driveType != null) vehicleData.setDriveType(mbInfo.driveType);
                if (mbInfo.doors != null) vehicleData.setDoors(mbInfo.doors);
                if (mbInfo.engineDescription != null) {
                    vehicleData.setEngineModel(mbInfo.engineDescription);
                    // Extract displacement from description
                    if (mbInfo.engineDescription.contains("2.0L")) {
                        vehicleData.setDisplacementL("2.0");
                        vehicleData.setEngineCylinders("4");
                    } else if (mbInfo.engineDescription.contains("3.0L")) {
                        vehicleData.setDisplacementL("3.0");
                        vehicleData.setEngineCylinders("6");
                    } else if (mbInfo.engineDescription.contains("3.5L")) {
                        vehicleData.setDisplacementL("3.5");
                        vehicleData.setEngineCylinders("6");
                    } else if (mbInfo.engineDescription.contains("4.0L")) {
                        vehicleData.setDisplacementL("4.0");
                        vehicleData.setEngineCylinders("8");
                    }
                    // Set fuel type
                    if (mbInfo.engineDescription.contains("Electric")) {
                        vehicleData.setFuelTypePrimary("Electric");
                    } else if (mbInfo.engineDescription.contains("Hybrid")) {
                        vehicleData.setFuelTypePrimary("Gasoline/Electric Hybrid");
                    } else if (mbInfo.engineDescription.contains("Diesel")) {
                        vehicleData.setFuelTypePrimary("Diesel");
                    } else {
                        vehicleData.setFuelTypePrimary("Gasoline");
                    }
                }
                if (mbInfo.transmissionStyle != null) vehicleData.setTransmissionStyle(mbInfo.transmissionStyle);
                if (mbInfo.transmissionSpeeds != null) vehicleData.setTransmissionSpeeds(mbInfo.transmissionSpeeds);
                if (mbInfo.plantCity != null) vehicleData.setPlantCity(mbInfo.plantCity);
                if (mbInfo.plantState != null) vehicleData.setPlantState(mbInfo.plantState);
                if (mbInfo.gvwr != null) vehicleData.setGvwr(mbInfo.gvwr);
                if (mbInfo.curbWeight != null) vehicleData.setCurbWeight(mbInfo.curbWeight);
            }
        }

        // Determine vehicle type if not already set
        String vehicleType = vehicleData.getVehicleType();
        if (vehicleType == null || vehicleType.isEmpty()) {
            vehicleType = determineVehicleType(vehicleData.getMake(), vds);
            vehicleData.setVehicleType(vehicleType);
        }

        // Set additional fields
        vehicleData.setWmi(wmi);
        vehicleData.setVds(vds);
        vehicleData.setVis(vis);

        // Add decoded results list for compatibility
        List<VehicleData.Result> results = new ArrayList<>();

        addResult(results, "VIN", vin);
        addResult(results, "Make", vehicleData.getMake());
        addResult(results, "Manufacturer Name", vehicleData.getManufacturerName());
        addResult(results, "Model Year", vehicleData.getModelYear());
        addResult(results, "Plant Country", vehicleData.getPlantCountry());
        addResult(results, "Plant Code", vehicleData.getPlantCode());
        addResult(results, "Vehicle Type", vehicleData.getVehicleType());
        addResult(results, "Region", vehicleData.getRegion());
        addResult(results, "WMI", wmi);
        addResult(results, "VDS", vds);
        addResult(results, "VIS", vis);
        addResult(results, "Sequential Number", productionNumber);
        addResult(results, "Valid", String.valueOf(isValid));

        vehicleData.setResults(results);
        vehicleData.setCount(results.size());
        vehicleData.setMessage("VIN decoded offline");

        return vehicleData;
    }

    /**
     * Quick validation check without full decode
     */
    public boolean validate(String vin) {
        return VINValidator.isValidVIN(vin);
    }

    /**
     * Gets just the manufacturer from a VIN
     */
    public String getManufacturer(String vin) {
        if (vin == null || vin.length() < 3) return null;

        String wmi = VINValidator.getWMI(vin);
        if (wmi != null) {
            String manufacturer = wmiDatabase.getManufacturer(wmi);
            if (manufacturer != null) {
                return manufacturer;
            }

            // Try 2-char prefix
            String wmi2 = wmi.substring(0, 2);
            return wmiDatabase.getManufacturerByPrefix(wmi2);
        }

        return null;
    }

    /**
     * Gets just the model year from a VIN
     */
    public Integer getModelYear(String vin) {
        return VINValidator.getModelYear(vin);
    }

    /**
     * Gets the country of manufacture
     */
    public String getCountry(String vin) {
        return VINValidator.getCountry(vin);
    }

    /**
     * Gets the manufacturing region
     */
    public String getRegion(String vin) {
        return VINValidator.getRegion(vin);
    }

    /**
     * Determines vehicle type based on manufacturer and VDS
     */
    private String determineVehicleType(String manufacturer, String vds) {
        if (manufacturer == null || vds == null) return "Passenger Car";

        // Common patterns for different vehicle types
        manufacturer = manufacturer.toUpperCase();

        // Trucks
        if (manufacturer.contains("TRUCK") ||
            manufacturer.contains("FREIGHTLINER") ||
            manufacturer.contains("KENWORTH") ||
            manufacturer.contains("PETERBILT") ||
            manufacturer.contains("MACK") ||
            manufacturer.contains("VOLVO TRUCK")) {
            return "Truck";
        }

        // Motorcycles
        if (manufacturer.contains("HARLEY") ||
            manufacturer.contains("YAMAHA") ||
            manufacturer.contains("HONDA MOTORCYCLE") ||
            manufacturer.contains("KAWASAKI") ||
            manufacturer.contains("SUZUKI MOTORCYCLE") ||
            manufacturer.contains("DUCATI") ||
            manufacturer.contains("BMW MOTORCYCLE")) {
            return "Motorcycle";
        }

        // Buses
        if (manufacturer.contains("BUS") ||
            manufacturer.contains("MOTOR COACH") ||
            manufacturer.contains("BLUE BIRD")) {
            return "Bus";
        }

        // Trailers
        if (manufacturer.contains("TRAILER") ||
            manufacturer.contains("UTILITY")) {
            return "Trailer";
        }

        // RVs
        if (manufacturer.contains("WINNEBAGO") ||
            manufacturer.contains("AIRSTREAM") ||
            manufacturer.contains("FLEETWOOD") ||
            manufacturer.contains("RECREATIONAL")) {
            return "Multipurpose Passenger Vehicle (MPV)";
        }

        // Check VDS for SUV/Truck indicators
        char vdsChar4 = vds.charAt(0);
        if (vdsChar4 == 'T' || vdsChar4 == 'K' || vdsChar4 == 'C') {
            return "Truck";
        }

        // Default to passenger car
        return "Passenger Car";
    }

    /**
     * Helper to add a result to the list
     */
    private void addResult(List<VehicleData.Result> results, String variable, String value) {
        if (value != null && !value.isEmpty()) {
            VehicleData.Result result = new VehicleData.Result();
            result.setVariable(variable);
            result.setValue(value);
            result.setVariableId(String.valueOf(results.size() + 1));
            results.add(result);
        }
    }

    /**
     * Checks if this is a North American VIN
     */
    public boolean isNorthAmericanVIN(String vin) {
        return VINValidator.isNorthAmericanVIN(vin);
    }

    /**
     * Gets all available information as a formatted string
     */
    public String getFormattedInfo(String vin) {
        VehicleData data = decode(vin);
        StringBuilder sb = new StringBuilder();

        sb.append("=== VIN DECODER RESULTS ===\n");
        sb.append("VIN: ").append(vin).append("\n");
        sb.append("Valid: ").append(data.isValid()).append("\n");

        if (data.getMake() != null) {
            sb.append("Make: ").append(data.getMake()).append("\n");
        }
        if (data.getModelYear() != null) {
            sb.append("Model Year: ").append(data.getModelYear()).append("\n");
        }
        if (data.getVehicleType() != null) {
            sb.append("Vehicle Type: ").append(data.getVehicleType()).append("\n");
        }
        if (data.getPlantCountry() != null) {
            sb.append("Country: ").append(data.getPlantCountry()).append("\n");
        }
        if (data.getRegion() != null) {
            sb.append("Region: ").append(data.getRegion()).append("\n");
        }
        if (data.getPlantCode() != null) {
            sb.append("Plant Code: ").append(data.getPlantCode()).append("\n");
        }
        if (data.getSequentialNumber() != null) {
            sb.append("Serial Number: ").append(data.getSequentialNumber()).append("\n");
        }

        sb.append("===========================");

        return sb.toString();
    }
}