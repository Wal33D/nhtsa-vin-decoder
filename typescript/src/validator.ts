import { ValidationResult } from './types';

/**
 * VIN Validator following ISO 3779 standard
 */
export class VINValidator {
  private static readonly VIN_LENGTH = 17;
  private static readonly INVALID_CHARS = /[IOQ]/;
  private static readonly VALID_CHARS = /^[A-HJ-NPR-Z0-9]{17}$/;

  // Transliteration table for check digit calculation
  private static readonly TRANSLITERATION: Record<string, number> = {
    A: 1, B: 2, C: 3, D: 4, E: 5, F: 6, G: 7, H: 8,
    J: 1, K: 2, L: 3, M: 4, N: 5, P: 7, R: 9,
    S: 2, T: 3, U: 4, V: 5, W: 6, X: 7, Y: 8, Z: 9,
    '0': 0, '1': 1, '2': 2, '3': 3, '4': 4,
    '5': 5, '6': 6, '7': 7, '8': 8, '9': 9
  };

  // Weight factors for check digit calculation
  private static readonly WEIGHTS = [8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2];

  /**
   * Validate a VIN number
   */
  static validate(vin: string): ValidationResult {
    const errors: string[] = [];
    const warnings: string[] = [];

    if (!vin) {
      errors.push('VIN cannot be empty');
      return { valid: false, errors, warnings };
    }

    const normalizedVin = vin.toUpperCase().trim();

    // Check length
    if (normalizedVin.length !== this.VIN_LENGTH) {
      errors.push(`VIN must be exactly ${this.VIN_LENGTH} characters (got ${normalizedVin.length})`);
      return { valid: false, errors, warnings };
    }

    // Check for invalid characters
    if (this.INVALID_CHARS.test(normalizedVin)) {
      errors.push('VIN contains invalid characters (I, O, Q are not allowed)');
    }

    // Check for valid characters overall
    if (!this.VALID_CHARS.test(normalizedVin)) {
      errors.push('VIN contains invalid characters (only A-Z and 0-9 allowed, excluding I, O, Q)');
    }

    // Validate check digit for North American VINs
    if (this.isNorthAmericanVIN(normalizedVin)) {
      if (!this.validateCheckDigit(normalizedVin)) {
        warnings.push('Check digit validation failed (VIN may be from a region that doesn\'t use check digits)');
      }
    }

    return {
      valid: errors.length === 0,
      errors,
      warnings
    };
  }

  /**
   * Check if VIN is valid
   */
  static isValid(vin: string): boolean {
    return this.validate(vin).valid;
  }

  /**
   * Validate check digit (position 9)
   */
  static validateCheckDigit(vin: string): boolean {
    const normalizedVin = vin.toUpperCase().trim();

    if (normalizedVin.length !== this.VIN_LENGTH) {
      return false;
    }

    const checkDigit = normalizedVin[8];
    const calculatedCheckDigit = this.calculateCheckDigit(normalizedVin);

    return checkDigit === calculatedCheckDigit;
  }

  /**
   * Calculate check digit for a VIN
   */
  static calculateCheckDigit(vin: string): string {
    const normalizedVin = vin.toUpperCase().trim();
    let sum = 0;

    for (let i = 0; i < this.VIN_LENGTH; i++) {
      const char = normalizedVin[i];
      const value = this.TRANSLITERATION[char];

      if (value === undefined) {
        return 'X'; // Invalid character
      }

      sum += value * this.WEIGHTS[i];
    }

    const remainder = sum % 11;
    return remainder === 10 ? 'X' : remainder.toString();
  }

  /**
   * Check if VIN is from North America (WMI starts with 1-5)
   */
  static isNorthAmericanVIN(vin: string): boolean {
    const firstChar = vin[0];
    return firstChar >= '1' && firstChar <= '5';
  }

  /**
   * Extract WMI (World Manufacturer Identifier) - first 3 characters
   */
  static getWMI(vin: string): string {
    return vin.substring(0, 3).toUpperCase();
  }

  /**
   * Extract VDS (Vehicle Descriptor Section) - characters 4-9
   */
  static getVDS(vin: string): string {
    return vin.substring(3, 9).toUpperCase();
  }

  /**
   * Extract VIS (Vehicle Identifier Section) - characters 10-17
   */
  static getVIS(vin: string): string {
    return vin.substring(9, 17).toUpperCase();
  }

  /**
   * Extract model year character (position 10)
   */
  static getModelYearChar(vin: string): string {
    return vin[9].toUpperCase();
  }

  /**
   * Extract plant code (position 11)
   */
  static getPlantCode(vin: string): string {
    return vin[10].toUpperCase();
  }

  /**
   * Extract sequential number (positions 12-17)
   */
  static getSequentialNumber(vin: string): string {
    return vin.substring(11, 17);
  }

  /**
   * Get model year from VIN (supports 1980-2039)
   */
  static getModelYear(vin: string): number | null {
    const yearChar = this.getModelYearChar(vin);
    return this.decodeModelYear(yearChar);
  }

  /**
   * Decode model year from year character
   */
  static decodeModelYear(yearChar: string): number | null {
    // Year code mapping for 1980-2039
    const yearCodes: Record<string, number[]> = {
      A: [1980, 2010], B: [1981, 2011], C: [1982, 2012], D: [1983, 2013],
      E: [1984, 2014], F: [1985, 2015], G: [1986, 2016], H: [1987, 2017],
      J: [1988, 2018], K: [1989, 2019], L: [1990, 2020], M: [1991, 2021],
      N: [1992, 2022], P: [1993, 2023], R: [1994, 2024], S: [1995, 2025],
      T: [1996, 2026], V: [1997, 2027], W: [1998, 2028], X: [1999, 2029],
      Y: [2000, 2030],
      '1': [2001, 2031], '2': [2002, 2032], '3': [2003, 2033], '4': [2004, 2034],
      '5': [2005, 2035], '6': [2006, 2036], '7': [2007, 2037], '8': [2008, 2038],
      '9': [2009, 2039]
    };

    const years = yearCodes[yearChar.toUpperCase()];

    if (!years) {
      return null;
    }

    // Return the more recent year by default (can be refined with additional context)
    const currentYear = new Date().getFullYear();
    return years[1] <= currentYear + 1 ? years[1] : years[0];
  }

  /**
   * Get region from WMI
   */
  static getRegion(vin: string): string {
    const firstChar = vin[0].toUpperCase();

    if (firstChar >= '1' && firstChar <= '5') return 'North America';
    if (firstChar >= '6' && firstChar <= '7') return 'Oceania';
    if (firstChar >= '8' && firstChar <= '9') return 'South America';
    if (firstChar >= 'A' && firstChar <= 'E') return 'Africa';
    if (firstChar >= 'F' && firstChar <= 'K') return 'Asia';
    if (firstChar >= 'L' && firstChar <= 'R') return 'Europe';
    if (firstChar >= 'S' && firstChar <= 'Z') return 'Europe';

    return 'Unknown';
  }

  /**
   * Get country from WMI (basic mapping)
   */
  static getCountry(vin: string): string {
    const firstChar = vin[0].toUpperCase();
    const secondChar = vin[1].toUpperCase();

    // North America
    if (firstChar === '1' || firstChar === '4' || firstChar === '5') return 'United States';
    if (firstChar === '2') return 'Canada';
    if (firstChar === '3') return 'Mexico';

    // Europe - partial mapping
    if (firstChar === 'S' && secondChar >= 'A' && secondChar <= 'M') return 'United Kingdom';
    if (firstChar === 'S' && secondChar >= 'N' && secondChar <= 'T') return 'Germany';
    if (firstChar === 'V' && secondChar >= 'F' && secondChar <= 'R') return 'France';
    if (firstChar === 'W') return 'Germany';
    if (firstChar === 'Z') return 'Italy';

    // Asia
    if (firstChar === 'J') return 'Japan';
    if (firstChar === 'K') return 'South Korea';
    if (firstChar === 'L') return 'China';

    return this.getRegion(vin);
  }
}
