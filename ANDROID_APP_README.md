# Jacky 2.0 Android App

Android application for Jacky 2.0 - City of Midland AI Assistant powered by ElevenLabs and ANAM.

## Overview

This Android app provides a native mobile experience for the Jacky 2.0 web application using a WebView-based approach. It includes:

- **Full WebView integration** with your Jacky web app
- **Microphone permissions** for voice conversations
- **Offline-friendly caching** for better performance
- **Deep link support** for web integration
- **Network security configuration** for development and production

## Features

✅ Voice-enabled AI conversations with microphone access
✅ Seamless integration with ElevenLabs and ANAM services
✅ Support for both portrait and landscape orientations
✅ Automatic permission handling for audio recording
✅ WebView caching for improved performance
✅ Deep linking support for web-to-app navigation
✅ Development mode with localhost support

## Requirements

### Development Environment
- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **JDK**: Java 21 (LTS)
- **Android SDK**: API Level 34 (Android 14)
- **Minimum Android Version**: API Level 24 (Android 7.0)

### Web Backend
- Your Jacky 2.0 web server must be running and accessible
- For testing with Android Emulator: Use `http://10.0.2.2:3000`
- For testing on physical device: Use your computer's local IP (e.g., `http://192.168.1.100:3000`)
- For production: Use your production URL (e.g., `https://jacky.midlandtexas.gov`)

## Configuration

### Setting Your Web App URL

Edit `MainActivity.java` and update the `JACKY_URL` constant:

```java
private static final String JACKY_URL = "https://jacky.midlandtexas.gov";
```

**For Development:**
- **Android Emulator**: `http://10.0.2.2:3000`
- **Physical Device**: `http://YOUR_COMPUTER_IP:3000`

**For Production:**
- Use your production domain: `https://jacky.midlandtexas.gov`

### Network Security Configuration

The app includes a network security config file (`res/xml/network_security_config.xml`) that:
- Allows HTTP traffic for localhost and local network IPs (development)
- Requires HTTPS for production domains (security)

Update this file if you need to allow additional domains.

## Building the App

### 1. Open in Android Studio

```bash
# Navigate to the project directory
cd "My First Android App"

# Open Android Studio
# File -> Open -> Select the project folder
```

### 2. Sync Gradle

Android Studio will automatically sync Gradle. If not, click:
- **File** > **Sync Project with Gradle Files**

### 3. Build the APK

#### Debug Build (for testing)
```bash
# Using Gradle wrapper
./gradlew assembleDebug

# Output location:
# app/build/outputs/apk/debug/app-debug.apk
```

#### Release Build (for production)
```bash
# Using Gradle wrapper
./gradlew assembleRelease

# Output location:
# app/build/outputs/apk/release/app-release-unsigned.apk
```

### 4. Sign the Release APK

Before distributing, you need to sign the release APK:

```bash
# Generate a keystore (first time only)
keytool -genkey -v -keystore jacky-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias jacky

# Sign the APK
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 -keystore jacky-release-key.jks app/build/outputs/apk/release/app-release-unsigned.apk jacky

# Align the APK (optional but recommended)
zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app/build/outputs/apk/release/jacky-release.apk
```

## Installing the App

### On Android Emulator

1. Start an Android emulator from Android Studio
2. Run the app:
   ```bash
   ./gradlew installDebug
   ```

### On Physical Device

1. Enable **Developer Options** on your Android device:
   - Go to **Settings** > **About Phone**
   - Tap **Build Number** 7 times
   
2. Enable **USB Debugging**:
   - Go to **Settings** > **Developer Options**
   - Enable **USB Debugging**

3. Connect device via USB and run:
   ```bash
   adb devices  # Verify device is connected
   ./gradlew installDebug
   ```

### Manual Installation (APK)

1. Transfer the APK to your device
2. Open the APK file on the device
3. Allow installation from unknown sources if prompted
4. Install the app

## Testing

### Local Development Testing

1. **Start your Jacky web server**:
   ```bash
   cd jacky-2.0-complete
   npm start
   ```

2. **Update MainActivity.java** with your local URL:
   - Emulator: `http://10.0.2.2:3000`
   - Physical device: `http://YOUR_IP:3000`

3. **Build and install** the app

4. **Grant microphone permissions** when prompted

5. **Test voice features** by speaking with Jacky

### Production Testing

1. Deploy your Jacky web app to production
2. Update `JACKY_URL` to your production domain
3. Build a release APK
4. Test on multiple devices and Android versions

## Permissions

The app requests the following permissions:

- **INTERNET** - Required to load the web app
- **ACCESS_NETWORK_STATE** - Check network connectivity
- **RECORD_AUDIO** - Voice input for AI conversations
- **MODIFY_AUDIO_SETTINGS** - Adjust audio for calls
- **ACCESS_FINE_LOCATION** (optional) - Location-based services
- **ACCESS_COARSE_LOCATION** (optional) - Approximate location

Users will be prompted to grant microphone permissions on first use.

## Troubleshooting

### Issue: App shows blank screen

**Solution:**
- Check that your web server is running
- Verify the URL in `MainActivity.java` is correct
- Check Android Logcat for errors:
  ```bash
  adb logcat | grep "chromium"
  ```

### Issue: Microphone not working

**Solution:**
- Ensure microphone permissions are granted
- Check that your device has a working microphone
- Verify WebRTC permissions in the web app

### Issue: HTTP URL not loading

**Solution:**
- For development, ensure `usesCleartextTraffic="true"` is in AndroidManifest
- Check `network_security_config.xml` includes your domain
- For production, use HTTPS only

### Issue: "App not installed" error

**Solution:**
- Uninstall any previous version of the app
- Check that the APK is properly signed
- Ensure sufficient storage space on device

## App Store Deployment

### Google Play Store

1. **Create a Google Play Developer account** ($25 one-time fee)

2. **Prepare app assets**:
   - App icon (512x512 PNG)
   - Feature graphic (1024x500 PNG)
   - Screenshots (at least 2)
   - Privacy policy URL

3. **Create app listing**:
   - App title: "Jacky 2.0 - City of Midland AI"
   - Short description (80 chars max)
   - Full description (4000 chars max)
   - Category: Lifestyle or Productivity

4. **Upload release APK** (signed)

5. **Submit for review**

### Alternative Distribution

- **Amazon Appstore**
- **Samsung Galaxy Store**
- **Direct APK distribution** (your website)

## Architecture

### WebView-Based Approach

The app uses Android WebView to load your Jacky web application:

```
┌─────────────────────────────────┐
│     Android App (Native)        │
│  ┌───────────────────────────┐  │
│  │     MainActivity.java     │  │
│  │  ┌─────────────────────┐  │  │
│  │  │      WebView        │  │  │
│  │  │  ┌───────────────┐  │  │  │
│  │  │  │  Jacky 2.0    │  │  │  │
│  │  │  │  Web App      │  │  │  │
│  │  │  │  (HTML/CSS/JS)│  │  │  │
│  │  │  └───────────────┘  │  │  │
│  │  └─────────────────────┘  │  │
│  └───────────────────────────┘  │
└─────────────────────────────────┘
         ↓
    ┌──────────────┐
    │ Web Backend  │
    │ (Node.js)    │
    └──────────────┘
```

### Benefits
- ✅ Single codebase for web and mobile
- ✅ Instant updates (no app store review)
- ✅ Access to native device features
- ✅ Offline caching support

### Trade-offs
- ⚠️ Requires internet connection
- ⚠️ Dependent on web app performance
- ⚠️ Limited native UI customization

## Future Enhancements

Potential improvements for future versions:

1. **Push Notifications** - Notify users of updates
2. **Offline Mode** - Cache static assets locally
3. **Native Navigation** - Add bottom navigation bar
4. **Biometric Auth** - Fingerprint/face unlock
5. **Share Integration** - Share content from other apps
6. **Widget Support** - Home screen widget for quick access
7. **Background Services** - Keep connection alive
8. **Analytics Integration** - Firebase Analytics

## Support

For issues or questions:
- **Technical Issues**: Check Android Logcat logs
- **Web App Issues**: Check browser console in WebView
- **Permissions**: Ensure all required permissions are granted

## License

This Android app is part of the Jacky 2.0 project.

## Changelog

### Version 1.0.0 (2025-11-08)
- Initial Android app release
- WebView integration with Jacky 2.0 web app
- Microphone permission handling
- Network security configuration
- Deep link support
- Development and production build configurations
