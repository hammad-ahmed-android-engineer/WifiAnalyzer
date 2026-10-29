package com.example.wifi_analyzer.domain.usecases

import com.example.wifi_analyzer.domain.model.ChannelRating
import com.example.wifi_analyzer.domain.repository.WifiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetChannelRatingsUseCase(
    private val wifiRepository: WifiRepository
) {
    operator fun invoke(): Flow<List<ChannelRating>> {
        return wifiRepository.scanWifiNetworks().map { networks ->
            val channelMap = networks.groupBy { it.channel }
            val standard24GChannels = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
            standard24GChannels.map { channel ->
                val count = channelMap[channel]?.size ?: 0
                val stars = when (count) {
                    0 -> 5
                    1 -> 4
                    2 -> 3
                    in 3..4 -> 2
                    else -> 1
                }
                val rec = when (stars) {
                    5 -> "Best Choice (No Interference)"
                    4 -> "Recommended"
                    3 -> "Fair"
                    else -> "Congested ($count networks)"
                }
                ChannelRating(
                    channelNumber = channel,
                    bandFrequency = "2.4 GHz",
                    stars = stars,
                    networkCount = count,
                    recommendation = rec
                )
            }
        }
    }
}