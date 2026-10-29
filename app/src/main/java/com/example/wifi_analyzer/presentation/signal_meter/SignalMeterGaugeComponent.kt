package com.example.wifi_analyzer.presentation.signal_meter

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun SignalMeterGaugeComponent(
    signalDbm: Int,
    percentage: Int,
    qualityLabel: String,
    modifier: Modifier = Modifier
) {
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    val gaugeColor = when {
        percentage >= 80 -> Color(0xFF00E5FF)
        percentage >= 60 -> Color(0xFF00E676)
        percentage >= 40 -> Color(0xFFFF9100)
        else -> Color(0xFFFF1744)
    }

    Box(
        modifier = modifier.size(AppDimensions.components.emptyStateIconContainer * 3.5f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 3.5f)) {
            val sweepAngle = 360f
            val startAngle = -90f
            val progressFraction = (percentage / 100f).coerceIn(0f, 1f)

            // Outer track
            drawArc(
                color = trackColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 32f, cap = StrokeCap.Round)
            )

            // Active Signal Arc
            drawArc(
                color = gaugeColor,
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
                text = "$signalDbm dBm",
                color = gaugeColor
            )
            Spacer(modifier = Modifier.height(AppDimensions.paddingMicro))
            AppTypography.LabelLarge(
                text = qualityLabel,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}