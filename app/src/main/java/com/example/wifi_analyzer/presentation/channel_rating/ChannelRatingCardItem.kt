package com.example.wifi_analyzer.presentation.channel_rating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.domain.model.ChannelRating

@Composable
fun ChannelRatingCardItem(
    item: ChannelRating,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimensions.paddingDefault),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                AppTypography.TitleMedium(
                    text = "Channel ${item.channelNumber} (${item.bandFrequency})",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(AppDimensions.paddingMicro))
                AppTypography.BodySmall(
                    text = "Recommendation: ${item.recommendation}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(AppDimensions.paddingDefault))
            AppTypography.TitleLarge(
                text = "★".repeat(item.stars),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}