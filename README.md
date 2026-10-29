# 📶 Wifi Analyzer - Advanced Network Diagnostics & Wi-Fi Toolkit

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.21-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android Gradle Plugin](https://img.shields.io/badge/AGP-9.4.1-blue.svg?style=flat&logo=android)](https://developer.android.com/studio/releases/gradle-plugin)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202026.09.00-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Material%203-Expressive-orange.svg?style=flat&logo=materialdesign)](https://m3.material.io/)
[![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM%20%2B%20UDF-brightgreen.svg?style=flat)](#-architecture--design-patterns)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)

**Wifi Analyzer** is a feature-rich, native Android network diagnostics, speed testing, and Wi-Fi management toolkit. Built with **Kotlin**, **Jetpack Compose (Material 3)**, and strict **Clean Architecture**, Wifi Analyzer empowers users to inspect signal strength, evaluate channel congestion, scan nearby Wi-Fi networks, discover connected local devices, run accurate speed tests, generate and scan QR codes, and configure router settings—all with zero unnecessary telemetry and robust local privacy.

---

## 🌟 Overview & Philosophy

Wifi Analyzer operates on three core principles:
1. **Local-First & Privacy-Focused**: Network diagnostics, signal measurements, local subnet discovery, and QR credentials remain strictly on your local device.
2. **Lightning-Fast Diagnostics**: From instant Wi-Fi scanning to real-time speed testing and subnet device detection, every tool is optimized for responsive execution.
3. **Engineering Excellence**: Built with strict Clean Architecture, Unidirectional Data Flow (UDF), responsive dual-orientation layouts, and scalable dimension tokens (`sdp`/`ssp`).

---

## ✨ Core Features & Capabilities

### 📡 1. Real-Time Signal Strength Meter
- Live RSSI dBm gauge with percentage calculation and signal quality rating (Excellent, Good, Fair, Weak).
- Instant feedback on connection speed and link parameters.

### 📊 2. Wi-Fi Channel Rating Analyzer
- Evaluates 2.4 GHz and 5 GHz channels for interference and congestion.
- Provides star ratings (★1 to ★5) and smart recommendations on the best channel to use.

### 🌐 3. All Nearby Wi-Fi Networks Scanner
- Scans and lists nearby Wi-Fi access points with SSID, BSSID, channel number, frequency, and security capabilities (WPA2/WPA3).

### 🔍 4. Connected Devices Discovery (Who's on my Wi-Fi)
- Subnet ping scanner inspecting local ARP tables (`/proc/net/arp`).
- Identifies active devices, IP addresses, MAC addresses, and manufacturer OUI vendor lookup.

### ⚡ 5. Multi-Metric Speed Test
- Comprehensive speed testing suite measuring **Ping Latency**, **Download Speed**, and **Upload Speed**.
- Interactive animated gauge meters and graphical progression representation.

### 🔐 6. Secure Wi-Fi Password Generator
- Customizable length (6 to 32 characters) and character sets (Uppercase, Lowercase, Numbers, Symbols).
- One-tap copy to clipboard and native sharing options.

### 📷 7. Wi-Fi QR Code Generator & Scanner
- Generate connectable Wi-Fi QR codes with SSID, security type, and password encryption.
- CameraX & ML Kit powered live QR code scanner to instantly decode and connect to Wi-Fi networks.

### 🛜 8. Wi-Fi Router Admin Setup
- Automatically detects gateway IP (e.g., `192.168.1.1`) and embeds an admin WebView or launches the router configuration page in an external browser.
- Built-in default credentials reference for major router brands.

### ➕ 9. Add Wi-Fi Network & Hidden Configuration
- Save and connect to Wi-Fi networks including hidden SSIDs and custom security protocols.

---

## 📐 Architecture & Design Patterns

Wifi Analyzer follows modern Android architectural standards with strict **Clean Architecture**, **MVVM**, and **Unidirectional Data Flow (UDF)**:

### Complete Project Directory & Package Structure

```
com.example.wifi_analyzer/
├── core/
│   ├── designsystem/
│   │   ├── components/            # Centralized Design System primitives (AppAlertDialog, AppBottomSheet, AppButton, AppCard, AppCheckbox, AppCircularProgressIndicator, AppConfirmDialog, AppIcon, AppIconButton, AppImage, AppNavigationDrawer, AppOutlinedButton, AppOutlinedTextField, AppProgressBar, AppRadioButton, AppRadioButtonGroup, AppSlider, AppSwitch, AppText, AppTextField, AppTopBar)
│   │   └── theme/                 # Theme, Color, AppTypography, AppDimensions
│   └── utils/                     # Kotlin extensions & utilities (ClickDebounce, ContextExtensions, FormatExtensions, NetworkExtensions)
│
├── data/
│   ├── local/                     # Local data sources (MacVendorLocalDataSource for JSON assets, WifiLocalDataSource for WifiManager & ConnectivityManager)
│   └── repository/                # Concrete repository implementations (DeviceScannerRepositoryImpl, PasswordGeneratorRepositoryImpl, QrCodeRepositoryImpl, SpeedTestRepositoryImpl, WifiRepositoryImpl)
│
├── di/                            # Dagger Hilt Modules
│   ├── AppModule.kt               # Application scope bindings & context providers
│   ├── RepositoryModule.kt        # Interface binding modules (@Provides)
│   └── UseCaseModule.kt           # Use case providers (@Provides)
│
├── domain/
│   ├── model/                     # Domain models (ChannelRating, ConnectedDevice, PasswordOptions, QrCodeConfig, SignalMeterData, SpeedTestResult, WifiDetails, WifiNetwork)
│   ├── repository/                # Abstract repository interfaces
│   └── usecase/                   # Single-responsibility business use cases
│       ├── AddWifiNetworkUseCase.kt
│       ├── GeneratePasswordUseCase.kt
│       ├── GenerateQrCodeUseCase.kt
│       ├── GetChannelRatingsUseCase.kt
│       ├── GetConnectedWifiUseCase.kt
│       ├── GetSignalMeterUseCase.kt
│       ├── ParseQrCodeUseCase.kt
│       ├── RunSpeedTestUseCase.kt
│       ├── ScanConnectedDevicesUseCase.kt
│       └── ScanWifiNetworksUseCase.kt
│
├── presentation/                  # Presentation Layer (Jetpack Compose + Hilt + MVVM + UDF)
│   ├── add_wifi/                  # Add Wi-Fi Screen, UiState, & ViewModel
│   ├── all_wifis/                 # All Wi-Fis Screen, CardItem, UiState, & ViewModel
│   ├── channel_rating/            # Channel Rating Screen, CardItem, UiState, & ViewModel
│   ├── connected_devices/         # Connected Devices Screen, ItemCard, UiState, & ViewModel
│   ├── generate_qr/               # Generate QR Screen, UiState, & ViewModel
│   ├── main/                      # Home Screen, DashboardCard, UiState, & ViewModel
│   ├── navigation/                # NavGraph & NavRoutes
│   ├── password_generator/        # Password Generator Screen, UiState, & ViewModel
│   ├── qr_scanner/                # QR Scanner Screen, ResultBottomSheet, UiState, & ViewModel
│   ├── signal_meter/              # Signal Meter Screen, GaugeComponent, UiState, & ViewModel
│   ├── speed_test/                # Speed Test Screen, GaugeComponent, UiState, & ViewModel
│   ├── splash/                    # SplashScreen, UiState, & ViewModel
│   ├── wifi_info/                 # Wi-Fi Info Screen, DetailCard, UiState, & ViewModel
│   └── wifi_router/               # Wi-Fi Router Admin Screen, UiState, & ViewModel
│
├── MainActivity.kt                # Single Activity entry point & edge-to-edge / Compose host
└── MyApplication.kt               # Application entry point (@HiltAndroidApp)
```

### Key Engineering Practices:
- **Unidirectional Data Flow (UDF)**: ViewModels expose immutable `StateFlow<UiState>` collected with `collectAsStateWithLifecycle()`.
- **Debounced Interactions**: User actions use a 500ms debounce guard (`clickableDebounced` / `rememberDebouncedClick`) to prevent duplicate touch triggers.
- **Strict Design System Adherence**: 100% of screens, components, dialogs, and bottom sheets leverage centralized app common components (`AppTopBar`, `AppIconButton`, `AppCard`, `AppAlertDialog`, `AppBottomSheet`, etc.) for consistency.
- **Adaptive Layouts**: Responsive Grid and column layouts that scale smoothly across portrait and landscape orientations.

---

## 🛠 Tech Stack & Dependencies

All dependencies are centrally managed via Gradle Version Catalog ([`libs.versions.toml`](gradle/libs.versions.toml)):

| Category | Library / Dependency | Version | Purpose |
| :--- | :--- | :--- | :--- |
| **Language** | [Kotlin](https://kotlinlang.org/) | `2.4.21` | Modern, expressive programming language |
| **Build System** | [Android Gradle Plugin (AGP)](https://developer.android.com/build) | `9.4.1` | Project build configuration |
| **UI Framework** | [Jetpack Compose BOM](https://developer.android.com/develop/ui/compose/bom) | `2026.09.00` | Declarative UI toolkit |
| **Design System** | [Material 3](https://developer.android.com/develop/ui/compose/components) | BOM Managed | Expressive Material Design components |
| **Icons** | Material Icons Extended | BOM Managed | Comprehensive icon set |
| **Dependency Injection** | [Dagger Hilt](https://dagger.dev/hilt/) | `2.60.1` | Dependency injection framework |
| **Hilt Navigation** | Hilt Navigation Compose | `1.2.0` | ViewModel injection in Navigation destinations |
| **Navigation** | [Navigation Compose](https://developer.android.com/guide/navigation) | `2.10.2` | Single-activity Compose navigation |
| **Responsive Units** | [SDP & SSP Android](https://github.com/intuit/sdp) | `1.1.1` | Scalable size and font dimensions |
| **QR Code Processing** | [ZXing Android Embedded](https://github.com/journeyapps/zxing-android-embedded) | `3.5.4` | QR Code generation and decoding |
| **Camera & Barcode** | [CameraX & ML Kit Barcode Scanning](https://developers.google.com/ml-kit/vision/barcode-scanning) | `1.6.2` / `17.3.0` | High-performance live QR camera scanning |
| **Utilities** | Apache Commons Lang3 | `3.21.0` | String and array helper utilities |

---

## 🚀 Getting Started & Git Instructions

### Prerequisites
- **Android Studio**: Android Studio Ladybug (2024.2+) or newer.
- **Java Development Kit (JDK)**: JDK 17, JDK 21, or JDK 25.
- **Android SDK Requirements**:
  - `compileSdk`: `37`
  - `targetSdk`: `37`
  - `minSdk`: `24` (Android 7.0 Nougat or higher)

### 1. Clone the Repository
```bash
git clone https://github.com/hammad-ahmed-android-engineer/WifiAnalyzer.git
cd WifiAnalyzer
```

### 2. Open Project in Android Studio
1. Launch Android Studio.
2. Select **File > Open...** and navigate to the cloned `WifiAnalyzer` directory.
3. Allow Gradle to synchronize dependencies.

### 3. Build & Run via Command Line

#### Assemble Debug APK:
```bash
# Windows
.\gradlew.bat assembleDebug

# macOS / Linux
./gradlew assembleDebug
```
The output APK is generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

#### Install Directly onto a Connected Device / Emulator:
```bash
# Windows
.\gradlew.bat installDebug

# macOS / Linux
./gradlew installDebug
```

---

## 🔒 Permissions

Wifi Analyzer requests necessary local permissions for network analysis and camera scanning:

- `android.permission.ACCESS_WIFI_STATE` & `android.permission.CHANGE_WIFI_STATE`: Access and manage Wi-Fi connections.
- `android.permission.ACCESS_FINE_LOCATION` & `android.permission.ACCESS_COARSE_LOCATION`: Required by Android OS for Wi-Fi scan results and RSSI signal measurements.
- `android.permission.ACCESS_NETWORK_STATE` & `android.permission.CHANGE_NETWORK_STATE`: Monitor network connectivity status.
- `android.permission.INTERNET`: Required for speed tests and router admin web views.
- `android.permission.CAMERA`: Used by the QR code scanner.
- `android.permission.VIBRATE`: Haptic feedback on scans and speed tests.

---

## 📄 License

This project is open-source and distributed under the terms of the [Apache License 2.0](LICENSE).
