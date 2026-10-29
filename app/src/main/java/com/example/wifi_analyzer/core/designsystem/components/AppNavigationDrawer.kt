package com.example.wifi_analyzer.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.wifi_analyzer.R
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.clickableDebounced

data class DrawerMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun AppNavigationDrawer(
    items: List<DrawerMenuItem>,
    selectedItemId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        modifier = modifier.width(AppDimensions.components.fab * 5),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppDimensions.components.emptyStateIconContainer * 2)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(AppDimensions.paddingDefault),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    AppTypography.HeadlineSmall(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    AppTypography.BodySmall(
                        text = stringResource(id = R.string.app_subtitle),
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

            items.forEach { item ->
                val isSelected = item.id == selectedItemId
                val backgroundColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
                val contentColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickableDebounced { onItemSelected(item.id) }
                        .padding(
                            horizontal = AppDimensions.paddingDefault,
                            vertical = AppDimensions.paddingMedium
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(AppDimensions.paddingMedium))
                    AppTypography.LabelLarge(
                        text = item.title,
                        color = contentColor
                    )
                }
            }
        }
    }
}