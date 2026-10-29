package com.example.wifi_analyzer.presentation.wifi_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppCircularProgressIndicator
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions

@Composable
fun WifiInfoScreen(
    viewModel: WifiInfoViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Wi-Fi Information",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                AppCircularProgressIndicator()
            }
        } else {
            val details = uiState.details
            val columns = if (isLandscape) GridCells.Fixed(2) else GridCells.Fixed(1)

            LazyVerticalGrid(
                columns = columns,
                contentPadding = PaddingValues(AppDimensions.paddingDefault),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingDefault),
                verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingDefault),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item { WifiInfoDetailCard("SSID", details?.ssid ?: "N/A") }
                item { WifiInfoDetailCard("BSSID (MAC)", details?.bssid ?: "N/A") }
                item { WifiInfoDetailCard("IP Address", details?.ipAddress ?: "N/A") }
                item { WifiInfoDetailCard("Link Speed", "${details?.linkSpeedMbps ?: 0} Mbps") }
                item { WifiInfoDetailCard("Frequency", "${details?.frequencyMhz ?: 0} MHz") }
                item { WifiInfoDetailCard("Signal Strength", "${details?.signalDbm ?: 0} dBm (${details?.signalPercentage ?: 0}%)") }
                item { WifiInfoDetailCard("Gateway IP", details?.gatewayIp ?: "N/A") }
                item { WifiInfoDetailCard("Netmask", details?.netmask ?: "N/A") }
                item { WifiInfoDetailCard("DNS 1", details?.dns1 ?: "N/A") }
                item { WifiInfoDetailCard("DNS 2", details?.dns2 ?: "N/A") }
                item { WifiInfoDetailCard("Security", details?.securityType ?: "N/A") }
            }
        }
    }
}