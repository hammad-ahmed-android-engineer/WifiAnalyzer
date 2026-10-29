package com.example.wifi_analyzer.di

import com.example.wifi_analyzer.domain.repository.DeviceScannerRepository
import com.example.wifi_analyzer.domain.repository.PasswordGeneratorRepository
import com.example.wifi_analyzer.domain.repository.QrCodeRepository
import com.example.wifi_analyzer.domain.repository.SpeedTestRepository
import com.example.wifi_analyzer.domain.repository.WifiRepository
import com.example.wifi_analyzer.domain.usecases.AddWifiNetworkUseCase
import com.example.wifi_analyzer.domain.usecases.GeneratePasswordUseCase
import com.example.wifi_analyzer.domain.usecases.GenerateQrCodeUseCase
import com.example.wifi_analyzer.domain.usecases.GetChannelRatingsUseCase
import com.example.wifi_analyzer.domain.usecases.GetConnectedWifiUseCase
import com.example.wifi_analyzer.domain.usecases.GetSignalMeterUseCase
import com.example.wifi_analyzer.domain.usecases.ParseQrCodeUseCase
import com.example.wifi_analyzer.domain.usecases.RunSpeedTestUseCase
import com.example.wifi_analyzer.domain.usecases.ScanConnectedDevicesUseCase
import com.example.wifi_analyzer.domain.usecases.ScanWifiNetworksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetConnectedWifiUseCase(repo: WifiRepository) = GetConnectedWifiUseCase(repo)

    @Provides
    @Singleton
    fun provideScanWifiNetworksUseCase(repo: WifiRepository) = ScanWifiNetworksUseCase(repo)

    @Provides
    @Singleton
    fun provideScanConnectedDevicesUseCase(repo: DeviceScannerRepository) = ScanConnectedDevicesUseCase(repo)

    @Provides
    @Singleton
    fun provideRunSpeedTestUseCase(repo: SpeedTestRepository) = RunSpeedTestUseCase(repo)

    @Provides
    @Singleton
    fun provideGeneratePasswordUseCase(repo: PasswordGeneratorRepository) = GeneratePasswordUseCase(repo)

    @Provides
    @Singleton
    fun provideAddWifiNetworkUseCase(repo: WifiRepository) = AddWifiNetworkUseCase(repo)

    @Provides
    @Singleton
    fun provideGenerateQrCodeUseCase(repo: QrCodeRepository) = GenerateQrCodeUseCase(repo)

    @Provides
    @Singleton
    fun provideParseQrCodeUseCase(repo: QrCodeRepository) = ParseQrCodeUseCase(repo)

    @Provides
    @Singleton
    fun provideGetChannelRatingsUseCase(repo: WifiRepository) = GetChannelRatingsUseCase(repo)

    @Provides
    @Singleton
    fun provideGetSignalMeterUseCase(repo: WifiRepository) = GetSignalMeterUseCase(repo)
}