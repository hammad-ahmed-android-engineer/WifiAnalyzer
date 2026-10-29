package com.example.wifi_analyzer.di

import android.content.Context
import com.example.wifi_analyzer.data.local.MacVendorLocalDataSource
import com.example.wifi_analyzer.data.local.WifiLocalDataSource
import com.example.wifi_analyzer.data.repository.DeviceScannerRepositoryImpl
import com.example.wifi_analyzer.data.repository.PasswordGeneratorRepositoryImpl
import com.example.wifi_analyzer.data.repository.QrCodeRepositoryImpl
import com.example.wifi_analyzer.data.repository.SpeedTestRepositoryImpl
import com.example.wifi_analyzer.data.repository.WifiRepositoryImpl
import com.example.wifi_analyzer.domain.repository.DeviceScannerRepository
import com.example.wifi_analyzer.domain.repository.PasswordGeneratorRepository
import com.example.wifi_analyzer.domain.repository.QrCodeRepository
import com.example.wifi_analyzer.domain.repository.SpeedTestRepository
import com.example.wifi_analyzer.domain.repository.WifiRepository
import com.example.wifi_analyzer.domain.usecases.AddWifiNetworkUseCase
import com.example.wifi_analyzer.domain.usecases.GeneratePasswordUseCase
import com.example.wifi_analyzer.domain.usecases.GenerateQrCodeUseCase
import com.example.wifi_analyzer.domain.usecases.GetConnectedWifiUseCase
import com.example.wifi_analyzer.domain.usecases.ParseQrCodeUseCase
import com.example.wifi_analyzer.domain.usecases.RunSpeedTestUseCase
import com.example.wifi_analyzer.domain.usecases.ScanConnectedDevicesUseCase
import com.example.wifi_analyzer.domain.usecases.ScanWifiNetworksUseCase

class AppContainer(private val context: Context) {

    private val wifiLocalDataSource by lazy { WifiLocalDataSource(context) }
    private val macVendorLocalDataSource by lazy { MacVendorLocalDataSource(context) }

    val wifiRepository: WifiRepository by lazy { WifiRepositoryImpl(wifiLocalDataSource) }
    val deviceScannerRepository: DeviceScannerRepository by lazy { DeviceScannerRepositoryImpl(context, macVendorLocalDataSource) }
    val speedTestRepository: SpeedTestRepository by lazy { SpeedTestRepositoryImpl() }
    val passwordGeneratorRepository: PasswordGeneratorRepository by lazy { PasswordGeneratorRepositoryImpl() }
    val qrCodeRepository: QrCodeRepository by lazy { QrCodeRepositoryImpl() }

    val getConnectedWifiUseCase by lazy { GetConnectedWifiUseCase(wifiRepository) }
    val scanWifiNetworksUseCase by lazy { ScanWifiNetworksUseCase(wifiRepository) }
    val scanConnectedDevicesUseCase by lazy { ScanConnectedDevicesUseCase(deviceScannerRepository) }
    val runSpeedTestUseCase by lazy { RunSpeedTestUseCase(speedTestRepository) }
    val generatePasswordUseCase by lazy { GeneratePasswordUseCase(passwordGeneratorRepository) }
    val addWifiNetworkUseCase by lazy { AddWifiNetworkUseCase(wifiRepository) }
    val generateQrCodeUseCase by lazy { GenerateQrCodeUseCase(qrCodeRepository) }
    val parseQrCodeUseCase by lazy { ParseQrCodeUseCase(qrCodeRepository) }
}