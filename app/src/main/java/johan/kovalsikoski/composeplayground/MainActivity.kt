package johan.kovalsikoski.composeplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import johan.kovalsikoski.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                MainActivityContent()
            }
        }
    }
}

@Composable
private fun Greeting(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(modifier = modifier, text = "Hello $text!")
}

@Composable
private fun TopBar(scaffoldState: ScaffoldState) {
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
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }) {
                    Icon(Icons.Filled.Menu, "Open drawer menu")
                }
            }, actions = {
                IconButton(onClick = {
                    Toast.makeText(context, "Share Click", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Filled.Share, "Share")
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
private fun BottomNavigationWithBadgeBox() {
    val context = LocalContext.current

    var isSelected by remember { mutableStateOf(false) }

    BottomNavigation {
        BottomNavigationItem(
            selected = isSelected,
            icon = {
                BadgedBox(badge = {
                    if (!isSelected) {
                        Badge(backgroundColor = Color.Red) {
                            Text(text = "10", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }) {
                    Icon(Icons.Filled.Favorite, "Favorite")
                }
            },
            onClick = {
                isSelected = !isSelected
                Toast.makeText(context, "Settings Click", Toast.LENGTH_SHORT).show()
            })
    }
}

@Composable
private fun MainActivityContent() {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(scaffoldState) },
        bottomBar = { BottomNavigationWithBadgeBox() },
        drawerContent = {
            Text(text = "Drawer Item")
        },
        content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val (boxRed, boxGreen, boxBlue, textCoveredByBlue, textAboveAll, textMessage, buttonAddNotification) = createRefs()

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

                Text(text = "Covered by the blue box",
                    modifier = Modifier
                        .background(color = Color.Yellow)
                        .constrainAs(textCoveredByBlue) {
                            width = Dimension.fillToConstraints
                            top.linkTo(boxRed.top)
                            start.linkTo(boxRed.start)
                            bottom.linkTo(boxRed.bottom)
                        },)

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
                    modifier = Modifier.constrainAs(buttonAddNotification) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    onClick = {
                        // TODO: Find a way to update notifications
                    }
                ) {
                    Text(text = "ADD NOTIFICATION")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposePlaygroundTheme {
        MainActivityContent()
    }
}