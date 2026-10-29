package com.example.wifi_analyzer.presentation.signal_meter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.GetSignalMeterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignalMeterViewModel @Inject constructor(
    private val getSignalMeterUseCase: GetSignalMeterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignalMeterUiState())
    val uiState: StateFlow<SignalMeterUiState> = _uiState.asStateFlow()

    init {
        loadSignalMeter()
    }

    fun loadSignalMeter() {
        viewModelScope.launch {
            _uiState.value = SignalMeterUiState(isLoading = true)
            getSignalMeterUseCase().collect { meterData ->
                _uiState.value = SignalMeterUiState(data = meterData, isLoading = false)
            }
        }
    }
}