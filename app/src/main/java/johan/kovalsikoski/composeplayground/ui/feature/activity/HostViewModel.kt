package johan.kovalsikoski.composeplayground.ui.feature.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HostViewModel: ViewModel() {

    private val _hostUIState = mutableStateOf(HostUIState())
    val hostUIState: State<HostUIState> = _hostUIState

}