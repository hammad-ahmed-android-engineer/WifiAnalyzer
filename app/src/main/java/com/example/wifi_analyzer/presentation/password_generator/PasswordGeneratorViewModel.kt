package com.example.wifi_analyzer.presentation.password_generator

import androidx.lifecycle.ViewModel
import com.example.wifi_analyzer.domain.model.PasswordOptions
import com.example.wifi_analyzer.domain.usecases.GeneratePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PasswordGeneratorViewModel @Inject constructor(
    private val generatePasswordUseCase: GeneratePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordGeneratorUiState())
    val uiState: StateFlow<PasswordGeneratorUiState> = _uiState.asStateFlow()

    init {
        generate()
    }

    fun setLength(length: Float) {
        _uiState.value = _uiState.value.copy(passwordLength = length)
        generate()
    }

    fun toggleUppercase(value: Boolean) {
        _uiState.value = _uiState.value.copy(includeUppercase = value)
        generate()
    }

    fun toggleLowercase(value: Boolean) {
        _uiState.value = _uiState.value.copy(includeLowercase = value)
        generate()
    }

    fun toggleNumbers(value: Boolean) {
        _uiState.value = _uiState.value.copy(includeNumbers = value)
        generate()
    }

    fun toggleSymbols(value: Boolean) {
        _uiState.value = _uiState.value.copy(includeSymbols = value)
        generate()
    }

    fun generate() {
        val s = _uiState.value
        val options = PasswordOptions(
            length = s.passwordLength.toInt(),
            includeUppercase = s.includeUppercase,
            includeLowercase = s.includeLowercase,
            includeNumbers = s.includeNumbers,
            includeSymbols = s.includeSymbols
        )
        val pwd = generatePasswordUseCase(options)
        _uiState.value = _uiState.value.copy(generatedPassword = pwd)
    }
}