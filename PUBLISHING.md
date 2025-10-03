# Publishing Guide

This document explains how to publish the NHTSA VIN Decoder to PyPI (Python Package Index) and Maven Central (Java).

## Version 0.1.0 Release

### Prerequisites

#### For PyPI (Python)
- Python 3.6+ installed
- PyPI account: https://pypi.org/account/register/
- TestPyPI account (optional, for testing): https://test.pypi.org/account/register/
- `build` and `twine` packages installed:
  ```bash
  pip install build twine
  ```

#### For Maven Central (Java)
- Java 11+ installed
- Maven or Gradle installed
- Sonatype OSSRH account: https://issues.sonatype.org/
- GPG key for signing artifacts
- Configure `~/.m2/settings.xml` with credentials

---

## Publishing to PyPI

### 1. Build the Distribution

```bash
# Clean any previous builds
rm -rf dist/ build/ *.egg-info

# Build source distribution and wheel
python -m build
```

This creates files in `dist/`:
- `nhtsa-vin-decoder-0.1.0.tar.gz` (source distribution)
- `nhtsa_vin_decoder-0.1.0-py3-none-any.whl` (wheel)

### 2. Test on TestPyPI (Optional but Recommended)

```bash
# Upload to TestPyPI
python -m twine upload --repository testpypi dist/*

# Test installation
pip install --index-url https://test.pypi.org/simple/ nhtsa-vin-decoder
```

### 3. Upload to PyPI

```bash
# Upload to production PyPI
python -m twine upload dist/*

# Enter your PyPI credentials when prompted
# Username: __token__
# Password: <your-api-token>
```

### 4. Verify Installation

```bash
# Install from PyPI
pip install nhtsa-vin-decoder

# Test it works
python -c "from python.nhtsa_vin_decoder import NHTSAVinDecoder; print('Success!')"
```

### 5. Usage After Installation

```python
# Users can now install with:
pip install nhtsa-vin-decoder

# And use it:
from python.nhtsa_vin_decoder import NHTSAVinDecoder
from python.wmi_database import WMIDatabase

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")
print(f"{vehicle.year} {vehicle.make} {vehicle.model}")
```

---

## Publishing to Maven Central

### 1. Setup GPG Key

```bash
# Generate GPG key if you don't have one
gpg --gen-key

# List your keys
gpg --list-keys

# Distribute your public key
gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
```

### 2. Configure Maven Settings

Edit `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>YOUR_SONATYPE_USERNAME</username>
      <password>YOUR_SONATYPE_PASSWORD</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>ossrh</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <gpg.executable>gpg</gpg.executable>
        <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>
      </properties>
    </profile>
  </profiles>
</settings>
```

### 3. Build and Deploy with Maven

```bash
# Clean and build
mvn clean package

# Deploy to Maven Central
mvn clean deploy -P release

# Or if using Gradle, add to build.gradle:
# apply plugin: 'maven-publish'
# apply plugin: 'signing'
```

### 4. Release on Sonatype Nexus

1. Login to https://s01.oss.sonatype.org/
2. Go to "Staging Repositories"
3. Find your repository (io.github.wal33d-xxxx)
4. Click "Close" to validate
5. Click "Release" to publish to Maven Central

### 5. Verify Installation (after 2-4 hours)

```xml
<!-- Add to pom.xml -->
<dependency>
    <groupId>io.github.wal33d</groupId>
    <artifactId>nhtsa-vin-decoder</artifactId>
    <version>0.1.0</version>
</dependency>
```

```gradle
// Or in build.gradle
implementation 'io.github.wal33d:nhtsa-vin-decoder:0.1.0'
```

---

## Creating GitHub Release

### 1. Create Git Tag

```bash
# Create annotated tag
git tag -a v0.1.0 -m "Release version 0.1.0"

# Push tag to GitHub
git push origin v0.1.0
```

### 2. Create Release on GitHub

```bash
# Using GitHub CLI (gh)
gh release create v0.1.0 \
  --title "v0.1.0 - Initial Release" \
  --notes-file RELEASE_NOTES.md \
  --draft=false

# Or manually:
# 1. Go to https://github.com/Wal33D/nhtsa-vin-decoder/releases
# 2. Click "Draft a new release"
# 3. Choose tag v0.1.0
# 4. Add release notes from CHANGELOG.md
# 5. Publish release
```

### 3. Release Notes Template

```markdown
## 🎉 NHTSA VIN Decoder v0.1.0

First stable release of the world's most comprehensive open-source VIN decoder!

### ✨ Highlights

- **2,015+ WMI Codes** - 6x industry standard coverage
- **Offline & Online Modes** - Works with or without internet
- **Manufacturer Decoders** - Mercedes, Ford, GM, Toyota with full specs
- **Multi-Platform** - Java 11+ and Python 3.6+ support
- **Production Ready** - Battle-tested with comprehensive test suite

### 📦 Installation

**Python:**
```bash
pip install nhtsa-vin-decoder
```

**Java (Maven):**
```xml
<dependency>
    <groupId>io.github.wal33d</groupId>
    <artifactId>nhtsa-vin-decoder</artifactId>
    <version>0.1.0</version>
</dependency>
```

**Java (Gradle):**
```gradle
implementation 'io.github.wal33d:nhtsa-vin-decoder:0.1.0'
```

### 🚀 Quick Start

**Python:**
```python
from python.nhtsa_vin_decoder import NHTSAVinDecoder

decoder = NHTSAVinDecoder()
vehicle = decoder.decode("1HGCM82633A004352")
print(f"{vehicle.year} {vehicle.make} {vehicle.model}")
# Output: 2003 Honda Accord
```

**Java:**
```java
OfflineVINDecoder decoder = new OfflineVINDecoder();
VehicleData vehicle = decoder.decode("1HGCM82633A004352");
System.out.println(vehicle.getModelYear() + " " + vehicle.getMake());
// Output: 2003 Honda
```

### 📊 What's New

- 6x more WMI coverage (2,015+ codes vs 311)
- Fixed year decoding bug for 1980-2009 vehicles
- Added 4 manufacturer-specific decoders
- Enhanced validation with ISO 3779 check digit
- Comprehensive Python examples (basic, batch, Flask API)
- Full CI/CD pipeline with automated testing

See [CHANGELOG.md](https://github.com/Wal33D/nhtsa-vin-decoder/blob/main/CHANGELOG.md) for complete details.

### 🙏 Credits

- NHTSA for the vPIC API
- WALL-E/vin-decoder for WMI data
- All contributors and testers

**Full Changelog**: https://github.com/Wal33D/nhtsa-vin-decoder/blob/main/CHANGELOG.md
```

---

## Verification Checklist

Before publishing, ensure:

- [ ] All tests pass (`gradle test` and `python tests/test_year.py`)
- [ ] Version number updated in:
  - [ ] `pyproject.toml`
  - [ ] `pom.xml`
  - [ ] `python/__init__.py`
  - [ ] `CHANGELOG.md`
- [ ] CHANGELOG.md is up to date
- [ ] README.md examples are tested
- [ ] License file exists (MIT)
- [ ] Documentation is complete
- [ ] Git tag created and pushed
- [ ] GitHub release created

---

## Post-Release

### Announce Release

1. **GitHub Discussions**: Create announcement post
2. **Reddit**: Post to r/programming, r/learnprogramming
3. **Twitter/X**: Tweet with #VINDecoder #OpenSource
4. **Dev.to**: Write release article
5. **Hacker News**: Submit if significant interest

### Monitor

- PyPI download stats: https://pypistats.org/packages/nhtsa-vin-decoder
- Maven Central stats: https://search.maven.org/artifact/io.github.wal33d/nhtsa-vin-decoder
- GitHub stars/forks/issues
- User feedback and bug reports

### Version Updates

For future releases:

1. Update version in all files
2. Update CHANGELOG.md
3. Run tests
4. Commit changes
5. Create tag
6. Build and publish to PyPI
7. Build and publish to Maven Central
8. Create GitHub release

---

## Troubleshooting

### PyPI Upload Fails

- **Wrong credentials**: Use API token, not password
- **Package name taken**: Try different name or contact PyPI support
- **Version already exists**: Increment version number

### Maven Central Issues

- **GPG signing fails**: Check GPG key and passphrase
- **Close fails**: Check POM metadata completeness
- **Release blocked**: Verify all requirements met (javadoc, sources, signatures)

### GitHub Release Issues

- **Tag doesn't appear**: Ensure tag is pushed (`git push --tags`)
- **Release notes empty**: Use `--notes-file` or manually add
- **Assets not uploading**: Check file paths and permissions

---

## Support

- **Documentation**: https://github.com/Wal33D/nhtsa-vin-decoder
- **Issues**: https://github.com/Wal33D/nhtsa-vin-decoder/issues
- **Email**: aquataze@yahoo.com

---

**Author**: Wal33D (aquataze@yahoo.com)
**License**: MIT
**Repository**: https://github.com/Wal33D/nhtsa-vin-decoder
