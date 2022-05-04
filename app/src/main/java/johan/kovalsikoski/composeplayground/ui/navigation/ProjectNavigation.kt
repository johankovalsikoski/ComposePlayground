package johan.kovalsikoski.composeplayground.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import johan.kovalsikoski.composeplayground.ui.feature.host_activity.HostScreen
import johan.kovalsikoski.composeplayground.ui.navigation.Destination.HOST_SCREEN


@Composable
fun ProjectNavigationHost(
    navController: NavHostController,
    startDestination: String = HOST_SCREEN
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = startDestination) {
            HostScreen(navController)
        }
//        navigation() - for nested navGraph
    }
}
