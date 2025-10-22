# NHTSA VIN Decoder - TypeScript/JavaScript

[![npm version](https://img.shields.io/npm/v/@wal33d/nhtsa-vin-decoder.svg)](https://www.npmjs.com/package/@wal33d/nhtsa-vin-decoder)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Advanced VIN decoder for Node.js and browsers with **offline decoding** (2,015+ manufacturer codes) and **online NHTSA vPIC API integration**.

## ✨ Features

- **🔌 Offline Mode**: Decode VINs without internet using comprehensive WMI database
- **🌐 Online Mode**: Full vehicle specs from NHTSA vPIC API
- **✅ VIN Validation**: ISO 3779 standard validation with check digit verification
- **📊 2,015+ Manufacturers**: Extensive WMI (World Manufacturer Identifier) database
- **🚀 High Performance**: Built-in caching for API calls
- **📘 TypeScript**: Full type definitions included
- **🧪 Well Tested**: Comprehensive test suite with Jest
- **🆓 Free**: No API key required for NHTSA API

## 📦 Installation

```bash
npm install @wal33d/nhtsa-vin-decoder
```

Or with yarn:

```bash
yarn add @wal33d/nhtsa-vin-decoder
```

## 🚀 Quick Start

### Automatic Decoding (Recommended - API with Fallback)

```typescript
import { VINDecoder } from '@wal33d/nhtsa-vin-decoder';

// By default, tries NHTSA API first, falls back to offline if API fails
const decoder = new VINDecoder();

const result = await decoder.decode('1HGCM82633A123456');
console.log(result);
// Complete vehicle data from NHTSA API including:
// - Make, Model, Year, Body type
// - Engine specs, transmission, drivetrain
// - Safety features, dimensions
// - Plant location, and more...
//
// If API fails (no internet, timeout, etc.):
// Falls back to offline WMI database automatically
```

### Offline-Only Mode (No Internet Required)

```typescript
import { VINDecoder } from '@wal33d/nhtsa-vin-decoder';

// Force offline mode - never calls API
const decoder = new VINDecoder({ online: false });

const result = await decoder.decode('1HGCM82633A123456');
console.log(result);
// {
//   vin: '1HGCM82633A123456',
//   manufacturer: 'Honda',
//   modelYear: 2010,
//   region: 'North America',
//   country: 'United States',
//   wmi: '1HG',
//   ...
// }
```

## 📖 API Reference

### VINDecoder

Main class for VIN decoding.

#### Constructor

```typescript
new VINDecoder(options?: DecoderOptions)
```

**Options:**
- `online?: boolean` - Use NHTSA API (default: `false`)
- `cacheDuration?: number` - Cache duration in ms (default: `3600000` = 1 hour)
- `apiBaseUrl?: string` - Custom API URL
- `timeout?: number` - Request timeout in ms (default: `10000`)

#### Methods

##### decode(vin: string, modelYear?: string): Promise\<VehicleData\>

Decode VIN using configured mode (offline or online).

```typescript
const result = await decoder.decode('1HGCM82633A123456');
```

##### decodeOffline(vin: string): VehicleData

Force offline decoding.

```typescript
const result = decoder.decodeOffline('1HGCM82633A123456');
```

##### decodeOnline(vin: string, modelYear?: string): Promise\<VehicleData\>

Force online decoding with NHTSA API.

```typescript
const result = await decoder.decodeOnline('1HGCM82633A123456');
```

##### validate(vin: string): ValidationResult

Validate VIN format and check digit.

```typescript
const validation = decoder.validate('1HGCM82633A123456');
console.log(validation.valid); // true
console.log(validation.errors); // []
console.log(validation.warnings); // []
```

##### isValid(vin: string): boolean

Quick VIN validation check.

```typescript
if (decoder.isValid('1HGCM82633A123456')) {
  // VIN is valid
}
```

##### getManufacturer(wmiOrVin: string): string

Get manufacturer from WMI code or full VIN.

```typescript
decoder.getManufacturer('1HG'); // 'Honda'
decoder.getManufacturer('1HGCM82633A123456'); // 'Honda'
```

##### getModelYear(vin: string): number | null

Extract model year from VIN (supports 1980-2039).

```typescript
decoder.getModelYear('1HGCM82633A123456'); // 2010
```

##### getRegion(vin: string): string

Get manufacturing region.

```typescript
decoder.getRegion('1HGCM82633A123456'); // 'North America'
```

##### getCountry(vin: string): string

Get manufacturing country.

```typescript
decoder.getCountry('1HGCM82633A123456'); // 'United States'
```

##### searchManufacturers(query: string): string[]

Search manufacturers by name.

```typescript
decoder.searchManufacturers('Honda'); // ['Honda', 'Honda Motorcycle', ...]
```

##### getAllManufacturers(): string[]

Get all manufacturers in database.

```typescript
const all = decoder.getAllManufacturers();
console.log(all.length); // 500+ unique manufacturers
```

##### getDatabaseSize(): number

Get total number of WMI codes.

```typescript
decoder.getDatabaseSize(); // 2015
```

## 🎯 Use Cases

### VIN Validation

```typescript
const validation = decoder.validate(vin);

if (!validation.valid) {
  console.error('Errors:', validation.errors);
}

if (validation.warnings.length > 0) {
  console.warn('Warnings:', validation.warnings);
}
```

### Manufacturer Lookup

```typescript
// From WMI code
const make = decoder.getManufacturer('1HG'); // 'Honda'

// From full VIN
const make2 = decoder.getManufacturer('1HGCM82633A123456'); // 'Honda'

// Search manufacturers
const results = decoder.searchManufacturers('BMW');
// ['BMW', 'BMW Motorrad', 'BMW of North America', ...]
```

### Year Decoding

```typescript
const year = decoder.getModelYear('1HGCM82633A123456');
console.log(year); // 2010

// Supports years 1980-2039
```

### VIN Components

```typescript
const wmi = decoder.getWMI(vin);        // 'World Manufacturer Identifier'
const vds = decoder.getVDS(vin);        // 'Vehicle Descriptor Section'
const vis = decoder.getVIS(vin);        // 'Vehicle Identifier Section'
```

## 📊 VehicleData Interface

```typescript
interface VehicleData {
  // Core identification
  vin?: string;
  make?: string;
  manufacturer?: string;
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
  region?: string;
  country?: string;

  // Body & dimensions
  bodyClass?: string;
  doors?: string | number;
  windows?: string | number;
  wheelBase?: string;

  // Engine
  engineCylinders?: string | number;
  displacementCC?: string;
  displacementL?: string;
  engineModel?: string;
  engineHP?: string | number;

  // Fuel
  fuelTypePrimary?: string;
  fuelTypeSecondary?: string;

  // Transmission
  transmissionStyle?: string;
  transmissionSpeeds?: string | number;
  driveType?: string;

  // Safety
  abs?: string;
  airBagLocFront?: string;
  airBagLocSide?: string;

  // EV/Hybrid
  electrificationLevel?: string;
  batteryKWh?: string;
  chargerPowerKW?: string;

  // Additional fields...
}
```

## 🧪 Testing

```bash
npm test                 # Run tests
npm run test:watch       # Watch mode
npm run test:coverage    # Coverage report
```

## 🏗️ Building

```bash
npm run build           # Build TypeScript to dist/
```

## 📄 License

MIT © [Wal33D](https://github.com/Wal33D)

## 🤝 Contributing

Contributions welcome! Please check out the [main repository](https://github.com/Wal33D/nhtsa-vin-decoder) for guidelines.

## 📚 Related Projects

- [NHTSA Recall Lookup](https://github.com/Wal33D/nhtsa-recall-lookup) - Vehicle recall campaign lookup

## ⭐ Support

If you find this library helpful, please give it a star on [GitHub](https://github.com/Wal33D/nhtsa-vin-decoder)!
