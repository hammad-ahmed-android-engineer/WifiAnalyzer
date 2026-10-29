package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun AppConfirmDialog(
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    confirmText: String = "Yes",
    cancelText: String = "No",
    onConfirm: () -> Unit,
    onCancel: () -> Unit = onDismissRequest,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { AppTypography.TitleLarge(text = title) },
        text = { AppTypography.BodyMedium(text = message) },
        confirmButton = {
            Row {
                AppOutlinedButton(
                    text = cancelText,
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(AppDimensions.paddingSmall))
                AppButton(
                    text = confirmText,
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    )
}