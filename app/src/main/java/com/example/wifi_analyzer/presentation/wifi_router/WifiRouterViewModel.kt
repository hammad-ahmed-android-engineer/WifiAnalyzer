package com.example.wifi_analyzer.presentation.wifi_router

import android.content.Context
import android.net.wifi.WifiManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.core.utils.toIpAddressString
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WifiRouterViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(WifiRouterUiState())
    val uiState: StateFlow<WifiRouterUiState> = _uiState.asStateFlow()

    init {
        loadGateway()
    }

    @Suppress("DEPRECATION")
    fun loadGateway() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
                val dhcp = wifiManager?.dhcpInfo
                val gatewayIp = dhcp?.gateway?.toIpAddressString() ?: "192.168.1.1"

                if (gatewayIp != "0.0.0.0") {
                    _uiState.value = WifiRouterUiState(gatewayIp = gatewayIp, isLoading = false)
                } else {
                    _uiState.value = WifiRouterUiState(gatewayIp = "192.168.1.1", isLoading = false)
                }
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}