package com.example.wifi_analyzer.data.local

import android.content.Context
import android.net.DhcpInfo
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.example.wifi_analyzer.core.utils.frequencyToChannel
import com.example.wifi_analyzer.core.utils.toIpAddressString
import com.example.wifi_analyzer.core.utils.toSignalStrengthPercentage
import com.example.wifi_analyzer.domain.model.WifiDetails
import com.example.wifi_analyzer.domain.model.WifiNetwork

class WifiLocalDataSource(private val context: Context) {

    private val wifiManager: WifiManager?
        get() = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

    fun getConnectedWifiDetails(): WifiDetails {
        val manager = wifiManager
        val wInfo: WifiInfo? = manager?.connectionInfo
        val dhcp: DhcpInfo? = manager?.dhcpInfo

        val ssid = wInfo?.ssid?.replace("\"", "") ?: "Not Connected"
        val bssid = wInfo?.bssid ?: "00:00:00:00:00:00"
        val ipAddress = wInfo?.ipAddress?.toIpAddressString() ?: "0.0.0.0"
        val macAddress = wInfo?.macAddress ?: "Unavailable"
        val linkSpeed = wInfo?.linkSpeed ?: 0
        val frequency = wInfo?.frequency ?: 0
        val networkId = wInfo?.networkId ?: -1
        val signalDbm = wInfo?.rssi ?: -100
        val signalPercentage = signalDbm.toSignalStrengthPercentage()
        val gatewayIp = dhcp?.gateway?.toIpAddressString() ?: "0.0.0.0"
        val netmask = dhcp?.netmask?.toIpAddressString() ?: "255.255.255.0"
        val dns1 = dhcp?.dns1?.toIpAddressString() ?: "0.0.0.0"
        val dns2 = dhcp?.dns2?.toIpAddressString() ?: "0.0.0.0"

        return WifiDetails(
            ssid = ssid,
            bssid = bssid,
            ipAddress = ipAddress,
            macAddress = macAddress,
            linkSpeedMbps = linkSpeed,
            frequencyMhz = frequency,
            networkId = networkId,
            signalDbm = signalDbm,
            signalPercentage = signalPercentage,
            gatewayIp = gatewayIp,
            netmask = netmask,
            dns1 = dns1,
            dns2 = dns2,
            securityType = "WPA2/WPA3"
        )
    }

    @Suppress("DEPRECATION")
    fun getScanResults(): List<WifiNetwork> {
        val manager = wifiManager ?: return emptyList()
        val results = manager.scanResults ?: return emptyList()
        val connectedSsid = manager.connectionInfo?.ssid?.replace("\"", "") ?: ""

        return results.map { scanResult ->
            val cleanSsid = scanResult.SSID?.replace("\"", "") ?: "Hidden Network"
            val level = scanResult.level
            WifiNetwork(
                ssid = cleanSsid,
                bssid = scanResult.BSSID ?: "",
                levelDbm = level,
                signalPercentage = level.toSignalStrengthPercentage(),
                frequency = scanResult.frequency,
                channel = scanResult.frequency.frequencyToChannel(),
                capabilities = scanResult.capabilities ?: "",
                isConnected = cleanSsid.isNotEmpty() && cleanSsid == connectedSsid
            )
        }
    }

    @Suppress("DEPRECATION")
    fun addAndConnect(ssid: String, password: String, securityType: String): Boolean {
        val manager = wifiManager ?: return false
        val conf = WifiConfiguration().apply {
            SSID = "\"$ssid\""
            if (password.isNotEmpty()) {
                preSharedKey = "\"$password\""
            } else {
                allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
            }
        }
        val netId = manager.addNetwork(conf)
        if (netId != -1) {
            manager.disconnect()
            manager.enableNetwork(netId, true)
            manager.reconnect()
            return true
        }
        return false
    }
}