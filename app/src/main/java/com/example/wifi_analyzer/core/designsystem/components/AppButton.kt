package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.rememberDebouncedClick

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
) {
    val debouncedClick = rememberDebouncedClick(onClick = onClick)
    Button(
        onClick = debouncedClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = AppDimensions.buttonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(AppDimensions.cornerMedium),
        colors = colors
    ) {
        AppTypography.ButtonText(text = text)
    }
}