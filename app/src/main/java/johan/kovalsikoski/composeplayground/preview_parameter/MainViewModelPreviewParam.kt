package johan.kovalsikoski.composeplayground.preview_parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.HomeScreenViewModel

class MainViewModelPreviewParam : PreviewParameterProvider<HomeScreenViewModel> {
    override val values = sequenceOf(HomeScreenViewModel())
}
