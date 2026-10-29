package com.example.wifi_analyzer.domain.repository

import com.example.wifi_analyzer.domain.model.PasswordOptions

interface PasswordGeneratorRepository {
    fun generatePassword(options: PasswordOptions): String
}