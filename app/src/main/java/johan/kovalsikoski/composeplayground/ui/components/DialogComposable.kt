package johan.kovalsikoski.composeplayground.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogComposable(
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {}) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .background(Color.Green)
        ) {
            Text(text = "Text")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DialogComposablePreview() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            DialogComposable()
        }
    }
}