package com.example.wifi_analyzer.domain.repository

import android.graphics.Bitmap
import com.example.wifi_analyzer.domain.model.QrCodeConfig

interface QrCodeRepository {
    fun generateQrCodeBitmap(config: QrCodeConfig): Result<Bitmap>
    fun parseWifiQrCode(qrText: String): QrCodeConfig?
}