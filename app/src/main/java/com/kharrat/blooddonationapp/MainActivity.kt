package com.kharrat.blooddonationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kharrat.blooddonationapp.ui.McpBloodDonationApp
import com.kharrat.blooddonationapp.ui.theme.BloodDonationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPreferences = remember {
                getSharedPreferences("mcp_blood_donation_prefs", MODE_PRIVATE)
            }
            var isDarkThemeEnabled by remember {
                mutableStateOf(sharedPreferences.getBoolean("dark_theme_enabled", false))
            }
            var notificationsEnabled by remember {
                mutableStateOf(sharedPreferences.getBoolean("notifications_enabled", true))
            }

            BloodDonationAppTheme(darkTheme = isDarkThemeEnabled) {
                McpBloodDonationApp(
                    notificationsEnabled = notificationsEnabled,
                    onNotificationsEnabledChange = { enabled ->
                        notificationsEnabled = enabled
                        sharedPreferences
                            .edit()
                            .putBoolean("notifications_enabled", enabled)
                            .apply()
                    },
                    darkThemeEnabled = isDarkThemeEnabled,
                    onDarkThemeEnabledChange = { enabled ->
                        isDarkThemeEnabled = enabled
                        sharedPreferences
                            .edit()
                            .putBoolean("dark_theme_enabled", enabled)
                            .apply()
                    }
                )
            }
        }
    }
}
