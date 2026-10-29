package com.example.wifi_analyzer.domain.model

data class ConnectedDevice(
    val name: String,
    val ipAddress: String,
    val macAddress: String,
    val vendorName: String,
    val isCurrentDevice: Boolean = false
)