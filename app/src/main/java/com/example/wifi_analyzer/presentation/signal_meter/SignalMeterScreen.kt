package com.example.wifi_analyzer.presentation.signal_meter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppCircularProgressIndicator
import com.example.wifi_analyzer.core.designsystem.components.AppIconButton
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun SignalMeterScreen(
    viewModel: SignalMeterViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Signal Strength Meter",
                onBackClick = onBackClick,
                actions = {
                    AppIconButton(
                        icon = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        onClick = { viewModel.loadSignalMeter() }
                    )
                }
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
            val d = uiState.data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(AppDimensions.paddingDefault),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTypography.HeadlineSmall(
                    text = d?.ssid ?: "Connected Wi-Fi",
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

                SignalMeterGaugeComponent(
                    signalDbm = d?.rssiDbm ?: -100,
                    percentage = d?.percentage ?: 0,
                    qualityLabel = d?.qualityLabel ?: "Unknown"
                )

                Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

                if (isLandscape) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium)
                    ) {
                        AppCard(modifier = Modifier.weight(1f)) {
                            Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                                AppTypography.LabelMedium(text = "Signal Percentage")
                                AppTypography.TitleLarge(text = "${d?.percentage ?: 0}%")
                            }
                        }
                        AppCard(modifier = Modifier.weight(1f)) {
                            Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                                AppTypography.LabelMedium(text = "Link Speed")
                                AppTypography.TitleLarge(text = "${d?.linkSpeedMbps ?: 0} Mbps")
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium)
                    ) {
                        AppCard(modifier = Modifier.weight(1f)) {
                            Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                                AppTypography.LabelMedium(text = "Signal Percentage")
                                AppTypography.TitleLarge(text = "${d?.percentage ?: 0}%")
                            }
                        }
                        AppCard(modifier = Modifier.weight(1f)) {
                            Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                                AppTypography.LabelMedium(text = "Link Speed")
                                AppTypography.TitleLarge(text = "${d?.linkSpeedMbps ?: 0} Mbps")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

                AppButton(
                    text = "Refresh Signal Data",
                    onClick = { viewModel.loadSignalMeter() }
                )
            }
        }
    }
}