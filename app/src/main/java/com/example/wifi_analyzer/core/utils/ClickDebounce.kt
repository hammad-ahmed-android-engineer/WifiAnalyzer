package com.example.wifi_analyzer.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

/**
 * Click handler that debounces multiple rapid clicks within [debounceIntervalMs] (default 500ms).
 */
@Composable
fun rememberDebouncedClick(
    debounceIntervalMs: Long = 500L,
    onClick: () -> Unit
): () -> Unit {
    val currentOnClick by rememberUpdatedState(onClick)
    var lastClickTime by remember { mutableStateOf(0L) }

    return remember(debounceIntervalMs) {
        {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= debounceIntervalMs) {
                lastClickTime = currentTime
                currentOnClick()
            }
        }
    }
}

/**
 * Modifier extension to apply clickable with debouncing (500ms default).
 */
fun Modifier.clickableDebounced(
    debounceIntervalMs: Long = 500L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    val debouncedClick = rememberDebouncedClick(debounceIntervalMs = debounceIntervalMs, onClick = onClick)
    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = debouncedClick
    )
}

/**
 * Modifier extension to apply clickable with debouncing and custom interaction source.
 */
fun Modifier.clickableDebounced(
    interactionSource: MutableInteractionSource,
    indication: androidx.compose.foundation.Indication?,
    debounceIntervalMs: Long = 500L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    val debouncedClick = rememberDebouncedClick(debounceIntervalMs = debounceIntervalMs, onClick = onClick)
    this.clickable(
        interactionSource = interactionSource,
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = debouncedClick
    )
}