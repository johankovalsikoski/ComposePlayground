package johan.kovalsikoski.composeplayground.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    onPrimary = Color.Magenta,
    secondary = Green500,
    secondaryVariant = Green700,
    onSecondary = Color.Black,
    error = RedErrorLight,
    onError = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkColorPalette = darkColors(
    primary = Orange500,
    primaryVariant = Orange700,
    onPrimary = Color.Red,
    secondary = Navy500,
    secondaryVariant = Navy700,
    onSecondary = Color.White,
    error = RedErrorDark,
    onError = Color.White,
    background = Color.Black,
    onBackground = Color.Cyan,
    surface = Color.Black,
    onSurface = Color.Cyan,
)

@Composable
fun ComposePlaygroundTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}