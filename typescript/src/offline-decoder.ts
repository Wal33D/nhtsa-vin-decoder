import { VehicleData, WMIEntry } from './types';
import { VINValidator } from './validator';
import wmiDatabase from './wmi-database.json';

/**
 * Offline VIN Decoder using WMI database
 * Supports 2,015+ manufacturer codes
 */
export class OfflineVINDecoder {
  private readonly wmiMap: Record<string, string>;

  constructor() {
    this.wmiMap = wmiDatabase as Record<string, string>;
  }

  /**
   * Decode VIN using offline database
   */
  decode(vin: string): VehicleData {
    if (!vin) {
      throw new Error('VIN cannot be empty');
    }

    const normalizedVin = vin.toUpperCase().trim();
    const validation = VINValidator.validate(normalizedVin);

    if (!validation.valid) {
      throw new Error(`Invalid VIN: ${validation.errors.join(', ')}`);
    }

    const data: VehicleData = {
      vin: normalizedVin
    };

    // Extract basic VIN structure
    data.wmi = VINValidator.getWMI(normalizedVin);
    data.vds = VINValidator.getVDS(normalizedVin);
    data.vis = VINValidator.getVIS(normalizedVin);
    data.sequentialNumber = VINValidator.getSequentialNumber(normalizedVin);

    // Get manufacturer from WMI database
    data.manufacturer = this.getManufacturer(data.wmi);
    data.make = data.manufacturer;

    // Get model year
    const modelYear = VINValidator.getModelYear(normalizedVin);
    if (modelYear) {
      data.modelYear = modelYear;
    }

    // Get region and country
    data.region = VINValidator.getRegion(normalizedVin);
    data.country = VINValidator.getCountry(normalizedVin);

    // Get plant code
    const plantCode = VINValidator.getPlantCode(normalizedVin);
    data.plantCountry = data.country;

    return data;
  }

  /**
   * Get manufacturer from WMI
   */
  getManufacturer(wmi: string): string {
    const normalized = wmi.toUpperCase().substring(0, 3);

    // Try exact match
    if (this.wmiMap[normalized]) {
      return this.wmiMap[normalized];
    }

    // Try 2-character prefix
    const twoChar = normalized.substring(0, 2);
    for (const [key, value] of Object.entries(this.wmiMap)) {
      if (key.startsWith(twoChar)) {
        return value;
      }
    }

    return 'Unknown Manufacturer';
  }

  /**
   * Check if manufacturer exists in database
   */
  hasManufacturer(wmi: string): boolean {
    return this.getManufacturer(wmi) !== 'Unknown Manufacturer';
  }

  /**
   * Get all WMI codes for a manufacturer
   */
  getWMICodesForManufacturer(manufacturer: string): string[] {
    const codes: string[] = [];
    const searchTerm = manufacturer.toLowerCase();

    for (const [wmi, mfr] of Object.entries(this.wmiMap)) {
      if (mfr.toLowerCase().includes(searchTerm)) {
        codes.push(wmi);
      }
    }

    return codes;
  }

  /**
   * Get total number of WMI codes in database
   */
  getDatabaseSize(): number {
    return Object.keys(this.wmiMap).length;
  }

  /**
   * Get all manufacturers in database
   */
  getAllManufacturers(): string[] {
    const manufacturers = new Set(Object.values(this.wmiMap));
    return Array.from(manufacturers).sort();
  }

  /**
   * Search manufacturers by partial name
   */
  searchManufacturers(query: string): string[] {
    const searchTerm = query.toLowerCase();
    const results = new Set<string>();

    for (const manufacturer of Object.values(this.wmiMap)) {
      if (manufacturer.toLowerCase().includes(searchTerm)) {
        results.add(manufacturer);
      }
    }

    return Array.from(results).sort();
  }
}
