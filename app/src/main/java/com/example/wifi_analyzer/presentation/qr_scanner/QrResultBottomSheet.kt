package com.example.wifi_analyzer.presentation.qr_scanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.components.AppBottomSheet
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedButton
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.domain.model.QrCodeConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrResultBottomSheet(
    config: QrCodeConfig,
    onDismiss: () -> Unit,
    onConnect: () -> Unit,
    onCopyPassword: () -> Unit,
    isConnecting: Boolean,
    modifier: Modifier = Modifier
) {
    AppBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AppTypography.HeadlineSmall(
                text = "Wi-Fi Network Found",
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))
            AppTypography.TitleMedium(text = "SSID: ${config.ssid}")
            AppTypography.BodyMedium(text = "Security: ${config.securityType}")
            AppTypography.BodyMedium(text = "Password: ${if (config.password.isEmpty()) "(Open)" else "••••••••"}")

            Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

            AppButton(
                text = if (isConnecting) "Connecting..." else "Connect to Network",
                onClick = onConnect,
                enabled = !isConnecting
            )
            Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
            if (config.password.isNotEmpty()) {
                AppOutlinedButton(
                    text = "Copy Password",
                    onClick = onCopyPassword
                )
            }
        }
    }
}