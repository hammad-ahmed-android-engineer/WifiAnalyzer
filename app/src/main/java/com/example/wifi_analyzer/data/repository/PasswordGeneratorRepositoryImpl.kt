package com.example.wifi_analyzer.data.repository

import com.example.wifi_analyzer.domain.model.PasswordOptions
import com.example.wifi_analyzer.domain.repository.PasswordGeneratorRepository
import java.security.SecureRandom

class PasswordGeneratorRepositoryImpl : PasswordGeneratorRepository {

    override fun generatePassword(options: PasswordOptions): String {
        val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lowercase = "abcdefghijklmnopqrstuvwxyz"
        val numbers = "0123456789"
        val symbols = "!@#$%^&*()-=+{}[]<>,.?"

        val charPool = StringBuilder()
        if (options.includeUppercase) charPool.append(uppercase)
        if (options.includeLowercase) charPool.append(lowercase)
        if (options.includeNumbers) charPool.append(numbers)
        if (options.includeSymbols) charPool.append(symbols)

        if (charPool.isEmpty()) {
            charPool.append(lowercase)
        }

        val random = SecureRandom()
        val result = StringBuilder()
        val len = options.length.coerceIn(4, 64)

        for (i in 0 until len) {
            val randomIndex = random.nextInt(charPool.length)
            result.append(charPool[randomIndex])
        }

        return result.toString()
    }
}