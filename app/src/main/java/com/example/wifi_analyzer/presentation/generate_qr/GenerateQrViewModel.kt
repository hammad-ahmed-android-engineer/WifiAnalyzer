package com.example.wifi_analyzer.presentation.generate_qr

import androidx.lifecycle.ViewModel
import com.example.wifi_analyzer.domain.model.QrCodeConfig
import com.example.wifi_analyzer.domain.usecases.GenerateQrCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GenerateQrViewModel @Inject constructor(
    private val generateQrCodeUseCase: GenerateQrCodeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GenerateQrUiState())
    val uiState: StateFlow<GenerateQrUiState> = _uiState.asStateFlow()

    fun updateSsid(value: String) {
        _uiState.value = _uiState.value.copy(ssid = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun updateSecurity(value: String) {
        _uiState.value = _uiState.value.copy(securityType = value)
    }

    fun toggleHidden(value: Boolean) {
        _uiState.value = _uiState.value.copy(isHidden = value)
    }

    fun generateQrCode() {
        val s = _uiState.value
        if (s.ssid.isBlank()) {
            _uiState.value = s.copy(errorMessage = "SSID is required")
            return
        }
        val config = QrCodeConfig(
            ssid = s.ssid,
            password = s.password,
            securityType = s.securityType,
            isHidden = s.isHidden
        )
        val res = generateQrCodeUseCase(config)
        res.onSuccess { bitmap ->
            _uiState.value = s.copy(qrBitmap = bitmap, errorMessage = null)
        }.onFailure { err ->
            _uiState.value = s.copy(errorMessage = err.message ?: "Failed to generate QR")
        }
    }
}