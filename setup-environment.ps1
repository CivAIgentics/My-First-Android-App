# Android Development Setup Script for Windows
# This script helps install Java JDK and provides guidance for Android Studio setup

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Android App Development Setup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if running as Administrator
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
if (-not $isAdmin) {
    Write-Host "⚠️  Note: Running without administrator privileges. Some installations may require admin rights." -ForegroundColor Yellow
    Write-Host ""
}

# Function to check if a command exists
function Test-Command {
    param($Command)
    try {
        if (Get-Command $Command -ErrorAction Stop) {
            return $true
        }
    }
    catch {
        return $false
    }
}

# Check Java installation
Write-Host "Checking Java installation..." -ForegroundColor Yellow
if (Test-Command "java") {
    $javaVersion = java -version 2>&1 | Select-String "version" | ForEach-Object { $_ -replace '.*version "(.+)".*', '$1' }
    Write-Host "✅ Java is installed: $javaVersion" -ForegroundColor Green
    
    if ($env:JAVA_HOME) {
        Write-Host "✅ JAVA_HOME is set: $env:JAVA_HOME" -ForegroundColor Green
    } else {
        Write-Host "⚠️  JAVA_HOME is not set" -ForegroundColor Yellow
    }
} else {
    Write-Host "❌ Java is NOT installed" -ForegroundColor Red
    Write-Host ""
    Write-Host "📥 To install Java JDK:" -ForegroundColor Cyan
    Write-Host "   1. Visit: https://adoptium.net/" -ForegroundColor White
    Write-Host "   2. Download JDK 17 or later (LTS version recommended)" -ForegroundColor White
    Write-Host "   3. Run the installer" -ForegroundColor White
    Write-Host "   4. During installation, check 'Set JAVA_HOME variable'" -ForegroundColor White
    Write-Host ""
    
    $openBrowser = Read-Host "Would you like to open the download page now? (Y/N)"
    if ($openBrowser -eq "Y" -or $openBrowser -eq "y") {
        Start-Process "https://adoptium.net/"
    }
}

Write-Host ""

# Check Android Studio / Android SDK
Write-Host "Checking Android SDK installation..." -ForegroundColor Yellow
if ($env:ANDROID_HOME) {
    Write-Host "✅ ANDROID_HOME is set: $env:ANDROID_HOME" -ForegroundColor Green
    
    if (Test-Path $env:ANDROID_HOME) {
        Write-Host "✅ Android SDK directory exists" -ForegroundColor Green
    } else {
        Write-Host "⚠️  ANDROID_HOME path does not exist" -ForegroundColor Yellow
    }
} else {
    Write-Host "❌ ANDROID_HOME is NOT set" -ForegroundColor Red
    Write-Host ""
    Write-Host "📥 To install Android Studio:" -ForegroundColor Cyan
    Write-Host "   1. Visit: https://developer.android.com/studio" -ForegroundColor White
    Write-Host "   2. Download Android Studio" -ForegroundColor White
    Write-Host "   3. Run the installer and follow the setup wizard" -ForegroundColor White
    Write-Host "   4. During setup, ensure these are installed:" -ForegroundColor White
    Write-Host "      - Android SDK" -ForegroundColor White
    Write-Host "      - Android SDK Platform 34" -ForegroundColor White
    Write-Host "      - Android SDK Build-Tools" -ForegroundColor White
    Write-Host "      - Android Emulator" -ForegroundColor White
    Write-Host ""
    Write-Host "   5. After installation, set ANDROID_HOME:" -ForegroundColor White
    Write-Host "      - Usually: C:\Users\$env:USERNAME\AppData\Local\Android\Sdk" -ForegroundColor White
    Write-Host ""
    
    $openBrowser = Read-Host "Would you like to open the download page now? (Y/N)"
    if ($openBrowser -eq "Y" -or $openBrowser -eq "y") {
        Start-Process "https://developer.android.com/studio"
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Environment Variables Setup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Suggest environment variable setup
$sdkPath = "C:\Users\$env:USERNAME\AppData\Local\Android\Sdk"
$javaPath = Get-ChildItem "C:\Program Files\Eclipse Adoptium" -ErrorAction SilentlyContinue | Select-Object -First 1

Write-Host "To set environment variables permanently:" -ForegroundColor Yellow
Write-Host ""
Write-Host "1. Press Win + X and select 'System'" -ForegroundColor White
Write-Host "2. Click 'Advanced system settings'" -ForegroundColor White
Write-Host "3. Click 'Environment Variables'" -ForegroundColor White
Write-Host "4. Under 'User variables', click 'New' and add:" -ForegroundColor White
Write-Host ""
Write-Host "   Variable: ANDROID_HOME" -ForegroundColor Cyan
Write-Host "   Value: $sdkPath" -ForegroundColor White
Write-Host ""

if ($javaPath) {
    Write-Host "   Variable: JAVA_HOME" -ForegroundColor Cyan
    Write-Host "   Value: $($javaPath.FullName)" -ForegroundColor White
} else {
    Write-Host "   Variable: JAVA_HOME" -ForegroundColor Cyan
    Write-Host "   Value: C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot" -ForegroundColor White
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Next Steps" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "After installing Java and Android Studio:" -ForegroundColor Yellow
Write-Host "1. Restart your terminal/PowerShell" -ForegroundColor White
Write-Host "2. Navigate to your project directory" -ForegroundColor White
Write-Host "3. Run: .\gradlew.bat build" -ForegroundColor White
Write-Host "4. To run on emulator: .\gradlew.bat installDebug" -ForegroundColor White
Write-Host ""
Write-Host "For more details, see README.md in your project folder" -ForegroundColor White
Write-Host ""

Read-Host "Press Enter to exit"
