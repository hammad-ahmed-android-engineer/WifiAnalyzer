package com.example.wifi_analyzer.domain.model

data class PasswordOptions(
    val length: Int = 12,
    val includeUppercase: Boolean = true,
    val includeLowercase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSymbols: Boolean = true
)