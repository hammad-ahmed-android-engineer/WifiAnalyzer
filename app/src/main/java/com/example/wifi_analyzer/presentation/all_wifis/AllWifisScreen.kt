package com.example.wifi_analyzer.presentation.all_wifis

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCircularProgressIndicator
import com.example.wifi_analyzer.core.designsystem.components.AppIconButton
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.hasPermission

@Composable
fun AllWifisScreen(
    viewModel: AllWifisViewModel,
    onBackClick: () -> Unit,
    onSelectNetwork: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {
        viewModel.scanNetworks()
    }

    fun requestPermissionsAndScan() {
        val fineGranted = context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseGranted = context.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fineGranted || coarseGranted) {
            viewModel.scanNetworks()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        requestPermissionsAndScan()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "All Nearby Wi-Fis",
                onBackClick = onBackClick,
                actions = {
                    AppIconButton(
                        icon = Icons.Default.Refresh,
                        contentDescription = "Scan Networks",
                        onClick = { requestPermissionsAndScan() }
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
                text = if (uiState.isScanning) "Scanning..." else "Scan Wi-Fi Networks",
                onClick = { requestPermissionsAndScan() },
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
                        Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))
                        AppTypography.BodyMedium(
                            text = "Scanning nearby Wi-Fi networks...",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else if (uiState.networks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AppTypography.BodyMedium(
                        text = "No Wi-Fi networks found. Tap Scan to retry.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = AppDimensions.paddingDefault),
                    verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.networks) { network ->
                        WifiNetworkCardItem(
                            network = network,
                            onClick = { onSelectNetwork(network.ssid) }
                        )
                    }
                }
            }
        }
    }
}