package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.utils.rememberDebouncedClick

@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val debouncedClick = rememberDebouncedClick {
        onCheckedChange(!checked)
    }
    Checkbox(
        checked = checked,
        onCheckedChange = { debouncedClick() },
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}