# API Reference

## NHTSAVinDecoder Class

The main class for decoding Vehicle Identification Numbers using the NHTSA API.

### Constructor

```python
decoder = NHTSAVinDecoder()
```

Creates a new instance of the VIN decoder with default settings.

### Methods

#### decode(vin, model_year=None)

Decodes a VIN using the NHTSA API with automatic WMI fallback.

**Parameters:**
- `vin` (str): The Vehicle Identification Number to decode
- `model_year` (str, optional): The model year for more accurate decoding

**Returns:**
- `VehicleData`: Object containing decoded vehicle information

**Example:**
```python
result = decoder.decode('1HGCM82633A004352')
print(f"Vehicle: {result.year} {result.make} {result.model}")
```

#### decode_offline(vin)

Decodes a VIN using only the offline WMI database without making API calls.

**Parameters:**
- `vin` (str): The Vehicle Identification Number to decode

**Returns:**
- `VehicleData`: Object containing basic vehicle information from WMI

**Example:**
```python
result = decoder.decode_offline('WBA5B3C50GG252337')
print(f"Manufacturer: {result.manufacturer}")
print(f"Country: {result.plant_country}")
```

#### decode_async(vin, callback, model_year=None)

Performs asynchronous VIN decoding with a callback function.

**Parameters:**
- `vin` (str): The Vehicle Identification Number to decode
- `callback` (callable): Function called with VehicleData when complete
- `model_year` (str, optional): The model year for more accurate decoding

**Example:**
```python
def on_complete(data):
    print(f"Decoded: {data.make} {data.model}")

decoder.decode_async('1HGCM82633A004352', on_complete)
```

#### decode_batch(vins)

Decodes multiple VINs efficiently in batch.

**Parameters:**
- `vins` (list[str]): VIN strings to decode

**Returns:**
- `dict[str, VehicleData]`: Mapping of VIN to decoded data

**Example:**
```python
vins = ['1HGCM82633A004352', 'WBA5B3C50GG252337']
results = decoder.decode_batch(vins)
for vin, data in results.items():
    if data.error_text:
        print(f"{vin}: error -> {data.error_text}")
    else:
        print(f"{vin}: {data.get_display_name()}")
```

#### get_recalls_for_vehicle(make, model, model_year=None)

Retrieves recall campaigns for a given make/model/year combination.

**Parameters:**
- `make` (str): Vehicle make (e.g., "Honda")
- `model` (str): Vehicle model (e.g., "Accord")
- `model_year` (str | int, optional): Optional year filter

**Returns:**
- `list[RecallRecord]`: Recall entries reported by NHTSA

#### get_recalls_by_vin(vin, model_year=None)

Decodes a VIN then fetches associated recalls.

**Parameters:**
- `vin` (str): Vehicle Identification Number
- `model_year` (str, optional): Optional year hint

**Returns:**
- `tuple[VehicleData, list[RecallRecord]]`: Pair of decoded vehicle and recall list

#### clear_cache()

Clears cached VIN and recall lookups created by the decoder instance.

```python
decoder.clear_cache()
```

## VehicleData Class

Data class containing decoded vehicle information.

### Attributes

| Attribute | Type | Description |
|-----------|------|-------------|
| `vin` | str | Vehicle Identification Number |
| `make` | str | Brand/marketing make (e.g., "Honda") |
| `manufacturer` | str | Production manufacturer (if available) |
| `model` | str | Vehicle model |
| `year` | int | Model year (if provided by API) |
| `trim` | str | Trim designation |
| `vehicle_type` | str | Vehicle type category |
| `body_class` | str | Body style classification |
| `doors` | int | Number of doors |
| `drive_type` | str | Drive configuration (FWD/RWD/AWD/etc.) |
| `engine_cylinders` | int | Engine cylinder count |
| `engine_displacement_l` | float | Displacement in liters |
| `engine_model` | str | Engine marketing/model info |
| `fuel_type` | str | Primary fuel type |
| `transmission_style` | str | Transmission style (Automatic/Manual/etc.) |
| `transmission_speeds` | str | Number of transmission speeds |
| `gvwr` | str | Gross Vehicle Weight Rating (raw string) |
| `plant_city` | str | Assembly plant city |
| `plant_state` | str | Assembly plant state/province |
| `plant_country` | str | Assembly plant country |
| `error_text` | str | Error description when decoding fails |
| `raw_data` | dict | Original API payload (or WMI metadata) |
| `recalls` | list[RecallRecord] | Recall campaigns attached via `get_recalls_by_vin` |

### Helper Methods

- `is_valid()` -> bool: Returns True when make and model are populated.
- `get_display_name()` -> str: Convenience formatter for year/make/model/trim.

## RecallRecord Class

Data class that models a single recall campaign returned by NHTSA.

| Attribute | Type | Description |
|-----------|------|-------------|
| `manufacturer` | str | Reporting manufacturer |
| `nhtsa_campaign_number` | str | NHTSA campaign identifier |
| `nhtsa_action_number` | str | NHTSA action number (if provided) |
| `report_received_date` | str | Date NHTSA received the report |
| `component` | str | Component/system affected |
| `summary` | str | Recall summary provided by manufacturer |
| `consequence` | str | Potential consequence of the issue |
| `remedy` | str | Recommended remedy |
| `notes` | str | Additional notes or hotline contact |
| `model_year` | str | Model year covered by the recall |
| `make` | str | Vehicle make covered |
| `model` | str | Vehicle model covered |
| `mfr_recall_number` | str | Manufacturer-specific recall number |
| `over_the_air_update` | bool | Indicates OTA update availability |
| `park_it` | bool | Advises owners to stop driving immediately |
| `park_outside` | bool | Advises owners to park outside |
| `additional_fields` | dict | Raw entry for advanced use |

## WMIDatabase Class

Static class for offline WMI lookups.

### Static Methods

#### get_manufacturer(vin)

Gets the manufacturer name from a VIN's WMI code.

**Parameters:**
- `vin` (str): Vehicle Identification Number (minimum 3 characters)

**Returns:**
- `str`: Manufacturer name or "Unknown" if not found

#### get_country(vin)

Gets the country of manufacture from a VIN's WMI code.

**Parameters:**
- `vin` (str): Vehicle Identification Number (minimum 1 character)

**Returns:**
- `str`: Country name or "Unknown" if not found

#### get_year(vin)

Decodes the model year from a VIN.

**Parameters:**
- `vin` (str): Vehicle Identification Number (minimum 10 characters)

**Returns:**
- `int`: Decoded model year (supports the standard 1980–2039 VIN cycle) or None if invalid

#### is_supported(vin)

Checks if a VIN's WMI code is in the database.

**Parameters:**
- `vin` (str): Vehicle Identification Number (minimum 3 characters)

**Returns:**
- `bool`: True if WMI is supported, False otherwise

## Error Handling

The decoder handles various error scenarios gracefully:

- **Network errors**: Automatically falls back to WMI database
- **Invalid VINs**: Returns VehicleData with error information
- **API timeouts**: Uses WMI fallback or returns timeout error
- **Rate limiting**: Handles with appropriate delays

## Rate Limiting

The NHTSA API enforces request limits. `NHTSAVinDecoder` does not throttle calls automatically—implement rate limiting in your application when performing large batch operations. See `docs/USAGE.md` for a rate-limited decoder example.
