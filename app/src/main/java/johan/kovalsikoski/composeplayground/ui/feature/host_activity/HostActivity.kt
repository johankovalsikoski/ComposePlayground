package johan.kovalsikoski.composeplayground.ui.feature.host_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import johan.kovalsikoski.composeplayground.ui.navigation.Destination
import johan.kovalsikoski.composeplayground.ui.navigation.ProjectNavigationHost
import johan.kovalsikoski.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

class HostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposePlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProjectNavigationHost(
                        navController = navController,
                        startDestination = Destination.HOST_SCREEN
                    )
                }
            }
        }
    }
}

@Composable
fun HostScreen(navController: NavController = rememberNavController()) {
    val hostViewModel = getViewModel<HostViewModel>()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                scaffoldState = scaffoldState,
                navController = navController,
                screenTitle = "Home"
            )
        },
        bottomBar = { BottomBar() },
        drawerContent = { DrawerContent() },
        content = { paddingValues ->
            when (hostViewModel.uiViewState.value) {
                HostUIViewState.HomeUI -> {
                    ConstraintScreenBase(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .padding(paddingValues)
                    )
                }

                HostUIViewState.ProfileUI -> {
                    ConstraintScreenBase(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green)
                            .padding(paddingValues)
                    )
                }

                HostUIViewState.SettingsUI -> {
                    ConstraintScreenBase(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                            .padding(paddingValues)
                    )
                }
            }
        }
    )
}

@Composable
private fun ConstraintScreenBase(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Hi!")
    }
}

@Composable
private fun AppTopBar(
    scaffoldState: ScaffoldState,
    navController: NavController,
    screenTitle: String = "",
    showDrawerMenu: Boolean = true,
    actionItems: @Composable (() -> Unit?)? = null
) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        elevation = 8.dp,
        title = { Text(text = screenTitle) },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            if (showDrawerMenu) {
                IconButton(onClick = {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }) {
                    Icon(Icons.Filled.Menu, "Open drawer menu")
                }
            } else {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.ArrowBack, "Back arrow")
                }
            }
        },
        actions = {
            if (actionItems != null)
                actionItems()
        }
    )
}

@Composable
private fun BottomBar() {
    val hostViewModel = getViewModel<HostViewModel>()

    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            icon = { Icon(Icons.Filled.Home, "Home") },
            onClick = { hostViewModel.navigateToHome() }
        )

        BottomNavigationItem(
            selected = false,
            icon = {
                BadgedBox(badge = {
                    if (true) {
                        Badge(backgroundColor = Color.Red) {
                            Text(
                                text = "01",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }) {
                    Icon(Icons.Filled.Person, "Profile")
                }
            },
            onClick = { hostViewModel.navigateToProfile() }
        )

        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Filled.Settings, "Settings") },
            onClick = { hostViewModel.navigateToSettings() }
        )
    }
}

@Composable
private fun DrawerContent() {
    var currentColor by remember { mutableStateOf(MyColors.Red) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            MyColors.values().forEach { myColor ->
                Button(
                    onClick = { currentColor = myColor },
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .height(48.dp)
                        .background(myColor.color),
                    colors = ButtonDefaults.buttonColors(backgroundColor = myColor.color)
                ) {
                    Text(text = myColor.name)
                }
            }
        }
        Crossfade(targetState = currentColor, animationSpec = tween(3000)) { selectedColor ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(selectedColor.color)
            )
        }
    }
}

@Preview
@Composable
private fun HostScreenPreview() {
    HostScreen()
}

private enum class MyColors(val color: Color) {
    Red(Color.Red), Green(Color.Green), Blue(Color.Blue)
}

