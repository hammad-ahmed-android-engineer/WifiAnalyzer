package com.example.wifi_analyzer.presentation.all_wifis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.ScanWifiNetworksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllWifisViewModel @Inject constructor(
    private val scanWifiNetworksUseCase: ScanWifiNetworksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AllWifisUiState())
    val uiState: StateFlow<AllWifisUiState> = _uiState.asStateFlow()

    init {
        scanNetworks()
    }

    fun scanNetworks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isScanning = true)
            scanWifiNetworksUseCase().collect { list ->
                _uiState.value = AllWifisUiState(networks = list, isScanning = false)
            }
        }
    }
}