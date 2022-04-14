package johan.kovalsikoski.composeplayground.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.FirstScreen

@Composable
fun ProjectNavigationHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = FirstScreenActivity) {
            FirstScreen(navController = navController)
        }
    }
}

const val FirstScreenActivity = "FIRST_SCREEN_ACTIVITY"