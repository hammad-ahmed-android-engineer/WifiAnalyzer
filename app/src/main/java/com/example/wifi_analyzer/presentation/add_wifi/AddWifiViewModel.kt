package com.example.wifi_analyzer.presentation.add_wifi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.AddWifiNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWifiViewModel @Inject constructor(
    private val addWifiNetworkUseCase: AddWifiNetworkUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddWifiUiState())
    val uiState: StateFlow<AddWifiUiState> = _uiState.asStateFlow()

    fun updateSsid(value: String) {
        _uiState.value = _uiState.value.copy(ssid = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun updateSecurity(value: String) {
        _uiState.value = _uiState.value.copy(selectedSecurity = value)
    }

    fun toggleHidden(value: Boolean) {
        _uiState.value = _uiState.value.copy(isHidden = value)
    }

    fun addNetwork() {
        val state = _uiState.value
        if (state.ssid.isBlank()) {
            _uiState.value = state.copy(errorMessage = "SSID cannot be empty")
            return
        }
        viewModelScope.launch {
            val res = addWifiNetworkUseCase(state.ssid, state.password, state.selectedSecurity)
            if (res.isSuccess) {
                _uiState.value = state.copy(isSuccess = true, errorMessage = null)
            } else {
                _uiState.value = state.copy(errorMessage = "Failed to add Wi-Fi network")
            }
        }
    }
}