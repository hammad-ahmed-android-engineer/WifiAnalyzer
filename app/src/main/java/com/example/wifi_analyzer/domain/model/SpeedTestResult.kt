package com.example.wifi_analyzer.domain.model

data class SpeedTestResult(
    val pingMs: Double = 0.0,
    val downloadSpeedMbps: Double = 0.0,
    val uploadSpeedMbps: Double = 0.0,
    val hostLocation: String = "",
    val distanceKm: Double = 0.0,
    val isTesting: Boolean = false,
    val isFinished: Boolean = false,
    val statusText: String = ""
)