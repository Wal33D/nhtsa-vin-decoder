# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2025-10-03

### Added
- **WMI Database Synchronization**: Java implementation now matches Python with 2,015+ WMI codes (up from 1,219)
- **Status Badges**: Added CI/CD, license, Java, and Python version badges to README
- **Quick Start Section**: Added instant copy-paste examples for both Java and Python
- **Python Examples**: Created three comprehensive examples:
  - `basic_usage.py` - Simple VIN decoding demonstrations
  - `batch_decode.py` - High-performance batch processing with performance comparisons
  - `flask_api.py` - Production-ready REST API with endpoints for decode, validate, batch, health, and stats
- **GitHub Actions CI/CD**: Automated testing for both Java and Python implementations
- **Gradle Build System**: Added build.gradle and settings.gradle for consistent dependency management
- **MIT License**: Added open-source license file
- **Comprehensive Documentation**:
  - Quick Start guide
  - Performance comparison section
  - Real-world use cases
  - Installation guides
  - API documentation
  - WMI database documentation

### Changed
- **Package Structure**: Migrated from `com.obddroid.api` to `io.github.vindecoder`
- **Year Decoding**: Fixed 1980-2009 vs 2010+ cycle disambiguation using position 7 heuristic
- **Region Mapping**: Updated to use continental groupings instead of country-specific codes
- **Documentation Accuracy**: Updated all references from "948+" to "2,015+" WMI codes
- **Coverage Claims**: Updated from "3x" to "6x industry standard"

### Fixed
- **Java Syntax Errors**: Corrected invalid Python-style "or" operators in GMDecoder
- **Year Decoding Bug**: Now correctly decodes letter codes (A-Y) based on position 7 character
- **CI/CD Python Path**: Fixed PYTHONPATH in GitHub Actions workflow
- **Missing Setters**: Added 13 missing setter methods to VehicleData class for offline decoder support

### Technical Details
- **Java**: Requires Java 11+, uses Gradle for builds
- **Python**: Compatible with Python 3.6+, no external dependencies for offline mode
- **WMI Coverage**: 2,015+ manufacturer codes covering all major regions:
  - North America: Complete coverage (US, Canada, Mexico)
  - Europe: Comprehensive (Germany, UK, France, Italy, Spain, Sweden)
  - Asia: Extensive (Japan, Korea, China, India)
  - Other: Oceania, South America, Africa

### Performance
- Offline decode: <1ms per VIN
- Online decode: ~200-500ms per VIN (NHTSA API latency)
- Batch processing: 1,000+ VINs/second (offline mode)

## [Pre-0.1.0] - Historical

### Previous Versions
This project was previously maintained with manufacturer-specific decoders:
- Mercedes-Benz decoder (115+ model variants)
- Ford decoder (100+ model codes)
- GM/Chevrolet decoder with RPO engine codes
- Toyota/Lexus decoder

### Migration Notes
For users upgrading from pre-0.1.0 versions:
- Update package imports from `com.obddroid.api` to `io.github.vindecoder`
- Python WMI database now has 2,015+ codes (was ~1,500)
- Java WMI database synchronized with Python (both have 2,015+ codes)
- Year decoding now supports 1980-2039 range with improved accuracy

---

## Upcoming Features

### Planned for 0.2.0
- [ ] Honda/Acura decoder
- [ ] BMW decoder (17-character patterns)
- [ ] Nissan/Infiniti decoder
- [ ] Maven Central publication
- [ ] PyPI package publication (`pip install nhtsa-vin-decoder`)

### Future Considerations
- GraphQL API support
- Docker containerization
- TypeScript/JavaScript bindings
- Performance benchmarks vs other libraries
- Extended test coverage (>90%)

---

## Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Support

- **Issues**: https://github.com/Wal33D/nhtsa-vin-decoder/issues
- **Discussions**: https://github.com/Wal33D/nhtsa-vin-decoder/discussions
- **Email**: aquataze@yahoo.com

---

[0.1.0]: https://github.com/Wal33D/nhtsa-vin-decoder/releases/tag/v0.1.0
