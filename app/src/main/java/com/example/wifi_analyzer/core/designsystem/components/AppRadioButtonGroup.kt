package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.clickableDebounced

@Composable
fun AppRadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickableDebounced { onOptionSelected(option) }
                    .padding(vertical = AppDimensions.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppRadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
                AppTypography.BodyMedium(
                    text = option,
                    modifier = Modifier.padding(start = AppDimensions.paddingSmall)
                )
            }
        }
    }
}