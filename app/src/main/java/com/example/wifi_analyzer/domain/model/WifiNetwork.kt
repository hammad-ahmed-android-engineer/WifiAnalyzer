package com.example.wifi_analyzer.domain.model

data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val levelDbm: Int,
    val signalPercentage: Int,
    val frequency: Int,
    val channel: Int,
    val capabilities: String,
    val isConnected: Boolean = false
)