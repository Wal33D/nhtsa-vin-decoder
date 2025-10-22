import { VINValidator } from '../src/validator';

describe('VINValidator', () => {
  describe('validate', () => {
    it('should validate correct VIN', () => {
      const result = VINValidator.validate('1HGCM82633A123456');
      expect(result.valid).toBe(true);
      expect(result.errors).toHaveLength(0);
    });

    it('should reject empty VIN', () => {
      const result = VINValidator.validate('');
      expect(result.valid).toBe(false);
      expect(result.errors).toContain('VIN cannot be empty');
    });

    it('should reject VIN with wrong length', () => {
      const result = VINValidator.validate('1HGCM82633A');
      expect(result.valid).toBe(false);
      expect(result.errors[0]).toContain('must be exactly 17 characters');
    });

    it('should reject VIN with invalid characters (I, O, Q)', () => {
      const result = VINValidator.validate('1HGCM82633A12345I');
      expect(result.valid).toBe(false);
      expect(result.errors[0]).toContain('invalid characters');
    });

    it('should handle lowercase VIN', () => {
      const result = VINValidator.validate('1hgcm82633a123456');
      expect(result.valid).toBe(true);
    });
  });

  describe('isValid', () => {
    it('should return true for valid VIN', () => {
      expect(VINValidator.isValid('1HGCM82633A123456')).toBe(true);
    });

    it('should return false for invalid VIN', () => {
      expect(VINValidator.isValid('INVALID')).toBe(false);
    });
  });

  describe('getWMI', () => {
    it('should extract WMI correctly', () => {
      expect(VINValidator.getWMI('1HGCM82633A123456')).toBe('1HG');
      expect(VINValidator.getWMI('JH4KA7532PC000000')).toBe('JH4');
    });
  });

  describe('getVDS', () => {
    it('should extract VDS correctly', () => {
      expect(VINValidator.getVDS('1HGCM82633A123456')).toBe('CM8263');
    });
  });

  describe('getVIS', () => {
    it('should extract VIS correctly', () => {
      expect(VINValidator.getVIS('1HGCM82633A123456')).toBe('3A123456');
    });
  });

  describe('getModelYear', () => {
    it('should decode model year correctly', () => {
      // 2023 = P
      expect(VINValidator.getModelYear('1HGCM82633P123456')).toBe(2023);
      // 2019 = K
      expect(VINValidator.getModelYear('5J6RW2H89KA000000')).toBe(2019);
      // 2010 = A
      expect(VINValidator.getModelYear('1HGCM82633A123456')).toBe(2010);
    });

    it('should handle year codes A-Y and 1-9', () => {
      expect(VINValidator.decodeModelYear('A')).toBe(2010);
      expect(VINValidator.decodeModelYear('B')).toBe(2011);
      expect(VINValidator.decodeModelYear('Y')).toBe(2030);
      expect(VINValidator.decodeModelYear('1')).toBe(2031);
      expect(VINValidator.decodeModelYear('9')).toBe(2039);
    });
  });

  describe('getRegion', () => {
    it('should identify North American VINs', () => {
      expect(VINValidator.getRegion('1HGCM82633A123456')).toBe('North America');
      expect(VINValidator.getRegion('2HGCM82633A123456')).toBe('North America');
      expect(VINValidator.getRegion('5J6RW2H89KA000000')).toBe('North America');
    });

    it('should identify Asian VINs', () => {
      expect(VINValidator.getRegion('JH4KA7532PC000000')).toBe('Asia');
      expect(VINValidator.getRegion('KMHDU46D17U000000')).toBe('Asia');
    });

    it('should identify European VINs', () => {
      expect(VINValidator.getRegion('WBADT43452G000000')).toBe('Europe');
      expect(VINValidator.getRegion('ZFFXR48A000000000')).toBe('Europe');
    });
  });

  describe('getCountry', () => {
    it('should identify United States', () => {
      expect(VINValidator.getCountry('1HGCM82633A123456')).toBe('United States');
      expect(VINValidator.getCountry('4T1BF1FK0CU000000')).toBe('United States');
    });

    it('should identify Canada', () => {
      expect(VINValidator.getCountry('2HGCM82633A123456')).toBe('Canada');
    });

    it('should identify Japan', () => {
      expect(VINValidator.getCountry('JH4KA7532PC000000')).toBe('Japan');
    });

    it('should identify Germany', () => {
      expect(VINValidator.getCountry('WBADT43452G000000')).toBe('Germany');
    });
  });

  describe('isNorthAmericanVIN', () => {
    it('should identify North American VINs', () => {
      expect(VINValidator.isNorthAmericanVIN('1HGCM82633A123456')).toBe(true);
      expect(VINValidator.isNorthAmericanVIN('2HGCM82633A123456')).toBe(true);
      expect(VINValidator.isNorthAmericanVIN('5J6RW2H89KA000000')).toBe(true);
    });

    it('should reject non-North American VINs', () => {
      expect(VINValidator.isNorthAmericanVIN('JH4KA7532PC000000')).toBe(false);
      expect(VINValidator.isNorthAmericanVIN('WBADT43452G000000')).toBe(false);
    });
  });

  describe('calculateCheckDigit', () => {
    it('should calculate check digit correctly', () => {
      // Known VIN with check digit
      const vin = '1M8GDM9AXKP042788';
      const checkDigit = vin[8]; // Should be 'X'
      const calculated = VINValidator.calculateCheckDigit(vin);
      expect(calculated).toBe(checkDigit);
    });
  });

  describe('validateCheckDigit', () => {
    it('should validate correct check digit', () => {
      // Valid North American VIN
      expect(VINValidator.validateCheckDigit('1M8GDM9AXKP042788')).toBe(true);
    });

    it('should reject incorrect check digit', () => {
      // Modified check digit
      expect(VINValidator.validateCheckDigit('1M8GDM9A1KP042788')).toBe(false);
    });
  });
});
