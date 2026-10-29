package com.example.wifi_analyzer.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.wifi_analyzer.core.designsystem.dimensions.LocalDimensions
import com.example.wifi_analyzer.core.designsystem.dimensions.resolveDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimaryWhite,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = OnPrimaryWhite,
    secondary = SecondaryTeal,
    onSecondary = OnPrimaryWhite,
    background = DarkBackground,
    onBackground = OnSurfaceLight,
    surface = DarkSurface,
    onSurface = OnSurfaceLight,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = AccentRed
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimaryWhite,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = OnPrimaryWhite,
    secondary = SecondaryTeal,
    onSecondary = OnPrimaryWhite,
    background = LightBackground,
    onBackground = OnSurfaceDark,
    surface = LightSurface,
    onSurface = OnSurfaceDark,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = AccentRed
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val dimensions = resolveDimensions()
    val typography = AppTypography.material3Typography(dimensions.typeScale)
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    CompositionLocalProvider(
        LocalDimensions provides dimensions
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}