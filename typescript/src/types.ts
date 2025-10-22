/**
 * Vehicle data model containing comprehensive vehicle information
 */
export interface VehicleData {
  // Core identification
  vin?: string;
  make?: string;
  manufacturer?: string;
  manufacturerName?: string;
  model?: string;
  modelYear?: string | number;

  // VIN structure
  wmi?: string;
  vds?: string;
  vis?: string;
  sequentialNumber?: string;

  // Location
  plantCity?: string;
  plantState?: string;
  plantCountry?: string;
  plantCompanyName?: string;
  region?: string;
  country?: string;

  // Body & dimensions
  bodyClass?: string;
  doors?: string | number;
  windows?: string | number;
  wheelBase?: string;
  wheelBaseShort?: string;
  wheelBaseLong?: string;
  trailerType?: string;
  trailerLength?: string;

  // Engine
  engineCylinders?: string | number;
  displacementCC?: string;
  displacementCI?: string;
  displacementL?: string;
  engineModel?: string;
  engineManufacturer?: string;
  engineConfiguration?: string;
  engineHP?: string | number;
  engineKW?: string | number;

  // Fuel
  fuelTypePrimary?: string;
  fuelTypeSecondary?: string;

  // Transmission & drivetrain
  transmissionStyle?: string;
  transmissionSpeeds?: string | number;
  driveType?: string;

  // Safety
  abs?: string;
  airBagLocCurtain?: string;
  airBagLocFront?: string;
  airBagLocSide?: string;
  seatBeltsAll?: string;

  // EV/Hybrid
  electrificationLevel?: string;
  evDriveUnit?: string;
  batteryType?: string;
  batteryA?: string;
  batteryV?: string;
  batteryKWh?: string;
  batteryModules?: string;
  batteryCells?: string;
  chargerLevel?: string;
  chargerPowerKW?: string;

  // Weight & capacity
  gvwr?: string;
  gvwrFrom?: string;
  gvwrTo?: string;
  curbWeightLB?: string | number;
  grossVehicleWeightRating?: string;

  // Trim & series
  trim?: string;
  trim2?: string;
  series?: string;
  series2?: string;

  // Type & classification
  vehicleType?: string;
  vehicleDescriptor?: string;
  errorCode?: string;
  errorText?: string;

  // Additional fields
  [key: string]: string | number | undefined;
}

/**
 * NHTSA API response structure
 */
export interface NHTSAResponse {
  Count: number;
  Message: string;
  SearchCriteria: string;
  Results: Array<Record<string, string | null>>;
}

/**
 * WMI (World Manufacturer Identifier) database entry
 */
export interface WMIEntry {
  wmi: string;
  manufacturer: string;
  country?: string;
  region?: string;
  vehicleType?: string;
}

/**
 * VIN validation result
 */
export interface ValidationResult {
  valid: boolean;
  errors: string[];
  warnings: string[];
}

/**
 * Decoder options
 */
export interface DecoderOptions {
  /**
   * Use online NHTSA API (default: false)
   */
  online?: boolean;

  /**
   * Cache duration in milliseconds (default: 3600000 = 1 hour)
   */
  cacheDuration?: number;

  /**
   * API base URL (default: NHTSA vPIC API)
   */
  apiBaseUrl?: string;

  /**
   * Timeout for API requests in milliseconds (default: 10000)
   */
  timeout?: number;
}

/**
 * Manufacturer-specific decoder interface
 */
export interface ManufacturerDecoder {
  canDecode(wmi: string): boolean;
  decode(vin: string, baseData: VehicleData): VehicleData;
}
