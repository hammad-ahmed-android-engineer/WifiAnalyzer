package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.PasswordOptions
import com.example.wifi_analyzer.domain.repository.PasswordGeneratorRepository

class GeneratePasswordUseCase(
    private val repository: PasswordGeneratorRepository
) {
    operator fun invoke(options: PasswordOptions): String {
        return repository.generatePassword(options)
    }
}