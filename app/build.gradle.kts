plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.wifi_analyzer"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.wifi_analyzer"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.coreSplashscreen)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.commonsLang3)

    // Hilt DI
    implementation("com.google.dagger:hilt-android:2.60.1")
    add("kapt", "com.google.dagger:hilt-compiler:2.60.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Compose BOM & UI
    val composeBom = platform(libs.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    debugImplementation(libs.composeUiTooling)
    implementation(libs.composeMaterial3)
    implementation("androidx.compose.material:material-icons-extended")

    implementation(libs.activityCompose)
    implementation(libs.lifecycleViewModelCompose)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.navigationCompose)

    // Scalable dimensions SDP & SSP
    implementation(libs.sdpAndroid)
    implementation(libs.sspAndroid)

    // ZXing for QR Code generation & decoding
    implementation(libs.zxingCore)

    // CameraX & ML Kit for QR scanning
    implementation(libs.cameraxCore)
    implementation(libs.cameraxCamera2)
    implementation(libs.cameraxLifecycle)
    implementation(libs.cameraxView)
    implementation(libs.mlkitBarcodeScanning)

    testImplementation(libs.junit)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)
}