package com.example.wifi_analyzer.presentation.signal_meter

import com.example.wifi_analyzer.domain.model.SignalMeterData

data class SignalMeterUiState(
    val data: SignalMeterData? = null,
    val isLoading: Boolean = true
)