package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.WifiNetwork
import com.example.wifi_analyzer.domain.repository.WifiRepository
import kotlinx.coroutines.flow.Flow

class ScanWifiNetworksUseCase(
    private val wifiRepository: WifiRepository
) {
    operator fun invoke(): Flow<List<WifiNetwork>> {
        return wifiRepository.scanWifiNetworks()
    }
}