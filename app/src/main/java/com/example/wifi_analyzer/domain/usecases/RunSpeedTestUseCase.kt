package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.SpeedTestResult
import com.example.wifi_analyzer.domain.repository.SpeedTestRepository
import kotlinx.coroutines.flow.Flow

class RunSpeedTestUseCase(
    private val speedTestRepository: SpeedTestRepository
) {
    operator fun invoke(): Flow<SpeedTestResult> {
        return speedTestRepository.runSpeedTest()
    }
}