package com.example.wifi_analyzer.domain.model

data class WifiDetails(
    val ssid: String,
    val bssid: String,
    val ipAddress: String,
    val macAddress: String,
    val linkSpeedMbps: Int,
    val frequencyMhz: Int,
    val networkId: Int,
    val signalDbm: Int,
    val signalPercentage: Int,
    val gatewayIp: String,
    val netmask: String,
    val dns1: String,
    val dns2: String,
    val securityType: String
)