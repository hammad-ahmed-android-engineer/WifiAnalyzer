package com.example.wifi_analyzer.presentation.password_generator

data class PasswordGeneratorUiState(
    val generatedPassword: String = "",
    val passwordLength: Float = 12f,
    val includeUppercase: Boolean = true,
    val includeLowercase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSymbols: Boolean = true
)