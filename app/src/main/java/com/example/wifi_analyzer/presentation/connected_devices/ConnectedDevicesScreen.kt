package com.example.wifi_analyzer.presentation.connected_devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCircularProgressIndicator
import com.example.wifi_analyzer.core.designsystem.components.AppIconButton
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun ConnectedDevicesScreen(
    viewModel: ConnectedDevicesViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Devices On My Network",
                onBackClick = onBackClick,
                actions = {
                    AppIconButton(
                        icon = Icons.Default.Refresh,
                        contentDescription = "Rescan",
                        onClick = { viewModel.startScan() }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppDimensions.paddingDefault)
        ) {
            AppButton(
                text = if (uiState.isScanning) "Scanning Subnet..." else "Scan Connected Devices",
                onClick = { viewModel.startScan() },
                enabled = !uiState.isScanning,
                modifier = Modifier.padding(vertical = AppDimensions.paddingSmall)
            )

            if (uiState.isScanning) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AppCircularProgressIndicator()
                        AppTypography.BodyMedium(
                            text = "Pinging network devices...",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = AppDimensions.paddingMedium)
                        )
                    }
                }
            } else if (uiState.devices.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AppTypography.BodyMedium(
                        text = "No active devices found. Tap Scan to scan local network.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = AppDimensions.paddingDefault),
                    verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.devices) { device ->
                        ConnectedDeviceItemCard(device = device)
                    }
                }
            }
        }
    }
}