package com.example.wifi_analyzer.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.GetConnectedWifiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getConnectedWifiUseCase: GetConnectedWifiUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadConnectedWifi()
    }

    fun loadConnectedWifi() {
        viewModelScope.launch {
            getConnectedWifiUseCase().collect { details ->
                _uiState.value = _uiState.value.copy(
                    connectedWifi = details,
                    isConnected = details.ssid.isNotEmpty() && details.ssid != "Not Connected"
                )
            }
        }
    }

    fun selectDrawerItem(id: String) {
        _uiState.value = _uiState.value.copy(selectedDrawerItem = id)
    }
}