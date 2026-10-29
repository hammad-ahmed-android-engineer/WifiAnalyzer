package com.example.wifi_analyzer.presentation.qr_scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.AddWifiNetworkUseCase
import com.example.wifi_analyzer.domain.usecases.ParseQrCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrScannerViewModel @Inject constructor(
    private val parseQrCodeUseCase: ParseQrCodeUseCase,
    private val addWifiNetworkUseCase: AddWifiNetworkUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(QrScannerUiState())
    val uiState: StateFlow<QrScannerUiState> = _uiState.asStateFlow()

    fun onQrScanned(rawText: String) {
        val config = parseQrCodeUseCase(rawText)
        if (config != null) {
            _uiState.value = _uiState.value.copy(
                scannedConfig = config,
                isBottomSheetVisible = true
            )
        }
    }

    fun dismissBottomSheet() {
        _uiState.value = _uiState.value.copy(isBottomSheetVisible = false)
    }

    fun connectScannedWifi() {
        val config = _uiState.value.scannedConfig ?: return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isConnecting = true)
            val result = addWifiNetworkUseCase(config.ssid, config.password, config.securityType)
            _uiState.value = _uiState.value.copy(
                isConnecting = false,
                connectSuccess = result.isSuccess
            )
        }
    }
}