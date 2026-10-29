package com.example.wifi_analyzer.data.repository

import android.graphics.Bitmap
import android.graphics.Color
import com.example.wifi_analyzer.domain.model.QrCodeConfig
import com.example.wifi_analyzer.domain.repository.QrCodeRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QrCodeRepositoryImpl : QrCodeRepository {

    override fun generateQrCodeBitmap(config: QrCodeConfig): Result<Bitmap> {
        return try {
            val qrText = "WIFI:S:${config.ssid};T:${config.securityType};P:${config.password};H:${config.isHidden};;"
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(qrText, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            Result.success(bitmap)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun parseWifiQrCode(qrText: String): QrCodeConfig? {
        if (!qrText.startsWith("WIFI:")) return null
        return try {
            val ssidMatch = Regex("S:([^;]+)").find(qrText)?.groupValues?.get(1) ?: ""
            val passMatch = Regex("P:([^;]+)").find(qrText)?.groupValues?.get(1) ?: ""
            val typeMatch = Regex("T:([^;]+)").find(qrText)?.groupValues?.get(1) ?: "WPA"
            val hiddenMatch = Regex("H:(true|false)").find(qrText)?.groupValues?.get(1)?.toBoolean() ?: false
            QrCodeConfig(
                ssid = ssidMatch,
                password = passMatch,
                securityType = typeMatch,
                isHidden = hiddenMatch
            )
        } catch (_: Exception) {
            null
        }
    }
}