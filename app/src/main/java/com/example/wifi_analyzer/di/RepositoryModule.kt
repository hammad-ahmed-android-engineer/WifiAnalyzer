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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWifiRepository(
        localDataSource: WifiLocalDataSource
    ): WifiRepository {
        return WifiRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideDeviceScannerRepository(
        @ApplicationContext context: Context,
        macVendorLocalDataSource: MacVendorLocalDataSource
    ): DeviceScannerRepository {
        return DeviceScannerRepositoryImpl(context, macVendorLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideSpeedTestRepository(): SpeedTestRepository {
        return SpeedTestRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePasswordGeneratorRepository(): PasswordGeneratorRepository {
        return PasswordGeneratorRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideQrCodeRepository(): QrCodeRepository {
        return QrCodeRepositoryImpl()
    }
}