package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.WifiDetails
import com.example.wifi_analyzer.domain.repository.WifiRepository
import kotlinx.coroutines.flow.Flow

class GetConnectedWifiUseCase(
    private val wifiRepository: WifiRepository
) {
    operator fun invoke(): Flow<WifiDetails> {
        return wifiRepository.getConnectedWifiDetails()
    }
}