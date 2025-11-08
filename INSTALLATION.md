# Quick Start Installation Guide

## ✅ Complete Checklist

### 1. Install Java JDK (Required)
- [ ] Download JDK 17+ from https://adoptium.net/
- [ ] Run the installer
- [ ] Verify: `java -version` in PowerShell
- [ ] Set JAVA_HOME environment variable

### 2. Install Android Studio (Required)
- [ ] Download from https://developer.android.com/studio
- [ ] Run installer and complete setup wizard
- [ ] Install these components:
  - [ ] Android SDK
  - [ ] Android SDK Platform 34
  - [ ] Android SDK Build-Tools
  - [ ] Android Emulator
- [ ] Set ANDROID_HOME environment variable

### 3. Set Environment Variables
Add these to your system environment variables:

**ANDROID_HOME:**
```
C:\Users\YourUsername\AppData\Local\Android\Sdk
```

**JAVA_HOME:**
```
C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
```

**How to set:**
1. Press `Win + X` → Select "System"
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Under "User variables", click "New"
5. Add each variable name and value
6. Click OK to save

### 4. Build Your App
After installation:
```powershell
# Restart your terminal first!
cd "My First Android App"
.\gradlew.bat build
```

### 5. Run Your App
```powershell
# On emulator (start emulator first in Android Studio)
.\gradlew.bat installDebug

# Or on physical device (enable USB debugging)
.\gradlew.bat installDebug
```

## 🆘 Troubleshooting

### Java Not Found
```powershell
# Check if Java is installed
java -version

# If not found, install from https://adoptium.net/
```

### ANDROID_HOME Not Found
```powershell
# Check current value
echo $env:ANDROID_HOME

# Set temporarily for testing
$env:ANDROID_HOME = "C:\Users\steve\AppData\Local\Android\Sdk"
```

### Build Fails
```powershell
# Clean and rebuild
.\gradlew.bat clean
.\gradlew.bat build
```

## 📚 Resources
- Java Download: https://adoptium.net/
- Android Studio: https://developer.android.com/studio
- Android Docs: https://developer.android.com/docs
- GitHub Repo: https://github.com/CivAIgentics/My-First-Android-App

## 🎯 Quick Test Script
Run this in PowerShell to check your setup:
```powershell
.\setup-environment.ps1
```
