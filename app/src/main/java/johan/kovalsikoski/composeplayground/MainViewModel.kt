package johan.kovalsikoski.composeplayground

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import johan.kovalsikoski.composeplayground.data.MainState
import johan.kovalsikoski.composeplayground.data.ScreenPage

class MainViewModel : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun onAddNotification() {
        _state.value = state.value.copy(
            notifications = state.value.notifications + 1
        )
    }

    fun onPageChange(page: ScreenPage) {
        _state.value = state.value.copy(
            page = page
        )
    }

}
