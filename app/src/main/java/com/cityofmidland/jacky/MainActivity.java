package com.cityofmidland.jacky;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * MainActivity for Jacky 2.0 - City of Midland AI Assistant
 * WebView-based Android application that loads the Jacky web interface
 * with support for microphone access and modern web features
 */
public class MainActivity extends AppCompatActivity {
    
    private static final String JACKY_URL = "https://jacky.midlandtexas.gov"; // Update with your production URL
    // For development/testing, use: "http://10.0.2.2:3000" (Android emulator localhost)
    // For local network testing, use your computer's IP: "http://192.168.x.x:3000"
    
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String[] REQUIRED_PERMISSIONS = {
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.MODIFY_AUDIO_SETTINGS,
        Manifest.permission.INTERNET
    };
    
    private WebView webView;
    
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize WebView
        webView = findViewById(R.id.webview);
        
        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        
        // Enable mixed content (HTTP and HTTPS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        
        // Enable caching for better performance
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        
        // Set user agent to include Android info
        String userAgent = webSettings.getUserAgentString();
        webSettings.setUserAgentString(userAgent + " JackyAndroid/1.0");
        
        // Configure WebViewClient to handle page navigation
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Keep navigation within the WebView
                view.loadUrl(url);
                return true;
            }
            
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Inject JavaScript to notify web app it's running in Android
                webView.evaluateJavascript(
                    "window.isAndroidApp = true; " +
                    "window.androidVersion = '" + Build.VERSION.RELEASE + "';",
                    null
                );
            }
        });
        
        // Configure WebChromeClient to handle permissions and dialogs
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                // Handle web permissions (microphone, camera, etc.)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    runOnUiThread(() -> {
                        String[] requestedResources = request.getResources();
                        for (String resource : requestedResources) {
                            if (resource.equals(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
                                if (checkPermissions()) {
                                    request.grant(new String[]{PermissionRequest.RESOURCE_AUDIO_CAPTURE});
                                } else {
                                    requestPermissions();
                                    request.deny();
                                }
                                return;
                            }
                        }
                        request.deny();
                    });
                }
            }
            
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, 
                    GeolocationPermissions.Callback callback) {
                // Allow geolocation if needed
                callback.invoke(origin, true, false);
            }
        });
        
        // Check and request permissions
        if (checkPermissions()) {
            loadJackyApp();
        } else {
            requestPermissions();
        }
    }
    
    /**
     * Check if all required permissions are granted
     */
    private boolean checkPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) 
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Request required permissions from user
     */
    private void requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            REQUIRED_PERMISSIONS,
            PERMISSION_REQUEST_CODE
        );
    }
    
    /**
     * Handle permission request results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            
            if (allGranted) {
                loadJackyApp();
            } else {
                Toast.makeText(this, 
                    "Microphone permission is required for voice conversations with Jacky",
                    Toast.LENGTH_LONG).show();
                // Load anyway, but voice features won't work
                loadJackyApp();
            }
        }
    }
    
    /**
     * Load the Jacky web application
     */
    private void loadJackyApp() {
        webView.loadUrl(JACKY_URL);
    }
    
    /**
     * Handle back button to navigate WebView history
     */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    
    /**
     * Clean up WebView on destroy
     */
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
    
    /**
     * Pause WebView when app goes to background
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }
    
    /**
     * Resume WebView when app comes to foreground
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }
}
