package com.example.wifi_analyzer.presentation.speed_test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.formatMbps
import com.example.wifi_analyzer.core.utils.formatPingMs

@Composable
fun SpeedTestScreen(
    viewModel: SpeedTestViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val res = uiState.result
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Speed Test",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(AppDimensions.paddingDefault),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppTypography.LabelLarge(
                text = if (res.statusText.isEmpty()) "Tap Begin Test to measure your connection speed" else res.statusText,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

            val currentSpeed = if (res.uploadSpeedMbps > 0) res.uploadSpeedMbps else res.downloadSpeedMbps
            val speedLabel = if (res.uploadSpeedMbps > 0) "Upload Speed" else "Download Speed"

            SpeedGaugeComponent(
                currentSpeedMbps = currentSpeed,
                label = speedLabel
            )

            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

            if (isLandscape) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium)
                ) {
                    AppCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Ping")
                            AppTypography.TitleLarge(text = res.pingMs.formatPingMs(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    AppCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Download")
                            AppTypography.TitleLarge(text = res.downloadSpeedMbps.formatMbps(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    AppCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Upload")
                            AppTypography.TitleLarge(text = res.uploadSpeedMbps.formatMbps(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AppCard(modifier = Modifier.weight(1f).padding(end = AppDimensions.paddingSmall)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Ping")
                            AppTypography.TitleLarge(text = res.pingMs.formatPingMs(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    AppCard(modifier = Modifier.weight(1f).padding(horizontal = AppDimensions.paddingSmall)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Download")
                            AppTypography.TitleLarge(text = res.downloadSpeedMbps.formatMbps(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    AppCard(modifier = Modifier.weight(1f).padding(start = AppDimensions.paddingSmall)) {
                        Column(
                            modifier = Modifier.padding(AppDimensions.paddingDefault),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.LabelMedium(text = "Upload")
                            AppTypography.TitleLarge(text = res.uploadSpeedMbps.formatMbps(), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

            AppButton(
                text = if (res.isTesting) "Testing..." else if (res.isFinished) "Restart Test" else "Begin Test",
                onClick = { viewModel.startTest() },
                enabled = !res.isTesting,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}