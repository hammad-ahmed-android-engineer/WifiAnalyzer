package com.example.wifi_analyzer.presentation.add_wifi

data class AddWifiUiState(
    val ssid: String = "",
    val password: String = "",
    val selectedSecurity: String = "WPA2",
    val isHidden: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)