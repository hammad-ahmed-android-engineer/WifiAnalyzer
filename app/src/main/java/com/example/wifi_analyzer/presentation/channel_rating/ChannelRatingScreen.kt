package com.example.wifi_analyzer.presentation.channel_rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.core.designsystem.components.AppCircularProgressIndicator
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography

@Composable
fun ChannelRatingScreen(
    viewModel: ChannelRatingViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Channel Rating Analyzer",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AppCircularProgressIndicator()
                    Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))
                    AppTypography.BodyMedium(
                        text = "Analyzing Wi-Fi channels...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            val columns = if (isLandscape) GridCells.Fixed(2) else GridCells.Fixed(1)
            LazyVerticalGrid(
                columns = columns,
                contentPadding = PaddingValues(AppDimensions.paddingDefault),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall),
                verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(uiState.ratings) { item ->
                    ChannelRatingCardItem(item = item)
                }
            }
        }
    }
}