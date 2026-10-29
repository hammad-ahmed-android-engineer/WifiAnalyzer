package com.example.wifi_analyzer.presentation.wifi_info

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
class WifiInfoViewModel @Inject constructor(
    private val getConnectedWifiUseCase: GetConnectedWifiUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WifiInfoUiState())
    val uiState: StateFlow<WifiInfoUiState> = _uiState.asStateFlow()

    init {
        loadWifiInfo()
    }

    fun loadWifiInfo() {
        viewModelScope.launch {
            _uiState.value = WifiInfoUiState(isLoading = true)
            getConnectedWifiUseCase().collect { details ->
                _uiState.value = WifiInfoUiState(details = details, isLoading = false)
            }
        }
    }
}