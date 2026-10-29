package com.example.wifi_analyzer.data.local

import android.content.Context
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class MacVendorLocalDataSource(private val context: Context) {

    private var jsonArray: JSONArray? = null

    @Synchronized
    private fun getVendorJsonArray(): JSONArray? {
        if (jsonArray != null) return jsonArray
        try {
            val inputStream = context.assets.open("vendors.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            reader.close()
            inputStream.close()
            jsonArray = JSONArray(sb.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonArray
    }

    fun getVendorName(macAddress: String): String {
        val array = getVendorJsonArray() ?: return "Unknown"
        val cleanMac = macAddress.lowercase()
        try {
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val prefix = obj.getString("m").lowercase()
                val vendor = obj.getString("n")
                if (cleanMac.startsWith(prefix)) {
                    return vendor
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Unknown"
    }
}