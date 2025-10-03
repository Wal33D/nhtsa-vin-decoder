#!/usr/bin/env python3
"""
Process WMI CSV files and generate Java code for WMIDatabase
"""

import csv
import re
from collections import defaultdict

def clean_manufacturer_name(name):
    """Clean and normalize manufacturer names"""
    if not name:
        return None

    # Remove extra spaces and commas
    name = re.sub(r'\s+', ' ', name.strip())

    # Handle special cases
    replacements = {
        'Hummer': 'AM General/Hummer',
        'International': 'Navistar International',
        'BMW M': 'BMW',
        'Mercedes Benz': 'Mercedes-Benz',
        'Rolls Royce': 'Rolls-Royce',
        'Alfa-Romeo': 'Alfa Romeo',
        'Land-Rover': 'Land Rover',
        'Mini Cooper': 'MINI',
        'Volkswagon': 'Volkswagen',
        'Chev Truck': 'Chevrolet',
        'Chev': 'Chevrolet',
        'GMC Truck': 'GMC',
    }

    for old, new in replacements.items():
        if name == old:
            name = new

    return name

def parse_csv_files():
    """Parse all CSV files and extract WMI codes"""
    wmi_dict = {}

    # Process each CSV file
    csv_files = [
        'wmi.csv',
        'wmi-from-wiki.csv',
        'wmi-from-github.csv',
        'wmi-from-offline.csv'
    ]

    for filename in csv_files:
        try:
            with open(filename, 'r', encoding='utf-8') as f:
                # Try to detect delimiter
                first_line = f.readline()
                f.seek(0)

                if ',' in first_line:
                    reader = csv.reader(f, delimiter=',')
                else:
                    # Some files might use tabs or other delimiters
                    reader = csv.reader(f, delimiter='\t')

                # Skip header if present
                header = next(reader, None)
                if header and 'WMI' in str(header).upper():
                    pass  # Already consumed header
                else:
                    # Reset if first line wasn't header
                    f.seek(0)
                    reader = csv.reader(f, delimiter=',')

                for row in reader:
                    if len(row) >= 2:
                        wmi = row[0].strip().upper()
                        manufacturer = clean_manufacturer_name(row[1].strip())

                        # Skip invalid entries
                        if not wmi or not manufacturer or wmi == 'WMI':
                            continue

                        # Only add if WMI is valid (1-6 characters)
                        if 1 <= len(wmi) <= 6:
                            # If we already have this WMI, prefer longer manufacturer names
                            if wmi not in wmi_dict or len(manufacturer) > len(wmi_dict[wmi]):
                                wmi_dict[wmi] = manufacturer

        except Exception as e:
            print(f"Error processing {filename}: {e}")

    return wmi_dict

def generate_java_code(wmi_dict):
    """Generate Java code for WMIDatabase"""

    # Sort by WMI code for organized output
    sorted_wmis = sorted(wmi_dict.items())

    # Group by first character for better organization
    grouped = defaultdict(list)
    for wmi, manufacturer in sorted_wmis:
        grouped[wmi[0]].append((wmi, manufacturer))

    java_code = []
    java_code.append("        // ===== COMPREHENSIVE WMI DATABASE =====")
    java_code.append("        // Generated from WALL-E/vin-decoder repository")
    java_code.append(f"        // Total WMI codes: {len(wmi_dict)}")
    java_code.append("")

    # Generate code by region/first character
    region_names = {
        '1': 'United States',
        '2': 'Canada',
        '3': 'Mexico',
        '4': 'United States',
        '5': 'United States',
        '6': 'Australia/Oceania',
        '7': 'New Zealand/Oceania',
        '8': 'Argentina/South America',
        '9': 'Brazil/South America',
        'A': 'South Africa',
        'B': 'Angola/Africa',
        'C': 'Benin/Africa',
        'D': 'Egypt/Africa',
        'E': 'Ethiopia/Africa',
        'F': 'Ghana/Africa',
        'G': 'Morocco/Africa',
        'H': 'Ghana/Africa',
        'J': 'Japan',
        'K': 'Korea',
        'L': 'China',
        'M': 'India/Asia',
        'N': 'Iran/Turkey',
        'P': 'Malaysia/Philippines',
        'R': 'Taiwan/UAE',
        'S': 'United Kingdom/Europe',
        'T': 'Switzerland/Europe',
        'U': 'Hungary/Europe',
        'V': 'Austria/Europe',
        'W': 'Germany',
        'X': 'Russia/USSR',
        'Y': 'Belgium/Europe',
        'Z': 'Italy/Europe'
    }

    for char in sorted(grouped.keys()):
        region = region_names.get(char, 'Unknown')
        java_code.append(f"        // ---- {char}: {region} ----")

        for wmi, manufacturer in grouped[char]:
            # Escape quotes in manufacturer name
            manufacturer = manufacturer.replace('"', '\\"')
            java_code.append(f'        WMI_MAP.put("{wmi}", "{manufacturer}");')

        java_code.append("")

    return '\n'.join(java_code)

def main():
    print("Processing WMI CSV files...")
    wmi_dict = parse_csv_files()
    print(f"Found {len(wmi_dict)} unique WMI codes")

    # Generate Java code
    java_code = generate_java_code(wmi_dict)

    # Save to file
    with open('wmi_database_generated.java', 'w') as f:
        f.write(java_code)

    print("Generated wmi_database_generated.java")

    # Also create a summary file
    with open('wmi_summary.txt', 'w') as f:
        f.write(f"Total unique WMI codes: {len(wmi_dict)}\n\n")
        f.write("Sample entries:\n")
        for wmi, manufacturer in list(wmi_dict.items())[:20]:
            f.write(f"{wmi}: {manufacturer}\n")

if __name__ == "__main__":
    main()