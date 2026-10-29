package com.example.wifi_analyzer.presentation.qr_scanner

import com.example.wifi_analyzer.domain.model.QrCodeConfig

data class QrScannerUiState(
    val scannedConfig: QrCodeConfig? = null,
    val isBottomSheetVisible: Boolean = false,
    val isConnecting: Boolean = false,
    val connectSuccess: Boolean = false
)