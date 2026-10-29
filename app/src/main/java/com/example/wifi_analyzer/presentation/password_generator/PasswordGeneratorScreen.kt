package com.example.wifi_analyzer.presentation.password_generator

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedButton
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedTextField
import com.example.wifi_analyzer.core.designsystem.components.AppSlider
import com.example.wifi_analyzer.core.designsystem.components.AppSwitch
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.shareText
import com.example.wifi_analyzer.core.utils.showToast

@Composable
fun PasswordGeneratorScreen(
    viewModel: PasswordGeneratorViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Password Generator",
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
                    AppTypography.LabelMedium(text = "Generated Secure Password")
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    AppOutlinedTextField(
                        value = uiState.generatedPassword,
                        onValueChange = {},
                        singleLine = true,
                        enabled = false
                    )
                    Spacer(modifier = Modifier.height(AppDimensions.paddingMedium))
                    Row(horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingMedium)) {
                        AppOutlinedButton(
                            text = "Copy",
                            onClick = {
                                val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                                val clip = android.content.ClipData.newPlainText("Password", uiState.generatedPassword)
                                clipboard.setPrimaryClip(clip)
                                context.showToast("Copied to clipboard!")
                            },
                            modifier = Modifier.weight(1f)
                        )
                        AppOutlinedButton(
                            text = "Share",
                            onClick = { context.shareText("Wi-Fi Password", uiState.generatedPassword) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                    AppTypography.TitleMedium(text = "Length: ${uiState.passwordLength.toInt()} characters")
                    AppSlider(
                        value = uiState.passwordLength,
                        onValueChange = { viewModel.setLength(it) },
                        valueRange = 6f..32f,
                        steps = 25
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTypography.BodyMedium(text = "Uppercase Letters (A-Z)")
                        AppSwitch(
                            checked = uiState.includeUppercase,
                            onCheckedChange = { viewModel.toggleUppercase(it) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTypography.BodyMedium(text = "Lowercase Letters (a-z)")
                        AppSwitch(
                            checked = uiState.includeLowercase,
                            onCheckedChange = { viewModel.toggleLowercase(it) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTypography.BodyMedium(text = "Numbers (0-9)")
                        AppSwitch(
                            checked = uiState.includeNumbers,
                            onCheckedChange = { viewModel.toggleNumbers(it) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppTypography.BodyMedium(text = "Symbols (!@#$%^&*)")
                        AppSwitch(
                            checked = uiState.includeSymbols,
                            onCheckedChange = { viewModel.toggleSymbols(it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingLarge))

            AppButton(
                text = "Generate New Password",
                onClick = { viewModel.generate() }
            )
        }
    }
}