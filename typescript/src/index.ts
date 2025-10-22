/**
 * NHTSA VIN Decoder - TypeScript/JavaScript Implementation
 *
 * Advanced VIN decoder with offline decoding (2,015+ manufacturer codes)
 * and online NHTSA vPIC API integration
 *
 * @author Wal33D <aquataze@yahoo.com>
 * @version 2.1.0
 * @license MIT
 */

import { VehicleData, DecoderOptions, ValidationResult } from './types';
import { VINValidator } from './validator';
import { OfflineVINDecoder } from './offline-decoder';
import { OnlineVINDecoder } from './online-decoder';

/**
 * Main VIN Decoder class with unified API
 */
export class VINDecoder {
  private offlineDecoder: OfflineVINDecoder;
  private onlineDecoder: OnlineVINDecoder;
  private readonly options: DecoderOptions;

  constructor(options: DecoderOptions = {}) {
    this.options = options;
    this.offlineDecoder = new OfflineVINDecoder();
    this.onlineDecoder = new OnlineVINDecoder(options);
  }

  /**
   * Decode VIN with automatic fallback
   * Tries NHTSA API first, falls back to offline WMI database if API fails
   */
  async decode(vin: string, modelYear?: string): Promise<VehicleData> {
    // If explicitly set to offline-only, skip API
    if (this.options.online === false) {
      return Promise.resolve(this.offlineDecoder.decode(vin));
    }

    // Try NHTSA API first (default behavior)
    try {
      return await this.onlineDecoder.decode(vin, modelYear);
    } catch (error) {
      // API failed - fall back to offline decoder
      console.warn('NHTSA API failed, falling back to offline decoder:', error);
      return this.offlineDecoder.decode(vin);
    }
  }

  /**
   * Decode VIN using offline database only
   */
  decodeOffline(vin: string): VehicleData {
    return this.offlineDecoder.decode(vin);
  }

  /**
   * Decode VIN using NHTSA API only
   */
  async decodeOnline(vin: string, modelYear?: string): Promise<VehicleData> {
    return this.onlineDecoder.decode(vin, modelYear);
  }

  /**
   * Validate VIN
   */
  validate(vin: string): ValidationResult {
    return VINValidator.validate(vin);
  }

  /**
   * Check if VIN is valid
   */
  isValid(vin: string): boolean {
    return VINValidator.isValid(vin);
  }

  /**
   * Get manufacturer from WMI code
   */
  getManufacturer(wmiOrVin: string): string {
    const wmi = wmiOrVin.length === 17
      ? VINValidator.getWMI(wmiOrVin)
      : wmiOrVin.substring(0, 3).toUpperCase();
    return this.offlineDecoder.getManufacturer(wmi);
  }

  /**
   * Get model year from VIN
   */
  getModelYear(vin: string): number | null {
    return VINValidator.getModelYear(vin);
  }

  /**
   * Get region from VIN
   */
  getRegion(vin: string): string {
    return VINValidator.getRegion(vin);
  }

  /**
   * Get country from VIN
   */
  getCountry(vin: string): string {
    return VINValidator.getCountry(vin);
  }

  /**
   * Check if VIN is from North America
   */
  isNorthAmericanVIN(vin: string): boolean {
    return VINValidator.isNorthAmericanVIN(vin);
  }

  /**
   * Get WMI (World Manufacturer Identifier)
   */
  getWMI(vin: string): string {
    return VINValidator.getWMI(vin);
  }

  /**
   * Get VDS (Vehicle Descriptor Section)
   */
  getVDS(vin: string): string {
    return VINValidator.getVDS(vin);
  }

  /**
   * Get VIS (Vehicle Identifier Section)
   */
  getVIS(vin: string): string {
    return VINValidator.getVIS(vin);
  }

  /**
   * Get formatted VIN information as string
   */
  getFormattedInfo(vin: string): string {
    const validation = this.validate(vin);
    if (!validation.valid) {
      return `Invalid VIN: ${validation.errors.join(', ')}`;
    }

    const data = this.offlineDecoder.decode(vin);
    const lines: string[] = [];

    lines.push(`VIN: ${data.vin}`);
    lines.push(`Manufacturer: ${data.manufacturer}`);
    lines.push(`Model Year: ${data.modelYear || 'Unknown'}`);
    lines.push(`Region: ${data.region}`);
    lines.push(`Country: ${data.country}`);
    lines.push(`WMI: ${data.wmi}`);
    lines.push(`VDS: ${data.vds}`);
    lines.push(`VIS: ${data.vis}`);
    lines.push(`Sequential Number: ${data.sequentialNumber}`);

    return lines.join('\n');
  }

  /**
   * Search manufacturers by name
   */
  searchManufacturers(query: string): string[] {
    return this.offlineDecoder.searchManufacturers(query);
  }

  /**
   * Get all manufacturers in database
   */
  getAllManufacturers(): string[] {
    return this.offlineDecoder.getAllManufacturers();
  }

  /**
   * Get database size (number of WMI codes)
   */
  getDatabaseSize(): number {
    return this.offlineDecoder.getDatabaseSize();
  }

  /**
   * Clear API cache
   */
  clearCache(): void {
    this.onlineDecoder.clearCache();
  }

  /**
   * Check if VIN is cached
   */
  isCached(vin: string, modelYear?: string): boolean {
    return this.onlineDecoder.isCached(vin, modelYear);
  }
}

// Export all types and classes
export * from './types';
export { VINValidator } from './validator';
export { OfflineVINDecoder } from './offline-decoder';
export { OnlineVINDecoder } from './online-decoder';

// Export default instance
export default VINDecoder;
