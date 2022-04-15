package johan.kovalsikoski.composeplayground.ui.navigation

// Preferred method
object Destination {
    const val HOME_SCREEN = "home_screen"
}

// Alternative method
sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
}