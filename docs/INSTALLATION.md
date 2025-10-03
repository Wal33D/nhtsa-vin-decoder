# Installation Guide

## Requirements

- Python 3.6 or higher
- No external dependencies required (uses Python standard library only)

## Installation Methods

### 1. Direct Download

Download the source files directly from GitHub:

```bash
# Clone the repository
git clone https://github.com/Wal33D/nhtsa-vin-decoder.git

# Navigate to the directory
cd nhtsa-vin-decoder
```

### 2. Manual Installation

Copy the Python files to your project:

```bash
# Copy the decoder files to your project
cp -r nhtsa-vin-decoder/python/* /path/to/your/project/
```

### 3. Python Path Installation

Add the decoder to your Python path:

```python
import sys
sys.path.append('/path/to/nhtsa-vin-decoder/python')

from nhtsa_vin_decoder import NHTSAVinDecoder
```

## Project Structure

```
nhtsa-vin-decoder/
├── docs/
│   ├── README.md
│   ├── API.md
│   ├── WMI_DATABASE.md
│   ├── USAGE.md
│   └── INSTALLATION.md
├── python/
│   ├── nhtsa_vin_decoder.py    # Main decoder class
│   └── wmi_database.py         # Offline WMI database
├── examples/
│   ├── basic_usage.py
│   ├── batch_decode.py
│   └── flask_api.py
├── tests/
│   └── test_decoder.py
├── LICENSE
└── README.md
```

## Quick Start

### Basic Setup

```python
# Import the decoder
from python.nhtsa_vin_decoder import NHTSAVinDecoder

# Create decoder instance
decoder = NHTSAVinDecoder()

# Test the installation
result = decoder.decode('1HGCM82633A004352')
print(f"Installation successful! Decoded: {result.make} {result.model}")
```

### Verify Installation

Run this test script to verify everything is working:

```python
#!/usr/bin/env python3
"""Installation verification script"""

import sys

def verify_installation():
    """Verify the NHTSA VIN decoder installation"""
    print("Verifying NHTSA VIN Decoder installation...")
    print("-" * 50)

    # Step 1: Import modules
    try:
        from python.nhtsa_vin_decoder import NHTSAVinDecoder
        print("✓ Main decoder module imported successfully")
    except ImportError as e:
        print(f"✗ Failed to import decoder: {e}")
        return False

    try:
        from python.wmi_database import WMIDatabase
        print("✓ WMI database module imported successfully")
    except ImportError as e:
        print(f"✗ Failed to import WMI database: {e}")
        return False

    # Step 2: Test decoder creation
    try:
        decoder = NHTSAVinDecoder()
        print("✓ Decoder instance created successfully")
    except Exception as e:
        print(f"✗ Failed to create decoder: {e}")
        return False

    # Step 3: Test offline decode
    try:
        offline_result = decoder.decode_offline('1HGCM82633A004352')
        if offline_result.manufacturer:
            print(f"✓ Offline decode working: {offline_result.manufacturer}")
        else:
            print("✗ Offline decode returned no data")
            return False
    except Exception as e:
        print(f"✗ Offline decode failed: {e}")
        return False

    # Step 4: Test online decode (optional)
    try:
        online_result = decoder.decode('1HGCM82633A004352')
        if online_result.make:
            print(f"✓ Online decode working: {online_result.make} {online_result.model}")
        else:
            print("⚠ Online decode returned no data (API may be unavailable)")
    except Exception as e:
        print(f"⚠ Online decode failed (API may be unavailable): {e}")

    # Step 5: Test WMI database
    try:
        manufacturer = WMIDatabase.get_manufacturer('1HG')
        country = WMIDatabase.get_country('1')
        year = WMIDatabase.get_year('1HGCM82633A004352')
        print(f"✓ WMI database working: {manufacturer} from {country}, year {year}")
    except Exception as e:
        print(f"✗ WMI database test failed: {e}")
        return False

    print("-" * 50)
    print("✓ Installation verified successfully!")
    return True

if __name__ == "__main__":
    success = verify_installation()
    sys.exit(0 if success else 1)
```

## Configuration

### Environment Variables

The decoder can be configured using environment variables:

```bash
# Set custom timeout for API requests (seconds)
export NHTSA_API_TIMEOUT=10

# Enable debug logging
export NHTSA_DEBUG=true
```

### Custom Configuration

```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

# Create decoder with custom configuration
class CustomDecoder(NHTSAVinDecoder):
    def __init__(self):
        super().__init__()
        # Custom timeout
        self.timeout = 15
        # Custom retry attempts
        self.max_retries = 3

decoder = CustomDecoder()
```

## Troubleshooting

### Common Issues

#### ImportError: No module named 'nhtsa_vin_decoder'

**Solution**: Ensure the Python files are in your Python path:

```python
import sys
import os
sys.path.append(os.path.dirname(__file__))
```

#### URLError: Network is unreachable

**Solution**: The decoder will automatically fall back to the offline WMI database when the network is unavailable.

#### ValueError: Invalid VIN format

**Solution**: Ensure VINs are exactly 17 characters and don't contain I, O, or Q:

```python
def clean_vin(vin):
    """Clean and validate VIN"""
    vin = vin.strip().upper()
    if len(vin) != 17:
        raise ValueError(f"VIN must be 17 characters, got {len(vin)}")
    if any(c in vin for c in 'IOQ'):
        raise ValueError("VIN contains invalid characters (I, O, or Q)")
    return vin
```

#### Slow API Response Times

**Solution**: Use the offline decoder for basic information:

```python
# Quick offline check first
offline = decoder.decode_offline(vin)
if offline.manufacturer:
    # Use offline data for immediate response
    print(f"Manufacturer: {offline.manufacturer}")

# Then fetch full data in background
import threading
def fetch_full_data():
    full_data = decoder.decode(vin)
    # Update UI or database with full data

thread = threading.Thread(target=fetch_full_data)
thread.start()
```

### Performance Optimization

#### Enable Connection Pooling

For high-volume applications:

```python
import urllib.request
from urllib.request import HTTPHandler, HTTPSHandler, build_opener

# Create opener with connection pooling
handler = HTTPSHandler()
opener = build_opener(handler)

# Install globally
urllib.request.install_opener(opener)

# Now use decoder normally
decoder = NHTSAVinDecoder()
```

#### Implement Caching

```python
import json
import os
from datetime import datetime, timedelta

class CachedDecoder(NHTSAVinDecoder):
    def __init__(self, cache_dir='/tmp/vin_cache'):
        super().__init__()
        self.cache_dir = cache_dir
        os.makedirs(cache_dir, exist_ok=True)

    def _get_cache_file(self, vin):
        return os.path.join(self.cache_dir, f"{vin}.json")

    def _is_cache_valid(self, cache_file, max_age_days=30):
        if not os.path.exists(cache_file):
            return False
        age = datetime.now() - datetime.fromtimestamp(os.path.getmtime(cache_file))
        return age < timedelta(days=max_age_days)

    def decode(self, vin, model_year=None):
        cache_file = self._get_cache_file(vin)

        # Check cache
        if self._is_cache_valid(cache_file):
            with open(cache_file, 'r') as f:
                cached_data = json.load(f)
                return VehicleData(**cached_data)

        # Fetch fresh data
        result = super().decode(vin, model_year)

        # Cache the result
        if not result.error_code:
            with open(cache_file, 'w') as f:
                json.dump(result.to_dict(), f)

        return result
```

## Integration with Package Managers

### Setup.py Configuration

If integrating into a larger project:

```python
# setup.py
from setuptools import setup, find_packages

setup(
    name='nhtsa-vin-decoder',
    version='1.0.0',
    author='Waleed Judah',
    author_email='aquataze@yahoo.com',
    description='NHTSA VIN Decoder with offline WMI fallback',
    packages=['python'],
    python_requires='>=3.6',
    classifiers=[
        'Programming Language :: Python :: 3',
        'License :: OSI Approved :: MIT License',
        'Operating System :: OS Independent',
    ],
)
```

### Requirements File

Create a `requirements.txt` for your project:

```txt
# No external dependencies required
# Python 3.6+ standard library only
```

## Testing

Run the included tests to ensure everything works:

```bash
# Run basic tests
python3 tests/test_decoder.py

# Test specific VIN
python3 -c "
from python.nhtsa_vin_decoder import NHTSAVinDecoder
decoder = NHTSAVinDecoder()
result = decoder.decode('YOUR_VIN_HERE')
print(result.to_dict())
"
```

## Support

For issues, questions, or contributions:
- GitHub: https://github.com/Wal33D/nhtsa-vin-decoder
- Author: Waleed Judah (Wal33D)
- Email: aquataze@yahoo.com
