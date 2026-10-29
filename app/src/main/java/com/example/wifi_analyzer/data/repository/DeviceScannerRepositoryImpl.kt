package com.example.wifi_analyzer.data.repository

import android.content.Context
import com.example.wifi_analyzer.data.local.MacVendorLocalDataSource
import com.example.wifi_analyzer.domain.model.ConnectedDevice
import com.example.wifi_analyzer.domain.repository.DeviceScannerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.InetAddress
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DeviceScannerRepositoryImpl(
    private val context: Context,
    private val vendorDataSource: MacVendorLocalDataSource
) : DeviceScannerRepository {

    override fun scanConnectedDevices(): Flow<List<ConnectedDevice>> = flow {
        val devices = mutableListOf<ConnectedDevice>()
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? android.net.wifi.WifiManager
        val dhcpInfo = wifiManager?.dhcpInfo
        val gatewayInt = dhcpInfo?.gateway ?: 0

        if (gatewayInt != 0) {
            val gatewayIp = String.format(
                "%d.%d.%d.%d",
                gatewayInt and 0xff,
                gatewayInt shr 8 and 0xff,
                gatewayInt shr 16 and 0xff,
                gatewayInt shr 24 and 0xff
            )
            val prefix = gatewayIp.substringBeforeLast(".") + "."

            val executor = Executors.newFixedThreadPool(32)
            for (i in 1..254) {
                val testIp = "$prefix$i"
                executor.execute {
                    try {
                        val inet = InetAddress.getByName(testIp)
                        if (inet.isReachable(300)) {
                            val hostName = inet.hostName.ifEmpty { "Device $i" }
                            val isCurrent = testIp == gatewayIp
                            val vendor = vendorDataSource.getVendorName("00:00:00")
                            synchronized(devices) {
                                devices.add(
                                    ConnectedDevice(
                                        name = hostName,
                                        ipAddress = testIp,
                                        macAddress = "02:00:00:00:00:00",
                                        vendorName = if (isCurrent) "Router Gateway" else vendor,
                                        isCurrentDevice = isCurrent
                                    )
                                )
                            }
                        }
                    } catch (_: Exception) {}
                }
            }
            executor.shutdown()
            executor.awaitTermination(10, TimeUnit.SECONDS)
        }

        emit(devices.toList())
    }.flowOn(Dispatchers.IO)
}