package com.example.wifi_analyzer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wifi_analyzer.presentation.add_wifi.AddWifiScreen
import com.example.wifi_analyzer.presentation.add_wifi.AddWifiViewModel
import com.example.wifi_analyzer.presentation.all_wifis.AllWifisScreen
import com.example.wifi_analyzer.presentation.all_wifis.AllWifisViewModel
import com.example.wifi_analyzer.presentation.channel_rating.ChannelRatingScreen
import com.example.wifi_analyzer.presentation.channel_rating.ChannelRatingViewModel
import com.example.wifi_analyzer.presentation.connected_devices.ConnectedDevicesScreen
import com.example.wifi_analyzer.presentation.connected_devices.ConnectedDevicesViewModel
import com.example.wifi_analyzer.presentation.generate_qr.GenerateQrScreen
import com.example.wifi_analyzer.presentation.generate_qr.GenerateQrViewModel
import com.example.wifi_analyzer.presentation.main.MainScreen
import com.example.wifi_analyzer.presentation.main.MainViewModel
import com.example.wifi_analyzer.presentation.password_generator.PasswordGeneratorScreen
import com.example.wifi_analyzer.presentation.password_generator.PasswordGeneratorViewModel
import com.example.wifi_analyzer.presentation.qr_scanner.QrScannerScreen
import com.example.wifi_analyzer.presentation.qr_scanner.QrScannerViewModel
import com.example.wifi_analyzer.presentation.signal_meter.SignalMeterScreen
import com.example.wifi_analyzer.presentation.signal_meter.SignalMeterViewModel
import com.example.wifi_analyzer.presentation.speed_test.SpeedTestScreen
import com.example.wifi_analyzer.presentation.speed_test.SpeedTestViewModel
import com.example.wifi_analyzer.presentation.splash.SplashScreen
import com.example.wifi_analyzer.presentation.splash.SplashViewModel
import com.example.wifi_analyzer.presentation.wifi_info.WifiInfoScreen
import com.example.wifi_analyzer.presentation.wifi_info.WifiInfoViewModel
import com.example.wifi_analyzer.presentation.wifi_router.WifiRouterScreen
import com.example.wifi_analyzer.presentation.wifi_router.WifiRouterViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH,
        modifier = modifier
    ) {
        composable(NavRoutes.SPLASH) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                viewModel = viewModel,
                onNavigateToMain = {
                    navController.navigate(NavRoutes.MAIN) {
                        popUpTo(NavRoutes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.MAIN) {
            val viewModel: MainViewModel = hiltViewModel()
            MainScreen(
                viewModel = viewModel,
                onNavigateToWifiInfo = { navController.navigate(NavRoutes.WIFI_INFO) },
                onNavigateToAllWifis = { navController.navigate(NavRoutes.ALL_WIFIS) },
                onNavigateToConnectedDevices = { navController.navigate(NavRoutes.CONNECTED_DEVICES) },
                onNavigateToSpeedTest = { navController.navigate(NavRoutes.SPEED_TEST) },
                onNavigateToPasswordGenerator = { navController.navigate(NavRoutes.PASSWORD_GENERATOR) },
                onNavigateToWifiRouter = { navController.navigate(NavRoutes.WIFI_ROUTER) },
                onNavigateToAddWifi = { navController.navigate(NavRoutes.ADD_WIFI) },
                onNavigateToGenerateQr = { navController.navigate(NavRoutes.GENERATE_QR) },
                onNavigateToQrScanner = { navController.navigate(NavRoutes.QR_SCANNER) },
                onNavigateToChannelRating = { navController.navigate(NavRoutes.CHANNEL_RATING) },
                onNavigateToSignalMeter = { navController.navigate(NavRoutes.SIGNAL_METER) }
            )
        }

        composable(NavRoutes.WIFI_INFO) {
            val viewModel: WifiInfoViewModel = hiltViewModel()
            WifiInfoScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.ALL_WIFIS) {
            val viewModel: AllWifisViewModel = hiltViewModel()
            AllWifisScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSelectNetwork = { _ -> }
            )
        }

        composable(NavRoutes.CONNECTED_DEVICES) {
            val viewModel: ConnectedDevicesViewModel = hiltViewModel()
            ConnectedDevicesScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.SPEED_TEST) {
            val viewModel: SpeedTestViewModel = hiltViewModel()
            SpeedTestScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.PASSWORD_GENERATOR) {
            val viewModel: PasswordGeneratorViewModel = hiltViewModel()
            PasswordGeneratorScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.WIFI_ROUTER) {
            val viewModel: WifiRouterViewModel = hiltViewModel()
            WifiRouterScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.ADD_WIFI) {
            val viewModel: AddWifiViewModel = hiltViewModel()
            AddWifiScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.GENERATE_QR) {
            val viewModel: GenerateQrViewModel = hiltViewModel()
            GenerateQrScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.QR_SCANNER) {
            val viewModel: QrScannerViewModel = hiltViewModel()
            QrScannerScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.CHANNEL_RATING) {
            val viewModel: ChannelRatingViewModel = hiltViewModel()
            ChannelRatingScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.SIGNAL_METER) {
            val viewModel: SignalMeterViewModel = hiltViewModel()
            SignalMeterScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}