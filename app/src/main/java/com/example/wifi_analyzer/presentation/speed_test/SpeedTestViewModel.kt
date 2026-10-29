package com.example.wifi_analyzer.presentation.speed_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.RunSpeedTestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeedTestViewModel @Inject constructor(
    private val runSpeedTestUseCase: RunSpeedTestUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpeedTestUiState())
    val uiState: StateFlow<SpeedTestUiState> = _uiState.asStateFlow()

    fun startTest() {
        viewModelScope.launch {
            runSpeedTestUseCase().collect { result ->
                _uiState.value = SpeedTestUiState(result = result)
            }
        }
    }
}