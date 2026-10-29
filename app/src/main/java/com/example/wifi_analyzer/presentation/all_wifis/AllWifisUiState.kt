package com.example.wifi_analyzer.presentation.all_wifis

import com.example.wifi_analyzer.domain.model.WifiNetwork

data class AllWifisUiState(
    val networks: List<WifiNetwork> = emptyList(),
    val isScanning: Boolean = false
)