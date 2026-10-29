package com.example.wifi_analyzer.data.repository

import com.example.wifi_analyzer.data.local.WifiLocalDataSource
import com.example.wifi_analyzer.domain.model.WifiDetails
import com.example.wifi_analyzer.domain.model.WifiNetwork
import com.example.wifi_analyzer.domain.repository.WifiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WifiRepositoryImpl(
    private val localDataSource: WifiLocalDataSource
) : WifiRepository {

    override fun getConnectedWifiDetails(): Flow<WifiDetails> = flow {
        emit(localDataSource.getConnectedWifiDetails())
    }

    override fun scanWifiNetworks(): Flow<List<WifiNetwork>> = flow {
        emit(localDataSource.getScanResults())
    }

    override suspend fun addAndConnectWifi(
        ssid: String,
        password: String,
        securityType: String
    ): Result<Boolean> {
        return try {
            val success = localDataSource.addAndConnect(ssid, password, securityType)
            Result.success(success)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}