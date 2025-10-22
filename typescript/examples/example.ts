/**
 * NHTSA VIN Decoder - TypeScript Examples
 *
 * This file demonstrates how to use the nhtsa-vin-decoder library
 * in both offline and online modes.
 */

import { VINDecoder } from '../src';

// Example VINs
const VINS = {
  honda: '1HGCM82633A123456',
  acura: 'JH4KA7532PC000000',
  toyota: '5TDKZ3DC9HS123456',
  tesla: '5YJ3E1EA7KF000000',
  ford: '1FMCU0J95KUA00000',
  mercedes: 'WDDHF8HB1CA000000',
  bmw: 'WBADT43452G000000'
};

/**
 * Example 1: Basic offline decoding
 */
async function example1_offlineDecoding() {
  console.log('=== Example 1: Offline Decoding ===\n');

  const decoder = new VINDecoder({ online: false });

  for (const [brand, vin] of Object.entries(VINS)) {
    try {
      const result = await decoder.decode(vin);
      console.log(`${brand.toUpperCase()}:`);
      console.log(`  VIN: ${result.vin}`);
      console.log(`  Manufacturer: ${result.manufacturer}`);
      console.log(`  Year: ${result.modelYear}`);
      console.log(`  Region: ${result.region}`);
      console.log(`  Country: ${result.country}`);
      console.log();
    } catch (error) {
      console.error(`Error decoding ${brand}: ${error}`);
    }
  }
}

/**
 * Example 2: Online decoding with full NHTSA data
 */
async function example2_onlineDecoding() {
  console.log('=== Example 2: Online Decoding (NHTSA API) ===\n');

  const decoder = new VINDecoder({ online: true });

  try {
    const vin = VINS.honda;
    const result = await decoder.decode(vin);

    console.log(`VIN: ${result.vin}`);
    console.log(`Make: ${result.make}`);
    console.log(`Manufacturer: ${result.manufacturerName}`);
    console.log(`Model: ${result.model}`);
    console.log(`Year: ${result.modelYear}`);
    console.log(`Body Class: ${result.bodyClass}`);
    console.log(`Engine: ${result.engineCylinders} cylinders, ${result.displacementL}L`);
    console.log(`Fuel Type: ${result.fuelTypePrimary}`);
    console.log(`Transmission: ${result.transmissionStyle}, ${result.transmissionSpeeds} speeds`);
    console.log(`Drive Type: ${result.driveType}`);
    console.log(`Plant: ${result.plantCity}, ${result.plantState}, ${result.plantCountry}`);
  } catch (error) {
    console.error(`Error: ${error}`);
  }
  console.log();
}

/**
 * Example 3: VIN validation
 */
function example3_validation() {
  console.log('=== Example 3: VIN Validation ===\n');

  const decoder = new VINDecoder();

  const testVINs = [
    '1HGCM82633A123456',  // Valid
    'INVALID',             // Too short
    '1HGCM82633A12345I',  // Contains 'I'
    'JH4KA7532PC000000',  // Valid
  ];

  for (const vin of testVINs) {
    const validation = decoder.validate(vin);
    console.log(`VIN: ${vin}`);
    console.log(`  Valid: ${validation.valid}`);
    if (validation.errors.length > 0) {
      console.log(`  Errors: ${validation.errors.join(', ')}`);
    }
    if (validation.warnings.length > 0) {
      console.log(`  Warnings: ${validation.warnings.join(', ')}`);
    }
    console.log();
  }
}

/**
 * Example 4: Extract VIN components
 */
function example4_vinComponents() {
  console.log('=== Example 4: VIN Components ===\n');

  const decoder = new VINDecoder();
  const vin = VINS.honda;

  console.log(`VIN: ${vin}`);
  console.log(`WMI (World Manufacturer Identifier): ${decoder.getWMI(vin)}`);
  console.log(`VDS (Vehicle Descriptor Section): ${decoder.getVDS(vin)}`);
  console.log(`VIS (Vehicle Identifier Section): ${decoder.getVIS(vin)}`);
  console.log(`Model Year: ${decoder.getModelYear(vin)}`);
  console.log(`Region: ${decoder.getRegion(vin)}`);
  console.log(`Country: ${decoder.getCountry(vin)}`);
  console.log(`Is North American: ${decoder.isNorthAmericanVIN(vin)}`);
  console.log();
}

/**
 * Example 5: Search manufacturers
 */
function example5_searchManufacturers() {
  console.log('=== Example 5: Search Manufacturers ===\n');

  const decoder = new VINDecoder();

  // Search for manufacturers
  const queries = ['Honda', 'BMW', 'Mercedes', 'Toyota'];

  for (const query of queries) {
    const results = decoder.searchManufacturers(query);
    console.log(`Search "${query}": Found ${results.length} results`);
    if (results.length > 0) {
      console.log(`  ${results.slice(0, 3).join(', ')}${results.length > 3 ? '...' : ''}`);
    }
    console.log();
  }

  // Database stats
  console.log(`Total manufacturers: ${decoder.getAllManufacturers().length}`);
  console.log(`Total WMI codes: ${decoder.getDatabaseSize()}`);
  console.log();
}

/**
 * Example 6: Formatted info
 */
function example6_formattedInfo() {
  console.log('=== Example 6: Formatted Info ===\n');

  const decoder = new VINDecoder();

  for (const [brand, vin] of Object.entries(VINS).slice(0, 3)) {
    console.log(`${brand.toUpperCase()}:`);
    console.log(decoder.getFormattedInfo(vin));
    console.log();
  }
}

/**
 * Example 7: Caching demonstration
 */
async function example7_caching() {
  console.log('=== Example 7: Caching ===\n');

  const decoder = new VINDecoder({
    online: true,
    cacheDuration: 60000 // 1 minute
  });

  const vin = VINS.honda;

  console.log('First call (will hit API):');
  const start1 = Date.now();
  await decoder.decode(vin);
  const time1 = Date.now() - start1;
  console.log(`  Time: ${time1}ms`);
  console.log(`  Cached: ${decoder.isCached(vin)}`);
  console.log();

  console.log('Second call (will use cache):');
  const start2 = Date.now();
  await decoder.decode(vin);
  const time2 = Date.now() - start2;
  console.log(`  Time: ${time2}ms`);
  console.log(`  Cached: ${decoder.isCached(vin)}`);
  console.log(`  Speed improvement: ${Math.round(time1 / time2)}x faster`);
  console.log();

  // Clear cache
  decoder.clearCache();
  console.log('Cache cleared');
  console.log(`  Cached: ${decoder.isCached(vin)}`);
  console.log();
}

/**
 * Example 8: Error handling
 */
async function example8_errorHandling() {
  console.log('=== Example 8: Error Handling ===\n');

  const decoder = new VINDecoder();

  const invalidVINs = [
    '',
    'INVALID',
    '1HGCM82633A12345I',  // Contains 'I'
    '1HGCM82633A12',       // Too short
  ];

  for (const vin of invalidVINs) {
    try {
      await decoder.decode(vin);
      console.log(`${vin}: Decoded successfully (unexpected)`);
    } catch (error) {
      console.log(`${vin}: ${error instanceof Error ? error.message : 'Unknown error'}`);
    }
  }
  console.log();
}

/**
 * Run all examples
 */
async function runAllExamples() {
  console.log('╔══════════════════════════════════════════════════════════════╗');
  console.log('║      NHTSA VIN Decoder - TypeScript/JavaScript Examples      ║');
  console.log('╚══════════════════════════════════════════════════════════════╝\n');

  try {
    await example1_offlineDecoding();
    example3_validation();
    example4_vinComponents();
    example5_searchManufacturers();
    example6_formattedInfo();
    example8_errorHandling();

    // Online examples (commented out to avoid API calls during testing)
    // await example2_onlineDecoding();
    // await example7_caching();

    console.log('✅ All examples completed successfully!');
  } catch (error) {
    console.error('❌ Error running examples:', error);
  }
}

// Run examples if executed directly
if (require.main === module) {
  runAllExamples();
}

export {
  example1_offlineDecoding,
  example2_onlineDecoding,
  example3_validation,
  example4_vinComponents,
  example5_searchManufacturers,
  example6_formattedInfo,
  example7_caching,
  example8_errorHandling,
};
