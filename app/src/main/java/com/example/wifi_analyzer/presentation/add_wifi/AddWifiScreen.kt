package com.example.wifi_analyzer.presentation.add_wifi

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedTextField
import com.example.wifi_analyzer.core.designsystem.components.AppRadioButtonGroup
import com.example.wifi_analyzer.core.designsystem.components.AppSwitch
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.showToast

@Composable
fun AddWifiScreen(
    viewModel: AddWifiViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            context.showToast("Wi-Fi network added successfully!")
            onBackClick()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Add Wi-Fi Network",
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
                        placeholder = "Enter Network Name (SSID)",
                        isError = uiState.errorMessage != null,
                        errorMessage = uiState.errorMessage
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

                    AppTypography.LabelMedium(text = "Security Type")
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    AppRadioButtonGroup(
                        options = listOf("Open", "WEP", "WPA2", "WPA3"),
                        selectedOption = uiState.selectedSecurity,
                        onOptionSelected = { viewModel.updateSecurity(it) }
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

                    if (uiState.selectedSecurity != "Open") {
                        AppTypography.LabelMedium(text = "Password")
                        Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                        AppOutlinedTextField(
                            value = uiState.password,
                            onValueChange = { viewModel.updatePassword(it) },
                            placeholder = "Enter Network Password"
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
                text = "Connect & Save Wi-Fi",
                onClick = { viewModel.addNetwork() }
            )
        }
    }
}