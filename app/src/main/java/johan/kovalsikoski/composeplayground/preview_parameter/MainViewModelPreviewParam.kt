package johan.kovalsikoski.composeplayground.preview_parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import johan.kovalsikoski.composeplayground.ui.feature.first_screen.FirstScreenViewModel

class MainViewModelPreviewParam : PreviewParameterProvider<FirstScreenViewModel> {
    override val values: Sequence<FirstScreenViewModel> = sequenceOf(FirstScreenViewModel())
}