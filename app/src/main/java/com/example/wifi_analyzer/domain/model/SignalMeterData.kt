package com.example.wifi_analyzer.domain.model

data class SignalMeterData(
    val ssid: String,
    val rssiDbm: Int,
    val percentage: Int,
    val qualityLabel: String,
    val frequencyMhz: Int,
    val channel: Int,
    val linkSpeedMbps: Int
)