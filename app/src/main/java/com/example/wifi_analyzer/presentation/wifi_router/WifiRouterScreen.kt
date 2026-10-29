package com.example.wifi_analyzer.presentation.wifi_router

import android.annotation.SuppressLint
import android.webkit.HttpAuthHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wifi_analyzer.R
import com.example.wifi_analyzer.core.designsystem.components.AppAlertDialog
import com.example.wifi_analyzer.core.designsystem.components.AppButton
import com.example.wifi_analyzer.core.designsystem.components.AppCard
import com.example.wifi_analyzer.core.designsystem.components.AppIconButton
import com.example.wifi_analyzer.core.designsystem.components.AppOutlinedTextField
import com.example.wifi_analyzer.core.designsystem.components.AppTopBar
import com.example.wifi_analyzer.core.designsystem.dimensions.AppDimensions
import com.example.wifi_analyzer.core.designsystem.typography.AppTypography
import com.example.wifi_analyzer.core.utils.openUrl

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WifiRouterScreen(
    viewModel: WifiRouterViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var customIpInput by remember { mutableStateOf(if (uiState.gatewayIp.isEmpty() || uiState.gatewayIp == "0.0.0.0") "192.168.1.1" else uiState.gatewayIp) }
    var activeUrl by remember { mutableStateOf("http://$customIpInput") }

    var showAuthDialog by remember { mutableStateOf(false) }
    var authHandler by remember { mutableStateOf<HttpAuthHandler?>(null) }
    var authUsername by remember { mutableStateOf("admin") }
    var authPassword by remember { mutableStateOf("admin") }

    LaunchedEffect(uiState.gatewayIp) {
        if (uiState.gatewayIp.isNotEmpty() && uiState.gatewayIp != "0.0.0.0") {
            customIpInput = uiState.gatewayIp
            activeUrl = "http://${uiState.gatewayIp}"
        }
    }

    if (showAuthDialog) {
        AppAlertDialog(
            onDismissRequest = {
                authHandler?.cancel()
                showAuthDialog = false
            },
            title = "Router Authentication",
            text = "Enter username and password for the router.",
            confirmButtonText = "Login",
            onConfirm = {
                authHandler?.proceed(authUsername, authPassword)
                showAuthDialog = false
            },
            dismissButtonText = "Cancel",
            onDismiss = {
                authHandler?.cancel()
                showAuthDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.title_wifi_router),
                onBackClick = onBackClick,
                actions = {
                    AppIconButton(
                        icon = Icons.Default.Refresh,
                        contentDescription = "Refresh Gateway IP",
                        onClick = { viewModel.loadGateway() }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppDimensions.paddingDefault)
        ) {
            // Router IP Config Bar
            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(AppDimensions.paddingDefault)) {
                    AppTypography.LabelMedium(text = stringResource(id = R.string.label_router_ip))
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppOutlinedTextField(
                            value = customIpInput,
                            onValueChange = { customIpInput = it },
                            placeholder = "e.g. 192.168.1.1",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(AppDimensions.paddingSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppButton(
                            text = stringResource(id = R.string.btn_load_admin_page),
                            onClick = {
                                val cleanIp = customIpInput.trim()
                                val formattedUrl = if (cleanIp.startsWith("http")) cleanIp else "http://$cleanIp"
                                activeUrl = formattedUrl
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

            // Embedded Router Admin WebView
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            webViewClient = object : WebViewClient() {
                                override fun onReceivedHttpAuthRequest(
                                    view: WebView,
                                    handler: HttpAuthHandler,
                                    host: String,
                                    realm: String
                                ) {
                                    authHandler = handler
                                    showAuthDialog = true
                                }
                            }
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            settings.useWideViewPort = true
                            settings.loadWithOverviewMode = true
                            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            loadUrl(activeUrl)
                        }
                    },
                    update = { webView ->
                        if (webView.url != activeUrl && webView.url?.replace("/","") != activeUrl.replace("/","")) {
                            webView.loadUrl(activeUrl)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(AppDimensions.paddingDefault))

            AppButton(
                text = stringResource(id = R.string.btn_open_external_browser),
                onClick = { context.openUrl(activeUrl) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}