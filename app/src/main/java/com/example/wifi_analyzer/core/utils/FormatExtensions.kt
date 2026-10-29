package com.example.wifi_analyzer.core.utils

import java.net.InetAddress
import java.util.Locale

fun Int.toIpAddressString(): String {
    return String.format(
        Locale.US,
        "%d.%d.%d.%d",
        this and 0xff,
        this shr 8 and 0xff,
        this shr 16 and 0xff,
        this shr 24 and 0xff
    )
}

fun Long.toIpAddressString(): String {
    return this.toInt().toIpAddressString()
}

fun Int.toSignalStrengthPercentage(): Int {
    return when {
        this <= -100 -> 0
        this >= -50 -> 100
        else -> 2 * (this + 100)
    }
}

fun Int.frequencyToChannel(): Int {
    return when (this) {
        in 2412..2484 -> (this - 2412) / 5 + 1
        in 5170..5825 -> (this - 5170) / 5 + 34
        else -> 0
    }
}

fun Double.formatMbps(): String {
    return String.format(Locale.US, "%.2f Mbps", this)
}

fun Double.formatPingMs(): String {
    return String.format(Locale.US, "%.0f ms", this)
}