import axios, { AxiosInstance } from 'axios';
import { VehicleData, NHTSAResponse, DecoderOptions } from './types';
import { VINValidator } from './validator';

/**
 * Online VIN Decoder using NHTSA vPIC API
 */
export class OnlineVINDecoder {
  private readonly apiClient: AxiosInstance;
  private readonly cache: Map<string, { data: VehicleData; timestamp: number }>;
  private readonly cacheDuration: number;

  constructor(options: DecoderOptions = {}) {
    const baseURL = options.apiBaseUrl || 'https://vpic.nhtsa.dot.gov/api/vehicles';
    const timeout = options.timeout || 10000;
    this.cacheDuration = options.cacheDuration || 3600000; // 1 hour default

    this.apiClient = axios.create({
      baseURL,
      timeout,
      headers: {
        'Accept': 'application/json',
        'User-Agent': 'nhtsa-vin-decoder-ts/2.1.0'
      }
    });

    this.cache = new Map();
  }

  /**
   * Decode VIN using NHTSA API
   */
  async decode(vin: string, modelYear?: string): Promise<VehicleData> {
    if (!vin) {
      throw new Error('VIN cannot be empty');
    }

    const normalizedVin = vin.toUpperCase().trim();
    const validation = VINValidator.validate(normalizedVin);

    if (!validation.valid) {
      throw new Error(`Invalid VIN: ${validation.errors.join(', ')}`);
    }

    // Check cache
    const cacheKey = `${normalizedVin}:${modelYear || ''}`;
    const cached = this.getFromCache(cacheKey);
    if (cached) {
      return cached;
    }

    // Call API
    const endpoint = modelYear
      ? `/DecodeVinValues/${normalizedVin}?format=json&modelyear=${modelYear}`
      : `/DecodeVinValues/${normalizedVin}?format=json`;

    try {
      const response = await this.apiClient.get<NHTSAResponse>(endpoint);
      const vehicleData = this.parseResponse(response.data, normalizedVin);

      // Cache result
      this.cache.set(cacheKey, {
        data: vehicleData,
        timestamp: Date.now()
      });

      return vehicleData;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        throw new Error(`NHTSA API error: ${error.message}`);
      }
      throw error;
    }
  }

  /**
   * Parse NHTSA API response
   */
  private parseResponse(response: NHTSAResponse, vin: string): VehicleData {
    if (!response.Results || response.Results.length === 0) {
      throw new Error('No results from NHTSA API');
    }

    const result = response.Results[0];
    const data: VehicleData = { vin };

    // Map API fields to VehicleData
    const fieldMapping: Record<string, keyof VehicleData> = {
      'Make': 'make',
      'Manufacturer': 'manufacturer',
      'ManufacturerName': 'manufacturerName',
      'Model': 'model',
      'ModelYear': 'modelYear',
      'BodyClass': 'bodyClass',
      'Doors': 'doors',
      'Windows': 'windows',
      'WheelBase': 'wheelBase',
      'WheelBaseShort': 'wheelBaseShort',
      'WheelBaseLong': 'wheelBaseLong',
      'TrailerType': 'trailerType',
      'TrailerLength': 'trailerLength',
      'EngineCylinders': 'engineCylinders',
      'DisplacementCC': 'displacementCC',
      'DisplacementCI': 'displacementCI',
      'DisplacementL': 'displacementL',
      'EngineModel': 'engineModel',
      'EngineManufacturer': 'engineManufacturer',
      'EngineConfiguration': 'engineConfiguration',
      'EngineHP': 'engineHP',
      'EngineKW': 'engineKW',
      'FuelTypePrimary': 'fuelTypePrimary',
      'FuelTypeSecondary': 'fuelTypeSecondary',
      'TransmissionStyle': 'transmissionStyle',
      'TransmissionSpeeds': 'transmissionSpeeds',
      'DriveType': 'driveType',
      'ABS': 'abs',
      'AirBagLocCurtain': 'airBagLocCurtain',
      'AirBagLocFront': 'airBagLocFront',
      'AirBagLocSide': 'airBagLocSide',
      'SeatBeltsAll': 'seatBeltsAll',
      'ElectrificationLevel': 'electrificationLevel',
      'EVDriveUnit': 'evDriveUnit',
      'BatteryType': 'batteryType',
      'BatteryA': 'batteryA',
      'BatteryV': 'batteryV',
      'BatteryKWh': 'batteryKWh',
      'BatteryModules': 'batteryModules',
      'BatteryCells': 'batteryCells',
      'ChargerLevel': 'chargerLevel',
      'ChargerPowerKW': 'chargerPowerKW',
      'GVWR': 'gvwr',
      'GVWRFrom': 'gvwrFrom',
      'GVWRTo': 'gvwrTo',
      'CurbWeightLB': 'curbWeightLB',
      'Trim': 'trim',
      'Trim2': 'trim2',
      'Series': 'series',
      'Series2': 'series2',
      'VehicleType': 'vehicleType',
      'VehicleDescriptor': 'vehicleDescriptor',
      'PlantCity': 'plantCity',
      'PlantState': 'plantState',
      'PlantCountry': 'plantCountry',
      'PlantCompanyName': 'plantCompanyName',
      'ErrorCode': 'errorCode',
      'ErrorText': 'errorText'
    };

    // Map all fields
    for (const [apiField, dataField] of Object.entries(fieldMapping)) {
      const value = result[apiField];
      if (value !== null && value !== undefined && value !== '') {
        data[dataField] = value;
      }
    }

    // Add VIN structure
    data.wmi = VINValidator.getWMI(vin);
    data.vds = VINValidator.getVDS(vin);
    data.vis = VINValidator.getVIS(vin);
    data.sequentialNumber = VINValidator.getSequentialNumber(vin);

    // Add region/country
    data.region = VINValidator.getRegion(vin);
    if (!data.country) {
      data.country = VINValidator.getCountry(vin);
    }

    return data;
  }

  /**
   * Get manufacturers list
   */
  async getManufacturers(): Promise<Array<{ id: number; name: string; country: string }>> {
    try {
      const response = await this.apiClient.get('/GetAllManufacturers?format=json');
      return response.data.Results.map((m: any) => ({
        id: m.Mfr_ID,
        name: m.Mfr_Name,
        country: m.Country
      }));
    } catch (error) {
      if (axios.isAxiosError(error)) {
        throw new Error(`NHTSA API error: ${error.message}`);
      }
      throw error;
    }
  }

  /**
   * Get makes for a manufacturer
   */
  async getMakesForManufacturer(manufacturerId: number): Promise<string[]> {
    try {
      const response = await this.apiClient.get(
        `/GetMakeForManufacturer/${manufacturerId}?format=json`
      );
      return response.data.Results.map((m: any) => m.Make_Name);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        throw new Error(`NHTSA API error: ${error.message}`);
      }
      throw error;
    }
  }

  /**
   * Get from cache if not expired
   */
  private getFromCache(key: string): VehicleData | null {
    const cached = this.cache.get(key);
    if (!cached) return null;

    const age = Date.now() - cached.timestamp;
    if (age > this.cacheDuration) {
      this.cache.delete(key);
      return null;
    }

    return cached.data;
  }

  /**
   * Clear cache
   */
  clearCache(): void {
    this.cache.clear();
  }

  /**
   * Check if VIN is cached
   */
  isCached(vin: string, modelYear?: string): boolean {
    const cacheKey = `${vin.toUpperCase().trim()}:${modelYear || ''}`;
    const cached = this.cache.get(cacheKey);
    if (!cached) return false;

    const age = Date.now() - cached.timestamp;
    return age <= this.cacheDuration;
  }

  /**
   * Get cache size
   */
  getCacheSize(): number {
    return this.cache.size;
  }
}
