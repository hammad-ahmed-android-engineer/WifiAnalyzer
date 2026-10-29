package com.example.wifi_analyzer.presentation.generate_qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedTextField
import com.example.wifi_analyzer.core.designsystem.components.AppRadioButtonGroup
import com.example.wifi_analyzer.core.designsystem.components.AppSwitch
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun GenerateQrScreen(
    viewModel: GenerateQrViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Generate Wi-Fi QR Code",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(AppDimensions.paddingDefault)
        ) {
            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                    AppTypography.LabelMedium(text = "Network SSID")
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    AppOutlinedTextField(
                        value = uiState.ssid,
                        onValueChange = { viewModel.updateSsid(it) },
                        placeholder = "Wi-Fi Name (SSID)",
                        isError = uiState.errorMessage != null,
                        errorMessage = uiState.errorMessage
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

                    AppTypography.LabelMedium(text = "Security Type")
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    AppRadioButtonGroup(
                        options = listOf("WPA", "WEP", "Open"),
                        selectedOption = uiState.securityType,
                        onOptionSelected = { viewModel.updateSecurity(it) }
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

                    if (uiState.securityType != "Open") {
                        AppTypography.LabelMedium(text = "Password")
                        Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                        AppOutlinedTextField(
                            value = uiState.password,
                            onValueChange = { viewModel.updatePassword(it) },
                            placeholder = "Wi-Fi Password"
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTypography.BodyMedium(text = "Hidden Network")
                        AppSwitch(
                            checked = uiState.isHidden,
                            onCheckedChange = { viewModel.toggleHidden(it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

            AppButton(
                text = "Generate QR Code",
                onClick = { viewModel.generateQrCode() }
            )

            uiState.qrBitmap?.let { bitmap ->
                Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppDimensions.paddingDefault),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppTypography.TitleMedium(text = "Scan to Connect")
                        Spacer(modifier = Modifier.height(AppDimensions.paddingMedium))
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Wi-Fi QR Code",
                            modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 3)
                        )
                    }
                }
            }
        }
    }
}