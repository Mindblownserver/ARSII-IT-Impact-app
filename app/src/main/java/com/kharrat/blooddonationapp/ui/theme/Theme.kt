package com.kharrat.blooddonationapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = VerdantLedgerColors.Primary,
    secondary = VerdantLedgerColors.Secondary,
    tertiary = VerdantLedgerColors.Tertiary,
    background = VerdantLedgerColors.Background,
    surface = VerdantLedgerColors.Surface,
    surfaceVariant = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainer = VerdantLedgerColors.SurfaceContainer,
    surfaceContainerHigh = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainerLow = VerdantLedgerColors.SurfaceContainerLow,
    onPrimary = VerdantLedgerColors.OnPrimary,
    onSurface = VerdantLedgerColors.OnSurface,
    outlineVariant = VerdantLedgerColors.OutlineVariant,
    error = VerdantLedgerColors.Error
)

private val LightColorScheme = lightColorScheme(
    primary = VerdantLedgerColors.Primary,
    secondary = VerdantLedgerColors.Secondary,
    tertiary = VerdantLedgerColors.Tertiary,
    background = VerdantLedgerColors.Background,
    surface = VerdantLedgerColors.Surface,
    surfaceVariant = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainer = VerdantLedgerColors.SurfaceContainer,
    surfaceContainerHigh = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainerLow = VerdantLedgerColors.SurfaceContainerLow,
    onPrimary = VerdantLedgerColors.OnPrimary,
    onSurface = VerdantLedgerColors.OnSurface,
    outlineVariant = VerdantLedgerColors.OutlineVariant,
    error = VerdantLedgerColors.Error
)

@Composable
fun BloodDonationAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
