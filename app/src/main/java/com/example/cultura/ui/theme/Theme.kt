package com.example.cultura.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// --- Color Palette ---

// Light Theme Colors
val PrimaryLight = Color(0xFFFFC107)       // Indigo 700
val SecondaryLight = Color(0xFF303F9F)     // Amber 500
val BackgroundLight = Color(0xFFFFFBFE)    // Light background
val SurfaceLight = Color(0xFFFFFFFF)
val OnLight = Color(0xFF121212)            // Text on light

// Dark Theme Colors
val PrimaryDark = Color(0xFF7986CB)        // Indigo 400
val SecondaryDark = Color(0xFFFFD54F)      // Amber 300
val BackgroundDark = Color(0xFF121212)     // Dark background
val SurfaceDark = Color(0xFF1E1E1E)
val OnDark = Color(0xFFE0E0E0)             // Text on dark

// --- Color Schemes ---

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnDark,
    onSecondary = OnDark,
    onBackground = OnDark,
    onSurface = OnDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = OnLight,
    onSecondary = OnLight,
    onBackground = OnLight,
    onSurface = OnLight
)

// --- Theme Composable ---

@Composable
fun CulturaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
