# Quick Start Guide - Jacky 2.0 Android App

## Before You Begin

Make sure you have:
1. ✅ Android Studio installed
2. ✅ JDK 21 configured (already done!)
3. ✅ Your Jacky web server accessible

## Step-by-Step Setup

### 1. Configure Your Web App URL

Open this file:
```
app/src/main/java/com/cityofmidland/jacky/MainActivity.java
```

Find line 26 and update the URL:

**For Testing with Android Emulator:**
```java
private static final String JACKY_URL = "http://10.0.2.2:3000";
```

**For Testing on Your Phone:**
```java
private static final String JACKY_URL = "http://YOUR_COMPUTER_IP:3000";
// Example: "http://192.168.1.100:3000"
```

**For Production:**
```java
private static final String JACKY_URL = "https://jacky.midlandtexas.gov";
```

### 2. Start Your Jacky Web Server

```bash
cd jacky-2.0-complete
npm start
```

Make sure it's running on port 3000 (or update the URL if different).

### 3. Open Project in Android Studio

1. Launch Android Studio
2. Click **File** > **Open**
3. Navigate to: `My First Android App` folder
4. Click **OK**
5. Wait for Gradle to sync (may take a few minutes)

### 4. Run the App

#### Option A: On Android Emulator

1. Click the **Device Manager** icon in Android Studio
2. Create a new virtual device (if you don't have one)
   - Recommended: Pixel 7, API 34
3. Click the green **Run** button (▶️)
4. Select your emulator
5. Wait for the app to launch

#### Option B: On Your Physical Phone

1. Enable Developer Mode on your phone:
   - Go to **Settings** > **About Phone**
   - Tap **Build Number** 7 times

2. Enable USB Debugging:
   - Go to **Settings** > **Developer Options**
   - Turn on **USB Debugging**

3. Connect phone to computer via USB

4. Click the green **Run** button (▶️) in Android Studio

5. Select your phone from the list

### 5. Grant Permissions

When the app launches:
1. It will ask for **Microphone** permission
2. Click **Allow**
3. The app will load your Jacky web interface

## Testing Checklist

Once the app loads:

- [ ] Web interface loads correctly
- [ ] Click "Start Conversation" button
- [ ] Speak into your phone's microphone
- [ ] Jacky responds with voice
- [ ] Transcript appears on screen
- [ ] Star rating system works
- [ ] Language toggle works (English/Spanish)

## Common Issues

### ❌ App shows blank white screen

**Fix:**
1. Check your web server is running: `http://localhost:3000`
2. If using emulator, make sure URL is `http://10.0.2.2:3000`
3. If using phone, make sure phone and computer are on same WiFi

### ❌ "Unable to resolve host" error

**Fix:**
1. Check your WiFi connection
2. Verify the URL in MainActivity.java is correct
3. Try pinging your server from terminal:
   ```bash
   ping 192.168.x.x
   ```

### ❌ Microphone not working

**Fix:**
1. Go to Android Settings
2. Apps > Jacky 2.0 > Permissions
3. Make sure Microphone is allowed

## Building APK for Distribution

### Debug APK (for testing)

```bash
cd "My First Android App"
./gradlew assembleDebug
```

APK Location: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK (for distribution)

```bash
./gradlew assembleRelease
```

APK Location: `app/build/outputs/apk/release/app-release-unsigned.apk`

**Note:** Release APKs must be signed before installation. See ANDROID_APP_README.md for signing instructions.

## Next Steps

1. **Test thoroughly** on multiple devices
2. **Customize the app icon** (optional)
3. **Set up deep linking** to your domain
4. **Prepare for Play Store** submission
5. **Add crash reporting** (Firebase Crashlytics)

## Need Help?

Check these files for detailed information:
- `ANDROID_APP_README.md` - Complete documentation
- `JAVA_UPGRADE_SUMMARY.md` - Java version info
- Android Logcat in Android Studio for error logs

## URLs Reference

| Environment | URL | Use Case |
|------------|-----|----------|
| Local (Emulator) | `http://10.0.2.2:3000` | Testing on Android emulator |
| Local (Phone) | `http://192.168.x.x:3000` | Testing on physical device |
| Staging | `https://staging.jacky.midlandtexas.gov` | Pre-production testing |
| Production | `https://jacky.midlandtexas.gov` | Live app |

## Important Files

```
My First Android App/
├── app/
│   ├── src/main/
│   │   ├── java/com/cityofmidland/jacky/
│   │   │   └── MainActivity.java          ← Update URL here!
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml  ← WebView layout
│   │   │   ├── values/strings.xml        ← App name, strings
│   │   │   └── xml/network_security_config.xml ← Network settings
│   │   └── AndroidManifest.xml           ← Permissions, config
│   └── build.gradle                      ← App dependencies
├── build.gradle                          ← Project config
└── gradle.properties                     ← Java 21 settings
```

---

**Ready to build!** 🚀

Run these commands in order:
```bash
# 1. Start your web server
cd jacky-2.0-complete && npm start

# 2. In another terminal, build the app
cd "My First Android App"
./gradlew assembleDebug

# 3. Or just run in Android Studio with the Run button!
```
