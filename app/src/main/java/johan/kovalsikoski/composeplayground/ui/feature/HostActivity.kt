package johan.kovalsikoski.composeplayground.ui.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import johan.kovalsikoski.composeplayground.ui.FirstScreenActivity
import johan.kovalsikoski.composeplayground.ui.ProjectNavigationHost
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.FirstScreen
import johan.kovalsikoski.composeplayground.ui.theme.ComposePlaygroundTheme

class HostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposePlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProjectNavigationHost(navController = navController, startDestination = FirstScreenActivity)
                }
            }
        }
    }

}