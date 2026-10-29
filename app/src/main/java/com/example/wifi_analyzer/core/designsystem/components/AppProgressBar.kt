package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions

@Composable
fun AppProgressBar(
    progress: Float? = null,
    modifier: Modifier = Modifier
) {
    val barModifier = modifier
        .fillMaxWidth()
        .height(AppDimensions.paddingSmall)
        .clip(RoundedCornerShape(AppDimensions.cornerSmall))

    if (progress != null) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = barModifier,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    } else {
        LinearProgressIndicator(
            modifier = barModifier,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}