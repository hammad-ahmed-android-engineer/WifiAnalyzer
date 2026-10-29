package com.example.wifi_analyzer.presentation.speed_test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.formatMbps

@Composable
fun SpeedGaugeComponent(
    currentSpeedMbps: Double,
    label: String,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier = modifier.size(AppDimensions.components.emptyStateIconContainer * 3.5f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 3.5f)) {
            val sweepAngle = 360f
            val startAngle = -90f
            val maxSpeed = 100f
            val progressFraction = (currentSpeedMbps.toFloat() / maxSpeed).coerceIn(0f, 1f)

            // Outer gauge background track
            drawArc(
                color = trackColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 32f, cap = StrokeCap.Round)
            )

            // Active speed arc
            drawArc(
                color = primaryColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle * progressFraction,
                useCenter = false,
                style = Stroke(width = 32f, cap = StrokeCap.Round)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            AppTypography.DisplayMedium(
                text = currentSpeedMbps.formatMbps(),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(AppDimensions.paddingMicro))
            AppTypography.LabelLarge(
                text = label,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}