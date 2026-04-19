package com.kharrat.blooddonationapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorScheme = darkColorScheme(
    primary = VerdantLedgerDarkColors.Primary,
    primaryContainer = VerdantLedgerDarkColors.PrimaryContainer,
    secondary = VerdantLedgerDarkColors.Secondary,
    secondaryContainer = VerdantLedgerDarkColors.SecondaryContainer,
    tertiary = VerdantLedgerDarkColors.Tertiary,
    tertiaryContainer = VerdantLedgerDarkColors.TertiaryContainer,
    background = VerdantLedgerDarkColors.Background,
    surface = VerdantLedgerDarkColors.Surface,
    surfaceVariant = VerdantLedgerDarkColors.SurfaceContainerHigh,
    surfaceContainer = VerdantLedgerDarkColors.SurfaceContainer,
    surfaceContainerHigh = VerdantLedgerDarkColors.SurfaceContainerHigh,
    surfaceContainerLow = VerdantLedgerDarkColors.SurfaceContainerLow,
    onPrimary = VerdantLedgerDarkColors.OnPrimary,
    onPrimaryContainer = VerdantLedgerDarkColors.OnBackground,
    onSecondary = VerdantLedgerDarkColors.OnSecondary,
    onSecondaryContainer = VerdantLedgerDarkColors.OnBackground,
    onTertiary = VerdantLedgerDarkColors.OnSecondary,
    onTertiaryContainer = VerdantLedgerDarkColors.OnBackground,
    onBackground = VerdantLedgerDarkColors.OnBackground,
    onSurface = VerdantLedgerDarkColors.OnSurface,
    outlineVariant = VerdantLedgerDarkColors.OutlineVariant,
    error = VerdantLedgerDarkColors.Error,
    surfaceTint = VerdantLedgerDarkColors.Primary
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
    val extraColors = if (darkTheme) DarkAppExtraColors else LightAppExtraColors

    CompositionLocalProvider(LocalAppExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
