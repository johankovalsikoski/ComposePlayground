package johan.kovalsikoski.composeplayground.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.HomeScreen
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.HomeScreenViewModel

@Composable
fun ProjectNavigationHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Destination.HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
//        navigation() - for nested navGraph
    }
}
