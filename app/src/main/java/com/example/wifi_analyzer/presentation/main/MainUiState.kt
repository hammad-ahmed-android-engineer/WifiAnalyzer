package com.example.wifi_analyzer.presentation.main

import com.example.wifi_analyzer.domain.model.WifiDetails

data class MainUiState(
    val connectedWifi: WifiDetails? = null,
    val isConnected: Boolean = false,
    val selectedDrawerItem: String = "home"
)