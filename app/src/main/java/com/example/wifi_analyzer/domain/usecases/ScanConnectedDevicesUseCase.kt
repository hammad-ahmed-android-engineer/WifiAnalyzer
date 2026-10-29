package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.ConnectedDevice
import com.example.wifi_analyzer.domain.repository.DeviceScannerRepository
import kotlinx.coroutines.flow.Flow

class ScanConnectedDevicesUseCase(
    private val deviceScannerRepository: DeviceScannerRepository
) {
    operator fun invoke(): Flow<List<ConnectedDevice>> {
        return deviceScannerRepository.scanConnectedDevices()
    }
}