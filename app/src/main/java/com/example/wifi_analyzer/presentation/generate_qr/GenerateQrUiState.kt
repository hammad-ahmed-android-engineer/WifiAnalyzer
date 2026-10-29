package com.example.wifi_analyzer.presentation.generate_qr

import android.graphics.Bitmap

data class GenerateQrUiState(
    val ssid: String = "",
    val password: String = "",
    val securityType: String = "WPA",
    val isHidden: Boolean = false,
    val qrBitmap: Bitmap? = null,
    val errorMessage: String? = null
)