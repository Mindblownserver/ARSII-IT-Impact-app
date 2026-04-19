package com.kharrat.blooddonationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kharrat.blooddonationapp.ui.screens.GardenScreen
import com.kharrat.blooddonationapp.ui.screens.HomeScreen
import com.kharrat.blooddonationapp.ui.screens.LoginScreen
import com.kharrat.blooddonationapp.ui.screens.SettingsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    notificationsEnabled: Boolean,
    onNotificationsEnabledChange: (Boolean) -> Unit,
    darkThemeEnabled: Boolean,
    onDarkThemeEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Login.route,
        modifier = modifier
    ) {
        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Login.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppDestination.Home.route) {
            HomeScreen()
        }

        composable(AppDestination.Garden.route) {
            GardenScreen()
        }

        composable(AppDestination.Settings.route) {
            SettingsScreen(
                notificationsEnabled = notificationsEnabled,
                onNotificationsEnabledChange = onNotificationsEnabledChange,
                darkThemeEnabled = darkThemeEnabled,
                onDarkThemeEnabledChange = onDarkThemeEnabledChange
            )
        }
    }
}
