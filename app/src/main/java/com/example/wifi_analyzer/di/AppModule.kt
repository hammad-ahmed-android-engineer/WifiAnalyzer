package com.example.wifi_analyzer.di

import android.content.Context
import com.example.wifi_analyzer.data.local.MacVendorLocalDataSource
import com.example.wifi_analyzer.data.local.WifiLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWifiLocalDataSource(@ApplicationContext context: Context): WifiLocalDataSource {
        return WifiLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideMacVendorLocalDataSource(@ApplicationContext context: Context): MacVendorLocalDataSource {
        return MacVendorLocalDataSource(context)
    }
}