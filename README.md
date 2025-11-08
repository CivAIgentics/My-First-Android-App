# My First Android App

A simple "Hello World" Android application built with Java.

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

1. **Android Studio** or **Android SDK Command-line Tools**
2. **Java Development Kit (JDK) 8 or higher**
3. **Android SDK** with:
   - Android SDK Platform 34 (Android 14)
   - Android SDK Build-Tools
   - Android Emulator (for testing)

## Setup Instructions

### 1. Install Android SDK

If you haven't already, install Android Studio from: https://developer.android.com/studio

Or install the Android SDK command-line tools from: https://developer.android.com/studio#command-tools

### 2. Set Environment Variables

Set the `ANDROID_HOME` environment variable to point to your Android SDK location:

**Windows (PowerShell):**
```powershell
$env:ANDROID_HOME = "C:\Users\YourUsername\AppData\Local\Android\Sdk"
```

**macOS/Linux:**
```bash
export ANDROID_HOME=$HOME/Android/Sdk
```

### 3. Build the Project

To build the project, run:

```bash
gradlew.bat build
```

Or on macOS/Linux:
```bash
./gradlew build
```

### 4. Run on Emulator

1. Create and start an Android Virtual Device (AVD) using Android Studio or:
   ```bash
   avdmanager create avd -n MyEmulator -k "system-images;android-34;google_apis;x86_64"
   emulator -avd MyEmulator
   ```

2. Install and run the app:
   ```bash
   gradlew.bat installDebug
   ```

### 5. Run on Physical Device

1. Enable Developer Options and USB Debugging on your Android device
2. Connect your device via USB
3. Run:
   ```bash
   gradlew.bat installDebug
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

- If Gradle sync fails, ensure `ANDROID_HOME` is set correctly
- For build errors, run `gradlew.bat clean` before rebuilding
- Make sure you have internet connection for Gradle to download dependencies

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
