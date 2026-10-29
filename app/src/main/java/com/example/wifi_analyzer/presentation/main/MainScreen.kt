package com.example.wifi_analyzer.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.R
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppIcon
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToWifiInfo: () -> Unit,
    onNavigateToAllWifis: () -> Unit,
    onNavigateToConnectedDevices: () -> Unit,
    onNavigateToSpeedTest: () -> Unit,
    onNavigateToPasswordGenerator: () -> Unit,
    onNavigateToWifiRouter: () -> Unit,
    onNavigateToAddWifi: () -> Unit,
    onNavigateToGenerateQr: () -> Unit,
    onNavigateToQrScanner: () -> Unit,
    onNavigateToChannelRating: () -> Unit,
    onNavigateToSignalMeter: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.app_name)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppDimensions.paddingDefault)
        ) {
            // Connected Wi-Fi Header Banner Card
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppDimensions.paddingSmall)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.paddingDefault),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppIcon(
                        imageVector = Icons.Default.Wifi,
                        contentDescription = stringResource(id = R.string.connected),
                        modifier = Modifier.size(AppDimensions.icons.extraLarge),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(AppDimensions.paddingMedium))
                    Column {
                        AppTypography.TitleLarge(
                            text = uiState.connectedWifi?.ssid ?: stringResource(id = R.string.not_connected),
                            color = MaterialTheme.colorScheme.primary
                        )
                        AppTypography.BodyMedium(
                            text = if (uiState.isConnected) "${stringResource(id = R.string.connected)} • Signal: ${uiState.connectedWifi?.signalPercentage ?: 0}%" else stringResource(id = R.string.disconnected),
                            color = if (uiState.isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))

            // Dashboard Grid Items
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = AppDimensions.components.emptyStateIconContainer * 2),
                contentPadding = PaddingValues(bottom = AppDimensions.paddingDefault),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium),
                verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_wifi_info),
                        icon = Icons.Default.Info,
                        onClick = onNavigateToWifiInfo
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_signal_meter),
                        icon = Icons.Default.SignalCellularAlt,
                        onClick = onNavigateToSignalMeter
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_channel_rating),
                        icon = Icons.Default.Assessment,
                        onClick = onNavigateToChannelRating
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_all_wifis),
                        icon = Icons.Default.Wifi,
                        onClick = onNavigateToAllWifis
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_speed_test),
                        icon = Icons.Default.Speed,
                        onClick = onNavigateToSpeedTest
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_connected_devices),
                        icon = Icons.Default.WifiTethering,
                        onClick = onNavigateToConnectedDevices
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_password_generator),
                        icon = Icons.Default.Lock,
                        onClick = onNavigateToPasswordGenerator
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_qr_scanner),
                        icon = Icons.Default.QrCodeScanner,
                        onClick = onNavigateToQrScanner
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_add_wifi),
                        icon = Icons.Default.AddCircle,
                        onClick = onNavigateToAddWifi
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_generate_qr),
                        icon = Icons.Default.QrCode,
                        onClick = onNavigateToGenerateQr
                    )
                }
                item {
                    MainDashboardCard(
                        title = stringResource(id = R.string.title_wifi_router),
                        icon = Icons.Default.Router,
                        onClick = onNavigateToWifiRouter
                    )
                }
            }
        }
    }
}