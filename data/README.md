# Data Directory

This directory contains source data and processing scripts for the WMI (World Manufacturer Identifier) database. These files are **not used at runtime** but are kept for reference and regeneration purposes.

## Files

### Source CSV Files
- `wmi.csv` - Primary WMI database (568 entries)
- `wmi-from-github.csv` - GitHub-sourced codes (742 entries)
- `wmi-from-wiki.csv` - Wikipedia-sourced codes (437 entries)
- `wmi-from-offline.csv` - Additional offline codes (569 entries)

### Processing Files
- `process_wmi.py` - Python script to process CSVs and generate Java code
- `combined_wmi_raw.txt` - Combined output of all CSV files (1,487 total entries)
- `wmi_summary.txt` - Summary of processed WMI codes

## Usage

To regenerate the WMI database:

1. Edit CSV files to add/update WMI codes
2. Run the processing script:
   ```bash
   python3 process_wmi.py
   ```
3. Copy the generated code to `/java/com/obddroid/api/offline/WMIDatabase.java`

## Important Notes

- The runtime application uses the compiled `WMIDatabase.java` file
- These CSV files are for documentation and regeneration only
- After processing, 948 unique WMI codes are extracted from the 1,487 raw entries
- Duplicates are automatically removed during processing

## Data Sources

All CSV files were obtained from public sources:
- WALL-E/vin-decoder GitHub repository
- Wikipedia WMI listings
- Various automotive databases