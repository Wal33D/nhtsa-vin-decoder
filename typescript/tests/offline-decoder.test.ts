import { OfflineVINDecoder } from '../src/offline-decoder';

describe('OfflineVINDecoder', () => {
  let decoder: OfflineVINDecoder;

  beforeEach(() => {
    decoder = new OfflineVINDecoder();
  });

  describe('decode', () => {
    it('should decode Honda VIN', () => {
      const result = decoder.decode('1HGCM82633A123456');
      expect(result.vin).toBe('1HGCM82633A123456');
      expect(result.manufacturer).toBe('Honda');
      expect(result.wmi).toBe('1HG');
      expect(result.modelYear).toBe(2010);
      expect(result.region).toBe('North America');
      expect(result.country).toBe('United States');
    });

    it('should decode Acura VIN', () => {
      const result = decoder.decode('JH4KA7532PC000000');
      expect(result.manufacturer).toBe('Acura');
      expect(result.wmi).toBe('JH4');
      expect(result.region).toBe('Asia');
      expect(result.country).toBe('Japan');
    });

    it('should decode Toyota VIN', () => {
      const result = decoder.decode('5TDKZ3DC9HS123456');
      expect(result.manufacturer).toContain('Toyota');
      expect(result.wmi).toBe('5TD');
      expect(result.region).toBe('North America');
    });

    it('should decode Mercedes VIN', () => {
      const result = decoder.decode('WDDHF8HB1CA000000');
      expect(result.manufacturer).toContain('Mercedes');
      expect(result.wmi).toBe('WDD');
      expect(result.region).toBe('Europe');
      expect(result.country).toBe('Germany');
    });

    it('should throw error for empty VIN', () => {
      expect(() => decoder.decode('')).toThrow('VIN cannot be empty');
    });

    it('should throw error for invalid VIN', () => {
      expect(() => decoder.decode('INVALID')).toThrow('Invalid VIN');
    });

    it('should handle lowercase VIN', () => {
      const result = decoder.decode('1hgcm82633a123456');
      expect(result.vin).toBe('1HGCM82633A123456');
      expect(result.manufacturer).toBe('Honda');
    });
  });

  describe('getManufacturer', () => {
    it('should get manufacturer from exact WMI match', () => {
      expect(decoder.getManufacturer('1HG')).toBe('Honda');
      expect(decoder.getManufacturer('JH4')).toBe('Acura');
      expect(decoder.getManufacturer('WDD')).toContain('Mercedes');
    });

    it('should return Unknown for non-existent WMI', () => {
      expect(decoder.getManufacturer('XXX')).toBe('Unknown Manufacturer');
    });

    it('should handle lowercase WMI', () => {
      expect(decoder.getManufacturer('1hg')).toBe('Honda');
    });
  });

  describe('hasManufacturer', () => {
    it('should return true for known manufacturers', () => {
      expect(decoder.hasManufacturer('1HG')).toBe(true);
      expect(decoder.hasManufacturer('JH4')).toBe(true);
    });

    it('should return false for unknown manufacturers', () => {
      expect(decoder.hasManufacturer('XXX')).toBe(false);
    });
  });

  describe('getDatabaseSize', () => {
    it('should return correct database size', () => {
      const size = decoder.getDatabaseSize();
      expect(size).toBe(2015);
    });
  });

  describe('getAllManufacturers', () => {
    it('should return all manufacturers', () => {
      const manufacturers = decoder.getAllManufacturers();
      expect(manufacturers.length).toBeGreaterThan(0);
      expect(manufacturers).toContain('Honda');
      expect(manufacturers).toContain('Toyota');
      expect(manufacturers).toContain('Ford');
    });

    it('should return sorted list', () => {
      const manufacturers = decoder.getAllManufacturers();
      const sorted = [...manufacturers].sort();
      expect(manufacturers).toEqual(sorted);
    });
  });

  describe('searchManufacturers', () => {
    it('should find manufacturers by partial name', () => {
      const results = decoder.searchManufacturers('Honda');
      expect(results).toContain('Honda');
    });

    it('should be case insensitive', () => {
      const results = decoder.searchManufacturers('honda');
      expect(results).toContain('Honda');
    });

    it('should find multiple matches', () => {
      const results = decoder.searchManufacturers('BMW');
      expect(results.length).toBeGreaterThan(0);
    });

    it('should return empty array for no matches', () => {
      const results = decoder.searchManufacturers('NonExistentBrand');
      expect(results).toEqual([]);
    });
  });

  describe('getWMICodesForManufacturer', () => {
    it('should get all WMI codes for Honda', () => {
      const codes = decoder.getWMICodesForManufacturer('Honda');
      expect(codes.length).toBeGreaterThan(0);
      expect(codes).toContain('1HG');
    });

    it('should get all WMI codes for Acura', () => {
      const codes = decoder.getWMICodesForManufacturer('Acura');
      expect(codes.length).toBe(3); // Acura has 3 codes
      expect(codes).toContain('JH4');
      expect(codes).toContain('2HN');
      expect(codes).toContain('5J8');
    });

    it('should be case insensitive', () => {
      const codes = decoder.getWMICodesForManufacturer('honda');
      expect(codes.length).toBeGreaterThan(0);
    });
  });
});
