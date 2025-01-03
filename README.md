# Positrarx Scanner Library
A lightweight Android library for QR code and barcode scanning built using ML Kit. This library provides an easy-to-use scanner activity that can be integrated into any Android project.

## Features
- Quick and easy barcode/QR code scanning
- Built using ML Kit for reliable scanning
- Simple integration with just a few lines of code
- Supports both QR codes and barcodes
- Customizable scanner UI and behavior
- Configurable orientation lock and sound options

## Installation
Add JitPack repository to your project's build.gradle file:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency to your app's build.gradle file:
```gradle
dependencies {
    implementation 'com.github.jithukrishnaju-tech:positrarx-scanner:1.0.1'
}
```

## Requirements
- Android SDK 21 (Android 5.0) or higher
- Camera permission must be handled by the implementing application
- Add camera permission in AndroidManifest.xml:
  ```xml
  <uses-permission android:name="android.permission.CAMERA" />
  ```

## Usage

### Scanner Configuration
The library provides a `ScannerConfigBuilder` class to customize the scanner behavior and appearance. Here are the available configuration options:

```kotlin
val scannerConfig = ScannerConfigBuilder()
    .setPoweredByText("Powered by ")           // Set custom powered by text
    .setGuideText("Scan QR/Barcode")          // Set custom guide text
    .setCompanyLogo(R.drawable.logo.xml) // Set company logo
    .setBeepSound(false)                      // Enable/disable beep sound on scan
    .setOrientationLock(true)                 // Lock screen orientation during scanning
    .build()
```

### Basic Implementation
1. Set up the activity result launcher to handle scanner results:
```kotlin
private val scannerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val scannedData = result.data?.getStringExtra(ScannerActivity.EXTRA_SCAN_RESULT)
        val scanFormat = result.data?.getStringExtra(ScannerActivity.EXTRA_SCAN_FORMAT)
        // Handle the scanned data here
    }
}
```

2. Start the scanner with configuration:
```kotlin
private fun startScannerActivity() {
    val intent = Intent(requireContext(), ScannerActivity::class.java)
    
    // Create and apply scanner configuration
    val scannerConfig = ScannerConfigBuilder()
        .setPoweredByText("Powered by ")
        .setGuideText("Scan QR/Barcode")
        .setBeepSound(false)
        .setOrientationLock(true)
        .build()
    
    intent.putExtras(scannerConfig)
    scannerLauncher.launch(intent)
}
```

### Intent Extras
The scanner activity returns the following extras in the result intent:
- `ScannerActivity.EXTRA_SCAN_RESULT`: Contains the raw value of the scanned code
- `ScannerActivity.EXTRA_SCAN_FORMAT`: Contains the format of the scanned code

## Sample Implementation
Here's a complete example of how to implement the scanner in a Fragment:

```kotlin
class ScannerFragment : Fragment() {
    private val scannerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val scannedData = result.data?.getStringExtra(ScannerActivity.EXTRA_SCAN_RESULT)
            val scanFormat = result.data?.getStringExtra(ScannerActivity.EXTRA_SCAN_FORMAT)
            
            // Handle your scanned data here
            handleScannedData(scannedData, scanFormat)
        }
    }

    private fun startScannerActivity() {
        val intent = Intent(requireContext(), ScannerActivity::class.java)
        
        // Configure scanner options
        val scannerConfig = ScannerConfigBuilder()
            .setPoweredByText("Powered by ")
            .setGuideText("Scan QR/Barcode")
            .setCompanyLogo(R.drawable.logo.xml)
            .setBeepSound(false)
            .setOrientationLock(true)
            .build()
            
        intent.putExtras(scannerConfig)
        scannerLauncher.launch(intent)
    }

    private fun handleScannedData(data: String?, format: String?) {
        // Handle your specific business logic here
    }
}
```

## Configuration Options

| Method | Description | Default Value |
|--------|-------------|---------------|
| `setPoweredByText(text: String)` | Sets the powered by text shown in the scanner UI | "Powered by " |
| `setGuideText(text: String)` | Sets the guidance text shown to the user | "Scan QR/Barcode" |
| `setCompanyLogo(drawable: Int)` | Sets the desired logo in the scanner UI | Default library logo |
| `setBeepSound(enabled: Boolean)` | Enables or disables beep sound on successful scan | true |
| `setOrientationLock(enabled: Boolean)` | Locks the screen orientation during scanning | false |
