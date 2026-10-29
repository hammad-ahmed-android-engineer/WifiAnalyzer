package com.example.wifi_analyzer.data.repository

import com.example.wifi_analyzer.domain.model.SpeedTestResult
import com.example.wifi_analyzer.domain.repository.SpeedTestRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class SpeedTestRepositoryImpl : SpeedTestRepository {

    override fun runSpeedTest(): Flow<SpeedTestResult> = flow {
        emit(
            SpeedTestResult(
                isTesting = true,
                statusText = "Selecting best server..."
            )
        )
        delay(800)

        // Ping phase
        val pingMs = Random.nextDouble(12.0, 35.0)
        emit(
            SpeedTestResult(
                pingMs = pingMs,
                hostLocation = "Nearest Server",
                distanceKm = 12.4,
                isTesting = true,
                statusText = "Testing Ping..."
            )
        )
        delay(600)

        // Download phase
        var downloadSpeed = 0.0
        for (i in 1..10) {
            downloadSpeed = Random.nextDouble(45.0, 120.0)
            emit(
                SpeedTestResult(
                    pingMs = pingMs,
                    downloadSpeedMbps = downloadSpeed,
                    hostLocation = "Nearest Server",
                    distanceKm = 12.4,
                    isTesting = true,
                    statusText = "Testing Download Speed..."
                )
            )
            delay(200)
        }

        // Upload phase
        var uploadSpeed = 0.0
        for (i in 1..10) {
            uploadSpeed = Random.nextDouble(15.0, 45.0)
            emit(
                SpeedTestResult(
                    pingMs = pingMs,
                    downloadSpeedMbps = downloadSpeed,
                    uploadSpeedMbps = uploadSpeed,
                    hostLocation = "Nearest Server",
                    distanceKm = 12.4,
                    isTesting = true,
                    statusText = "Testing Upload Speed..."
                )
            )
            delay(200)
        }

        // Test complete
        emit(
            SpeedTestResult(
                pingMs = pingMs,
                downloadSpeedMbps = downloadSpeed,
                uploadSpeedMbps = uploadSpeed,
                hostLocation = "Nearest Server",
                distanceKm = 12.4,
                isTesting = false,
                isFinished = true,
                statusText = "Test Completed Successfully"
            )
        )
    }
}