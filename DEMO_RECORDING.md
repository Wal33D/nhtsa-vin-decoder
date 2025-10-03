# Demo GIF Recording Guide

This guide explains how to create a professional demo GIF for the NHTSA VIN Decoder.

## Quick Start

```bash
# Run the demo script
python demo.py
```

## Recording Methods

### Method 1: Terminalizer (Recommended)

**Install:**
```bash
npm install -g terminalizer
```

**Record:**
```bash
# Start recording
terminalizer record nhtsa-demo

# Run the demo
python demo.py

# Stop recording (Ctrl+D or Ctrl+C)
```

**Render GIF:**
```bash
# Render to GIF
terminalizer render nhtsa-demo

# Output: nhtsa-demo.gif
```

**Customize:**
Edit the generated `nhtsa-demo.yml`:
```yaml
# The command to run
command: python demo.py

# Framerate
frameDelay: auto

# Quality (1-100)
quality: 100

# Terminal theme
theme:
  background: "#1e1e1e"
  foreground: "#d4d4d4"
```

### Method 2: asciinema + agg

**Install:**
```bash
# Install asciinema
pip install asciinema

# Install agg (for GIF conversion)
# See: https://github.com/asciinema/agg
cargo install --git https://github.com/asciinema/agg
```

**Record:**
```bash
# Record session
asciinema rec nhtsa-demo.cast

# Run the demo
python demo.py

# Stop recording (Ctrl+D)
```

**Convert to GIF:**
```bash
# Convert to GIF
agg nhtsa-demo.cast nhtsa-demo.gif

# With custom settings
agg --font-family "JetBrains Mono" \
    --theme monokai \
    --speed 1.5 \
    nhtsa-demo.cast nhtsa-demo.gif
```

### Method 3: ttyrec + ttygif

**Install (macOS):**
```bash
brew install ttyrec ttygif
```

**Record:**
```bash
# Start recording
ttyrec nhtsa-demo

# Run the demo
python demo.py

# Exit (Ctrl+D)
```

**Convert to GIF:**
```bash
# Create frames
ttygif nhtsa-demo

# This creates nhtsa-demo.gif
```

### Method 4: LICEcap (GUI)

**Install:**
- Download from: https://www.cockos.com/licecap/

**Record:**
1. Open LICEcap
2. Position window over terminal
3. Click Record
4. Run `python demo.py`
5. Click Stop when done

### Method 5: Kap (macOS)

**Install:**
```bash
brew install --cask kap
```

**Record:**
1. Open Kap
2. Select terminal window
3. Start recording
4. Run `python demo.py`
5. Stop and export as GIF

## Optimization Tips

### 1. Terminal Settings

```bash
# Set terminal to optimal size
# Recommended: 80x24 or 100x30

# macOS Terminal
# Terminal → Preferences → Profiles → Window
# Set columns: 100, rows: 30

# iTerm2
# Preferences → Profiles → Window
# Set columns: 100, rows: 30
```

### 2. Font Settings

Use a clear monospace font:
- JetBrains Mono (recommended)
- Fira Code
- Monaco
- Menlo
- Consolas

Size: 14-16pt for best readability

### 3. Theme Settings

Use a high-contrast theme:
- Monokai
- Dracula
- One Dark
- Solarized Dark

### 4. GIF Optimization

```bash
# Install gifsicle
brew install gifsicle  # macOS
apt-get install gifsicle  # Linux

# Optimize GIF
gifsicle -O3 --colors 256 nhtsa-demo.gif -o nhtsa-demo-optimized.gif

# Resize if too large
gifsicle --resize 800x600 nhtsa-demo.gif -o nhtsa-demo-small.gif
```

## Recording Checklist

Before recording:
- [ ] Terminal size set to 80-100 columns, 24-30 rows
- [ ] Font is clear and readable (14-16pt)
- [ ] High-contrast theme enabled
- [ ] No distracting background processes
- [ ] Demo script tested and works correctly

During recording:
- [ ] Run demo at normal speed (not too fast)
- [ ] Ensure all output is visible
- [ ] No errors or interruptions
- [ ] Clean terminal state (clear before starting)

After recording:
- [ ] GIF is clear and readable
- [ ] File size is reasonable (<10MB)
- [ ] All important content is visible
- [ ] Optimize if necessary

## Example Recording Session

```bash
# 1. Prepare terminal
clear
export PS1="$ "  # Simple prompt
resize -s 30 100  # Set size

# 2. Start recording (using terminalizer)
terminalizer record nhtsa-demo -c config.yml

# 3. Run demo
python demo.py

# 4. Stop recording
# Press Ctrl+D

# 5. Render
terminalizer render nhtsa-demo

# 6. Optimize
gifsicle -O3 --colors 256 nhtsa-demo.gif -o demo-final.gif

# 7. Upload to GitHub
# Add to README.md as:
# ![Demo](demo-final.gif)
```

## Recommended Configuration

**terminalizer config (config.yml):**
```yaml
command: python demo.py
cwd: .
env:
  recording: true
cols: 100
rows: 30
repeat: 0
quality: 100
frameDelay: auto
maxIdleTime: 2000
frameBox:
  type: window
  title: NHTSA VIN Decoder Demo
  style:
    border: 0px
    boxShadow: none
watermark:
  imagePath: null
  style:
    position: absolute
    right: 15px
    bottom: 15px
    width: 100px
    opacity: 0.9
cursorStyle: block
fontFamily: "JetBrains Mono, Monaco, Menlo, monospace"
fontSize: 14
lineHeight: 1.2
letterSpacing: 0
theme:
  background: "#1e1e1e"
  foreground: "#d4d4d4"
  cursor: "#aeafad"
  black: "#000000"
  red: "#cd3131"
  green: "#0dbc79"
  yellow: "#e5e510"
  blue: "#2472c8"
  magenta: "#bc3fbc"
  cyan: "#11a8cd"
  white: "#e5e5e5"
  brightBlack: "#666666"
  brightRed: "#f14c4c"
  brightGreen: "#23d18b"
  brightYellow: "#f5f543"
  brightBlue: "#3b8eea"
  brightMagenta: "#d670d6"
  brightCyan: "#29b8db"
  brightWhite: "#e5e5e5"
```

## Final Output

Place the final GIF in the repository root:
```
nhtsa-vin-decoder/
├── demo.gif           # Main demo GIF
├── demo.py           # Demo script
└── README.md         # Include GIF in README
```

Add to README.md:
```markdown
## 🎬 Demo

![NHTSA VIN Decoder Demo](demo.gif)

*Live demonstration of offline and online VIN decoding*
```

## Hosting Options

1. **GitHub Repository**: Commit GIF directly (if <10MB)
2. **GitHub Releases**: Attach as release asset
3. **Imgur**: Upload and link
4. **Giphy**: Upload and embed
5. **CloudFlare**: CDN hosting for faster loading

## Support

For issues with recording:
- Terminalizer: https://github.com/faressoft/terminalizer/issues
- asciinema: https://github.com/asciinema/asciinema/issues
- agg: https://github.com/asciinema/agg/issues

---

**Author**: Wal33D
**Repository**: https://github.com/Wal33D/nhtsa-vin-decoder
