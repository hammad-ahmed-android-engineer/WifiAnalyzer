package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String = "OK",
    onConfirm: () -> Unit,
    dismissButtonText: String? = null,
    onDismiss: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { AppTypography.TitleLarge(text = title) },
        text = { AppTypography.BodyMedium(text = text) },
        confirmButton = {
            AppButton(
                text = confirmButtonText,
                onClick = onConfirm
            )
        },
        dismissButton = dismissButtonText?.let {
            {
                AppOutlinedButton(
                    text = it,
                    onClick = { onDismiss?.invoke() }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    )
}