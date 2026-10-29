package com.example.wifi_analyzer.presentation.connected_devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.ScanConnectedDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectedDevicesViewModel @Inject constructor(
    private val scanConnectedDevicesUseCase: ScanConnectedDevicesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConnectedDevicesUiState())
    val uiState: StateFlow<ConnectedDevicesUiState> = _uiState.asStateFlow()

    init {
        startScan()
    }

    fun startScan() {
        viewModelScope.launch {
            _uiState.value = ConnectedDevicesUiState(isScanning = true)
            scanConnectedDevicesUseCase().collect { list ->
                _uiState.value = ConnectedDevicesUiState(devices = list, isScanning = false)
            }
        }
    }
}