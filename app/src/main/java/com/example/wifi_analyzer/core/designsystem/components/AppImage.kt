package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AppImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AppImage(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        bitmap = bitmap,
        contentDescription = contentDescription,
        modifier = modifier
    )
}