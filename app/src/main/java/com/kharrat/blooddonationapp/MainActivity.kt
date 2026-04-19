package com.kharrat.blooddonationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kharrat.blooddonationapp.ui.McpBloodDonationApp
import com.kharrat.blooddonationapp.ui.theme.BloodDonationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloodDonationAppTheme {
                McpBloodDonationApp()
            }
        }
    }
}
