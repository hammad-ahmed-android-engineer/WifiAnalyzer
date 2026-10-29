package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.SignalMeterData
import com.example.wifi_analyzer.domain.repository.WifiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSignalMeterUseCase(
    private val wifiRepository: WifiRepository
) {
    operator fun invoke(): Flow<SignalMeterData> {
        return wifiRepository.getConnectedWifiDetails().map { details ->
            val quality = when {
                details.signalDbm >= -55 -> "Excellent"
                details.signalDbm >= -67 -> "Good"
                details.signalDbm >= -80 -> "Fair"
                else -> "Weak Signal"
            }
            SignalMeterData(
                ssid = details.ssid,
                rssiDbm = details.signalDbm,
                percentage = details.signalPercentage,
                qualityLabel = quality,
                frequencyMhz = details.frequencyMhz,
                channel = if (details.frequencyMhz > 0) (details.frequencyMhz - 2412) / 5 + 1 else 0,
                linkSpeedMbps = details.linkSpeedMbps
            )
        }
    }
}