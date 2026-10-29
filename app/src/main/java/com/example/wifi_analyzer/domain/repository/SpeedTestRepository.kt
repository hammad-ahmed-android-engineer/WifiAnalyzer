package com.example.wifi_analyzer.domain.repository

import com.example.wifi_analyzer.domain.model.SpeedTestResult
import kotlinx.coroutines.flow.Flow

interface SpeedTestRepository {
    fun runSpeedTest(): Flow<SpeedTestResult>
}