package com.kharrat.blooddonationapp.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.outlined.DoNotDisturb
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kharrat.blooddonationapp.ui.navigation.AppDestination
import com.kharrat.blooddonationapp.ui.navigation.AppNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McpBloodDonationApp() {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("mcp_blood_donation_prefs", Context.MODE_PRIVATE)
    }
    var isDoNotDisturbEnabled by remember {
        mutableStateOf(sharedPreferences.getBoolean("do_not_disturb_enabled", false))
    }
    var showDoNotDisturbDialog by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val bottomBarItems = AppDestination.bottomBarItems
    val showTopBar = currentRoute != AppDestination.Login.route
    val showBottomBar = bottomBarItems.any { item -> item.route == currentRoute }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showTopBar) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("FidaaTUN")
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                if (isDoNotDisturbEnabled) {
                                    isDoNotDisturbEnabled = false
                                    sharedPreferences
                                        .edit()
                                        .putBoolean("do_not_disturb_enabled", false)
                                        .apply()
                                } else {
                                    showDoNotDisturbDialog = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isDoNotDisturbEnabled) {
                                    Icons.Filled.DoNotDisturbOn
                                } else {
                                    Icons.Outlined.DoNotDisturb
                                },
                                contentDescription = "Do not disturb",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    bottomBarItems.forEach { destination ->
                        NavigationBarItem(
                            selected = destination.route == currentRoute,
                            onClick = {
                                if (currentRoute != destination.route) {
                                    navController.navigate(destination.route) {
                                        popUpTo(AppDestination.Home.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = { Icon(imageVector = destination.icon, contentDescription = destination.title) },
                            label = { Text(text = destination.shortLabel) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (showDoNotDisturbDialog) {
        AlertDialog(
            onDismissRequest = {
                showDoNotDisturbDialog = false
            },
            title = {
                Text(text = "Enable Do Not Disturb")
            },
            text = {
                Text(text = "Do you want to activate Do Not Disturb mode?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDoNotDisturbEnabled = true
                        sharedPreferences
                            .edit()
                            .putBoolean("do_not_disturb_enabled", true)
                            .apply()
                        showDoNotDisturbDialog = false
                    }
                ) {
                    Text(text = "Accept")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showDoNotDisturbDialog = false
                    }
                ) {
                    Text(text = "Decline")
                }
            }
        )
    }
}
