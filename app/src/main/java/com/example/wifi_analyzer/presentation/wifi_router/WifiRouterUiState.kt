package com.example.wifi_analyzer.presentation.wifi_router

data class WifiRouterUiState(
    val gatewayIp: String = "192.168.1.1",
    val isLoading: Boolean = false
)