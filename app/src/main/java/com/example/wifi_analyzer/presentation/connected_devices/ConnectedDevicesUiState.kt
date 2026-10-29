package com.example.wifi_analyzer.presentation.connected_devices

import com.example.wifi_analyzer.domain.model.ConnectedDevice

data class ConnectedDevicesUiState(
    val devices: List<ConnectedDevice> = emptyList(),
    val isScanning: Boolean = false
)