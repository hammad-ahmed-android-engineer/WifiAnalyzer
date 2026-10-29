package com.example.wifi_analyzer.domain.model

data class ChannelRating(
    val channelNumber: Int,
    val bandFrequency: String,
    val stars: Int,
    val networkCount: Int,
    val recommendation: String
)