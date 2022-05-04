package johan.kovalsikoski.composeplayground.ui.navigation

// Preferred method
object Destination {
    const val HOST_SCREEN = "host_screen"
}

// Alternative method
sealed class Screen(val route: String) {
    object Home : Screen(route = "host_screen")
}