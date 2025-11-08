# My First Android App

A simple "Hello World" Android application built with Java.

**GitHub Repository:** https://github.com/CivAIgentics/My-First-Android-App

## Project Structure

```
My First Android App/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/myfirstandroidapp/
│   │       │   └── MainActivity.java
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml
│   │       │   └── values/
│   │       │       ├── strings.xml
│   │       │       └── colors.xml
│   │       └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Requirements

To build and run this Android app, you need:

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://adoptium.net/ (Recommended)
   - Or Oracle JDK: https://www.oracle.com/java/technologies/downloads/

2. **Android Studio** or **Android SDK Command-line Tools**
   - Download from: https://developer.android.com/studio

3. **Android SDK** with:
   - Android SDK Platform 34 (Android 14)
   - Android SDK Build-Tools
   - Android Emulator (for testing)

## Quick Installation Guide

### Step 1: Install Java (JDK)

1. Download JDK 17+ from https://adoptium.net/
2. Run the installer
3. Verify installation:
   ```powershell
   java -version
   ```

### Step 2: Install Android Studio

1. Download Android Studio from https://developer.android.com/studio
2. Run the installer and follow the setup wizard
3. During setup, ensure these are installed:
   - Android SDK
   - Android SDK Platform 34
   - Android SDK Build-Tools
   - Android Emulator

### Step 3: Set Environment Variables

Add these to your system environment variables:

**ANDROID_HOME:**
```
C:\Users\YourUsername\AppData\Local\Android\Sdk
```

**JAVA_HOME:**
```
C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
```

To set in PowerShell (temporary):
```powershell
$env:ANDROID_HOME = "C:\Users\steve\AppData\Local\Android\Sdk"
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot"
```

## Setup Instructions

### Clone the Repository (if not already local)

```powershell
git clone https://github.com/CivAIgentics/My-First-Android-App.git
cd My-First-Android-App
```

### Build the Project

To build the project, run:

```powershell
.\gradlew.bat build
```

Or on macOS/Linux:
```bash
./gradlew build
```

The Gradle wrapper will automatically download Gradle 8.2 on first run.

### Run on Emulator

1. Create and start an Android Virtual Device (AVD) using Android Studio or:
   ```bash
   avdmanager create avd -n MyEmulator -k "system-images;android-34;google_apis;x86_64"
   emulator -avd MyEmulator
   ```

2. Install and run the app:
   ```powershell
   .\gradlew.bat installDebug
   ```

### Run on Physical Device

1. Enable Developer Options and USB Debugging on your Android device
2. Connect your device via USB
3. Run:
   ```powershell
   .\gradlew.bat installDebug
   ```

## What This App Does

This is a simple "Hello World" Android application that displays a centered text message saying "Hello, Android World!" on the screen.

### Key Components

- **MainActivity.java**: The main activity that sets up the UI and displays the text
- **activity_main.xml**: The layout file defining the UI structure
- **AndroidManifest.xml**: App configuration and permissions
- **build.gradle**: Gradle build configuration files

## Development in VS Code

While Android Studio is the recommended IDE for Android development, you can use VS Code with:

1. **Extension Pack for Java** (already installed)
2. **Gradle for Java** (already installed)
3. Android SDK installed separately

## Troubleshooting

### Java Not Found
- Install JDK from https://adoptium.net/
- Set JAVA_HOME environment variable
- Restart your terminal/VS Code

### Android SDK Not Found
- Install Android Studio from https://developer.android.com/studio
- Set ANDROID_HOME environment variable
- Ensure it points to: `C:\Users\YourUsername\AppData\Local\Android\Sdk`

### Build Errors
- Run `.\gradlew.bat clean` before rebuilding
- Ensure you have internet connection for Gradle to download dependencies
- Check that all environment variables are set correctly

## Next Steps

- Add more UI components (buttons, input fields, etc.)
- Implement navigation between multiple screens
- Add app icons and customize themes
- Learn about Android activities, fragments, and intents
- Explore Material Design components

## Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Android Gradle Plugin](https://developer.android.com/studio/build)
- [Material Design](https://material.io/develop/android)
