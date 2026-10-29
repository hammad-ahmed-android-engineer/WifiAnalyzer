package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.repository.WifiRepository

class AddWifiNetworkUseCase(
    private val wifiRepository: WifiRepository
) {
    suspend operator fun invoke(ssid: String, password: String, securityType: String): Result<Boolean> {
        return wifiRepository.addAndConnectWifi(ssid, password, securityType)
    }
}