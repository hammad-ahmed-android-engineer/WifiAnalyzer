package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.QrCodeConfig
import com.example.wifi_analyzer.domain.repository.QrCodeRepository

class ParseQrCodeUseCase(
    private val qrCodeRepository: QrCodeRepository
) {
    operator fun invoke(qrText: String): QrCodeConfig? {
        return qrCodeRepository.parseWifiQrCode(qrText)
    }
}