# WMI Database Documentation

## Overview

The World Manufacturer Identifier (WMI) database provides offline vehicle manufacturer identification based on the first three characters of a VIN. This database enables basic vehicle information retrieval without requiring an internet connection.

## WMI Structure

A WMI code consists of:
- **Character 1**: Region/Country code
- **Characters 2-3**: Manufacturer identifier

## Coverage

The database includes over 2000 WMI codes covering:

### Regional Coverage
- **North America**: United States, Canada, Mexico
- **Europe**: Germany, United Kingdom, France, Italy, Spain, Sweden
- **Asia**: Japan, South Korea, China, India
- **Other**: Australia, Brazil, South Africa

### Major Manufacturers

#### United States (1, 4, 5)
- General Motors (1G)
- Ford (1F)
- Chrysler (1C)
- Tesla (5YJ)
- Chevrolet (1GC, 1GB)
- Cadillac (1GY)
- GMC (1GT)
- Lincoln (1LN)
- Jeep (1J4, 1J8)

#### Japan (J)
- Toyota (JT)
- Honda (JH)
- Mazda (JM)
- Mitsubishi (JA)
- Nissan (JN)
- Subaru (JF)
- Suzuki (JS)
- Isuzu (JAA-JAZ)
- Lexus (JTH, JTJ)
- Acura (JH4)
- Infiniti (JNK, JNR)

#### Germany (W, SN-ST)
- Mercedes-Benz (WDB, WDC, WDD)
- BMW (WBA, WBS)
- Audi (WAU, WA1)
- Volkswagen (WVW, WVG, WV1, WV2)
- Porsche (WP0, WP1)

#### South Korea (K)
- Hyundai (KMH)
- Kia (KNA, KND)
- Genesis (KMH)

#### United Kingdom (SA-SM)
- Jaguar (SAJ, SAD)
- Land Rover (SAL)
- Rolls-Royce (SCA)
- Bentley (SCB)
- Aston Martin (SCF)
- McLaren (SBM)
- Mini (WMW)

#### Italy (Z)
- Ferrari (ZFF)
- Lamborghini (ZHW)
- Maserati (ZAM)
- Alfa Romeo (ZAR)
- Fiat (ZFA)

#### Sweden (Y)
- Volvo (YV1, YV2, YV3)
- Saab (YS3)
- Koenigsegg (YT9)
- Polestar (YV4)

#### France (V)
- Renault (VF1, VF2)
- Peugeot (VF3)
- Citroen (VF7)
- Bugatti (VF9)

## VIN Year Decoding

The database supports year decoding from VIN position 10:

| Code | Year | Code | Year |
|------|------|------|------|
| 1 | 2001 | G | 2016 |
| 2 | 2002 | H | 2017 |
| 3 | 2003 | J | 2018 |
| 4 | 2004 | K | 2019 |
| 5 | 2005 | L | 2020 |
| 6 | 2006 | M | 2021 |
| 7 | 2007 | N | 2022 |
| 8 | 2008 | P | 2023 |
| 9 | 2009 | R | 2024 |
| A | 2010 | S | 2025 |
| B | 2011 | T | 2026 |
| C | 2012 | V | 2027 |
| D | 2013 | W | 2028 |
| E | 2014 | X | 2029 |
| F | 2015 | Y | 2030 |

## Usage Examples

### Basic WMI Lookup
```python
from wmi_database import WMIDatabase

# Get manufacturer from VIN
manufacturer = WMIDatabase.get_manufacturer('1HGCM82633A004352')
# Returns: "Honda"

# Get region from VIN
region = WMIDatabase.get_country('WBA5B3C50GG252337')
# Returns: "Europe"

# Get year from VIN
year = WMIDatabase.get_year('5YJ3E1EA5KF000316')
# Returns: 2019

# Check if WMI is supported
supported = WMIDatabase.is_supported('ZFF76ZHT3E0201920')
# Returns: True (Ferrari)
```

### Fallback Implementation
```python
from nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()

# When NHTSA API is unavailable, automatically uses WMI database
result = decoder.decode('1HGCM82633A004352')
if 'WMI fallback' in result.error_text:
    print(f"Using offline data: {result.manufacturer}")
```

## Database Structure

The WMI database is implemented as a Python dictionary with optimized lookup:

```python
WMI_MAP = {
    # United States manufacturers
    '1HG': 'Honda',
    '1HM': 'Honda',
    '1F1': 'Ford',
    '1FA': 'Ford',
    '1FB': 'Ford',
    # ... over 2000 entries
}

REGION_MAP = {
    # North America
    '1': 'North America', '2': 'North America', '3': 'North America',
    '4': 'North America', '5': 'North America',
    # Oceania
    '6': 'Oceania', '7': 'Oceania',
    # South America
    '8': 'South America', '9': 'South America',
    # Africa (A-H)
    'A': 'Africa', 'B': 'Africa', 'C': 'Africa', 'D': 'Africa',
    'E': 'Africa', 'F': 'Africa', 'G': 'Africa', 'H': 'Africa',
    # Asia (J-R)
    'J': 'Asia', 'K': 'Asia', 'L': 'Asia', 'M': 'Asia', 'N': 'Asia',
    'P': 'Asia', 'R': 'Asia',
    # Europe (S-Z)
    'S': 'Europe', 'T': 'Europe', 'U': 'Europe', 'V': 'Europe',
    'W': 'Europe', 'X': 'Europe', 'Y': 'Europe', 'Z': 'Europe',
}
```

## Limitations

While the WMI database provides extensive coverage, it has some limitations:

1. **Basic Information Only**: Provides manufacturer, region, and year only
2. **No Model Details**: Cannot determine specific model, trim, or features
3. **No Technical Specs**: No engine, transmission, or performance data
4. **Static Database**: Requires updates for new manufacturers or codes

For complete vehicle information, the NHTSA API should be used when available.

## Maintenance

The WMI database is maintained and updated periodically to include:
- New manufacturer codes
- Company mergers and acquisitions
- Brand changes
- Regional expansions

Last updated: January 2025
