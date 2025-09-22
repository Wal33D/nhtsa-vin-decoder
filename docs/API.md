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
- `vins` (list): List of VIN strings to decode

**Returns:**
- `dict`: Dictionary mapping VINs to VehicleData objects

**Example:**
```python
vins = ['1HGCM82633A004352', 'WBA5B3C50GG252337']
results = decoder.decode_batch(vins)
for vin, data in results.items():
    print(f"{vin}: {data.year} {data.make}")
```

## VehicleData Class

Data class containing decoded vehicle information.

### Attributes

| Attribute | Type | Description |
|-----------|------|-------------|
| `vin` | str | The Vehicle Identification Number |
| `make` | str | Vehicle manufacturer brand name |
| `model` | str | Vehicle model name |
| `year` | int | Model year |
| `manufacturer` | str | Manufacturer company name |
| `plant_country` | str | Country of manufacture |
| `plant_city` | str | City of manufacture |
| `plant_state` | str | State/Province of manufacture |
| `body_class` | str | Body type classification |
| `vehicle_type` | str | Vehicle type category |
| `doors` | int | Number of doors |
| `engine_cylinders` | int | Number of engine cylinders |
| `engine_configuration` | str | Engine configuration type |
| `fuel_type_primary` | str | Primary fuel type |
| `fuel_type_secondary` | str | Secondary fuel type (if applicable) |
| `drive_type` | str | Drive configuration (FWD, RWD, AWD, etc.) |
| `gvwr_class` | str | Gross Vehicle Weight Rating class |
| `series` | str | Vehicle series/trim level |
| `error_code` | str | Error code if decoding failed |
| `error_text` | str | Error description if decoding failed |
| `raw_data` | dict | Raw API response data |

### Methods

#### to_dict()

Converts the VehicleData object to a dictionary.

**Returns:**
- `dict`: Dictionary representation of vehicle data

**Example:**
```python
data = decoder.decode('1HGCM82633A004352')
vehicle_dict = data.to_dict()
```

#### to_json()

Converts the VehicleData object to a JSON string.

**Returns:**
- `str`: JSON representation of vehicle data

**Example:**
```python
data = decoder.decode('1HGCM82633A004352')
json_str = data.to_json()
```

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
- `int`: Model year (2001-2030) or None if invalid

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

The NHTSA API has rate limits. The decoder includes built-in rate limiting:
- Maximum 1000 requests per minute
- Automatic retry with exponential backoff
- Batch processing optimization for multiple VINs