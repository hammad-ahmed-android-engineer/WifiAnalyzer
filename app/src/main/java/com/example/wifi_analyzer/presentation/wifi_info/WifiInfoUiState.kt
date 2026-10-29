package com.example.wifi_analyzer.presentation.wifi_info

import com.example.wifi_analyzer.domain.model.WifiDetails

data class WifiInfoUiState(
    val details: WifiDetails? = null,
    val isLoading: Boolean = true
)