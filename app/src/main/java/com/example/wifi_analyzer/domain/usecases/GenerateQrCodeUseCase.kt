package com.example.wifi_analyzer.domain.usecases

import android.graphics.Bitmap
import com.example.wifi_analyzer.domain.model.QrCodeConfig
import com.example.wifi_analyzer.domain.repository.QrCodeRepository

class GenerateQrCodeUseCase(
    private val qrCodeRepository: QrCodeRepository
) {
    operator fun invoke(config: QrCodeConfig): Result<Bitmap> {
        return qrCodeRepository.generateQrCodeBitmap(config)
    }
}