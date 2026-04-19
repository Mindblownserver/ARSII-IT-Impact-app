package com.kharrat.blooddonationapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppDestination(
    val route: String,
    val title: String,
    val shortLabel: String,
    val icon: ImageVector
) {
    data object Login : AppDestination(
        route = "login",
        title = "Welcome",
        shortLabel = "Login",
        icon = Icons.Rounded.Home
    )

    data object Home : AppDestination(
        route = "home",
        title = "Home",
        shortLabel = "Home",
        icon = Icons.Rounded.Home
    )

    data object Garden : AppDestination(
        route = "garden",
        title = "Garden",
        shortLabel = "Garden",
        icon = Icons.Rounded.Spa
    )

    data object Settings : AppDestination(
        route = "settings",
        title = "Settings",
        shortLabel = "Settings",
        icon = Icons.Rounded.Settings
    )

    companion object {
        val bottomBarItems = listOf(Home, Garden, Settings)

        fun fromRoute(route: String?): AppDestination {
            return when (route) {
                Home.route -> Home
                Garden.route -> Garden
                Settings.route -> Settings
                else -> Login
            }
        }
    }
}
