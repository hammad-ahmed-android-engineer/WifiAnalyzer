package com.example.wifi_analyzer.domain.repository

import com.example.wifi_analyzer.domain.model.ConnectedDevice
import kotlinx.coroutines.flow.Flow

interface DeviceScannerRepository {
    fun scanConnectedDevices(): Flow<List<ConnectedDevice>>
}