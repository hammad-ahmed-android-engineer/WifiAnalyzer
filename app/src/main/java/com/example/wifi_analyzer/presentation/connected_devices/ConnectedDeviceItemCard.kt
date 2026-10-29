package com.example.wifi_analyzer.presentation.connected_devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.wifi_analyzer.R
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.domain.model.ConnectedDevice

@Composable
fun ConnectedDeviceItemCard(
    device: ConnectedDevice,
    modifier: Modifier = Modifier
) {
    AppCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimensions.paddingDefault),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.devices_info),
                contentDescription = "Device Icon",
                modifier = Modifier.size(AppDimensions.icons.large)
            )
            Spacer(modifier = Modifier.width(AppDimensions.paddingDefault))
            Column(modifier = Modifier.weight(1f)) {
                AppTypography.TitleMedium(
                    text = device.name,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(AppDimensions.paddingMicro))
                AppTypography.BodySmall(
                    text = "IP: ${device.ipAddress} • MAC: ${device.macAddress}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                AppTypography.BodySmall(
                    text = "Vendor: ${device.vendorName}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}