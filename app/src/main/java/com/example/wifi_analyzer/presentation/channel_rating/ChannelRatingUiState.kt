package com.example.wifi_analyzer.presentation.channel_rating

import com.example.wifi_analyzer.domain.model.ChannelRating

data class ChannelRatingUiState(
    val ratings: List<ChannelRating> = emptyList(),
    val isLoading: Boolean = true
)