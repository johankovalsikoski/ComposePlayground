package johan.kovalsikoski.composeplayground.preview_parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import johan.kovalsikoski.composeplayground.ui.feature.main.MainViewModel

class MainViewModelPreviewParam : PreviewParameterProvider<MainViewModel> {
    override val values: Sequence<MainViewModel> = sequenceOf(MainViewModel())
}