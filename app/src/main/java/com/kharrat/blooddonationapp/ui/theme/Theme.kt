package com.kharrat.blooddonationapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = VerdantLedgerColors.Primary,
    primaryContainer = VerdantLedgerColors.PrimaryContainer,
    secondary = VerdantLedgerColors.Secondary,
    secondaryContainer = VerdantLedgerColors.SecondaryContainer,
    tertiary = VerdantLedgerColors.Tertiary,
    tertiaryContainer = VerdantLedgerColors.TertiaryContainer,
    background = VerdantLedgerColors.Background,
    surface = VerdantLedgerColors.Surface,
    surfaceVariant = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainer = VerdantLedgerColors.SurfaceContainer,
    surfaceContainerHigh = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainerLow = VerdantLedgerColors.SurfaceContainerLow,
    onPrimary = VerdantLedgerColors.OnPrimary,
    onPrimaryContainer = VerdantLedgerColors.OnBackground,
    onSecondary = VerdantLedgerColors.OnSecondary,
    onSecondaryContainer = VerdantLedgerColors.OnBackground,
    onTertiary = VerdantLedgerColors.OnSecondary,
    onTertiaryContainer = VerdantLedgerColors.OnBackground,
    onBackground = VerdantLedgerColors.OnBackground,
    onSurface = VerdantLedgerColors.OnSurface,
    outlineVariant = VerdantLedgerColors.OutlineVariant,
    error = VerdantLedgerColors.Error,
    surfaceTint = VerdantLedgerColors.Primary
)

private val LightColorScheme = lightColorScheme(
    primary = VerdantLedgerColors.Primary,
    primaryContainer = VerdantLedgerColors.PrimaryContainer,
    secondary = VerdantLedgerColors.Secondary,
    secondaryContainer = VerdantLedgerColors.SecondaryContainer,
    tertiary = VerdantLedgerColors.Tertiary,
    tertiaryContainer = VerdantLedgerColors.TertiaryContainer,
    background = VerdantLedgerColors.Background,
    surface = VerdantLedgerColors.Surface,
    surfaceVariant = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainer = VerdantLedgerColors.SurfaceContainer,
    surfaceContainerHigh = VerdantLedgerColors.SurfaceContainerHigh,
    surfaceContainerLow = VerdantLedgerColors.SurfaceContainerLow,
    onPrimary = VerdantLedgerColors.OnPrimary,
    onPrimaryContainer = VerdantLedgerColors.OnBackground,
    onSecondary = VerdantLedgerColors.OnSecondary,
    onSecondaryContainer = VerdantLedgerColors.OnBackground,
    onTertiary = VerdantLedgerColors.OnSecondary,
    onTertiaryContainer = VerdantLedgerColors.OnBackground,
    onBackground = VerdantLedgerColors.OnBackground,
    onSurface = VerdantLedgerColors.OnSurface,
    outlineVariant = VerdantLedgerColors.OutlineVariant,
    error = VerdantLedgerColors.Error,
    surfaceTint = VerdantLedgerColors.Primary
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
