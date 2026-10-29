package com.example.wifi_analyzer.domain.model

data class QrCodeConfig(
    val ssid: String,
    val password: String,
    val securityType: String = "WPA",
    val isHidden: Boolean = false
)