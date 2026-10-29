package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.rememberDebouncedClick

@Composable
fun AppOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val debouncedClick = rememberDebouncedClick(onClick = onClick)
    OutlinedButton(
        onClick = debouncedClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = AppDimensions.buttonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(AppDimensions.cornerMedium),
        border = BorderStroke(AppDimensions.borderStrokeThin, MaterialTheme.colorScheme.primary)
    ) {
        AppTypography.ButtonText(
            text = text,
            color = MaterialTheme.colorScheme.primary
        )
    }
}