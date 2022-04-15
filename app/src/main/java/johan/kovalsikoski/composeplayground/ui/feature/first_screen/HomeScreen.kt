package johan.kovalsikoski.composeplayground.ui.feature.first_screen

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import johan.kovalsikoski.composeplayground.data.ScreenPage
import johan.kovalsikoski.composeplayground.ui.feature.canvas.CanvasActivity
import org.koin.androidx.compose.getViewModel

private enum class MyColors(val color: Color) {
    Red(Color.Red), Green(Color.Green), Blue(Color.Blue)
}

// TODO: Needs to fix preview
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    val viewModel: HomeScreenViewModel = getViewModel()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { BottomNavigationWithBadgeBox(viewModel) },
        drawerContent = { DrawerContent() },
        content = {
            if (viewModel.getCurrentPage() == ScreenPage.HomePage) {
                MainContent(viewModel, it)
            } else {
                MessageContent(viewModel, it)
            }
        }
    )
}

@Composable
private fun Greeting(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(modifier = modifier, text = "Hello $text!")
}

@Composable
private fun TopBar() {
    val context = LocalContext.current //for any reason this just work here
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = "I'm a top bar")
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = {
//                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }) {
                    Icon(Icons.Filled.Menu, "Open drawer menu")
                }
            }, actions = {
                IconButton(onClick = {
                    context.startActivity(Intent(context, CanvasActivity::class.java))
                }) {
                    Icon(Icons.Filled.ArrowForward, "Next Screen")
                }
                IconButton(onClick = {
                    Toast.makeText(context, "Settings Click", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Filled.Settings, "Settings")
                }
            }
        )
    }
}

@Composable
private fun BottomNavigationWithBadgeBox(
    viewModel: HomeScreenViewModel
) {
    val context = LocalContext.current

    BottomNavigation {
        BottomNavigationItem(
            selected = viewModel.getCurrentPage() == ScreenPage.HomePage,
            icon = { Icon(Icons.Filled.Person, "Profile") },
            onClick = {
                viewModel.onPageChange(ScreenPage.HomePage)
            }
        )

        BottomNavigationItem(
            selected = viewModel.getCurrentPage() == ScreenPage.ProfilePage,
            icon = {
                BadgedBox(badge = {
                    if (viewModel.getCurrentPage() != ScreenPage.ProfilePage && viewModel.hasNotifications()) {
                        Badge(backgroundColor = Color.Red) {
                            Text(
                                text = "${viewModel.getNotificationQuantity()}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }) {
                    Icon(Icons.Filled.Favorite, "Favorite")
                }
            },
            onClick = {
                viewModel.onPageChange(ScreenPage.ProfilePage)
                Toast.makeText(context, "Message Page Click", Toast.LENGTH_SHORT).show()
            })
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

@Composable
private fun MessageContent(
    viewModel: HomeScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(8.dp)
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (textMessages, buttonReadMessages) = createRefs()

        Text(
            text = "Notifications here: ${viewModel.getNotificationQuantity()}",
            modifier = Modifier.constrainAs(textMessages) {
                centerTo(parent)
            },
        )

        Button(
            modifier = Modifier.constrainAs(buttonReadMessages) {
                centerVerticallyTo(parent, bias = 1f)
                centerHorizontallyTo(parent)
            },
            onClick = {
                viewModel.clearNotificatons()
            }) {
            Text(text = "Click to read all messages")
        }

    }
}

@Composable
private fun MainContent(
    viewModel: HomeScreenViewModel = getViewModel(),
    paddingValues: PaddingValues = PaddingValues(8.dp)
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (boxRed, boxGreen, boxBlue, textCoveredByBlue, textMessage, buttonAddNotification) = createRefs()

        // Box overlap each other
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(color = Color.Red)
                .constrainAs(boxRed) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Box(
            modifier = Modifier
                .size(90.dp)
                .background(color = Color.Green)
                .constrainAs(boxGreen) {
                    top.linkTo(boxRed.top)
                    start.linkTo(boxRed.start, margin = 55.dp)
                }
        )

        Text(
            text = "Covered by the blue box",
            modifier = Modifier
                .background(color = Color.Yellow)
                .constrainAs(textCoveredByBlue) {
                    width = Dimension.fillToConstraints
                    top.linkTo(boxRed.top)
                    start.linkTo(boxRed.start)
                    bottom.linkTo(boxRed.bottom)
                },
        )

        Box(
            modifier = Modifier
                .size(90.dp)
                .background(color = Color.Blue)
                .constrainAs(boxBlue) {
                    top.linkTo(boxGreen.top)
                    start.linkTo(boxGreen.start, margin = 55.dp)
                }
        )

        Greeting(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(textMessage) {
                    centerTo(parent)
                },
            text = "Johan Vidal Kovalsikoski"
        )

        Button(
            modifier = Modifier
                .constrainAs(buttonAddNotification) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            onClick = {
                viewModel.onAddNotification()
            }
        ) {
            Text(text = "ADD NOTIFICATION")
        }
    }
}