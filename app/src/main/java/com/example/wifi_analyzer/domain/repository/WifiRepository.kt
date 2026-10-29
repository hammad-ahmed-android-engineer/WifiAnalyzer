package com.example.wifi_analyzer.domain.repository

import com.example.wifi_analyzer.domain.model.WifiDetails
import com.example.wifi_analyzer.domain.model.WifiNetwork
import kotlinx.coroutines.flow.Flow

interface WifiRepository {
    fun getConnectedWifiDetails(): Flow<WifiDetails>
    fun scanWifiNetworks(): Flow<List<WifiNetwork>>
    suspend fun addAndConnectWifi(ssid: String, password: String, securityType: String): Result<Boolean>
}