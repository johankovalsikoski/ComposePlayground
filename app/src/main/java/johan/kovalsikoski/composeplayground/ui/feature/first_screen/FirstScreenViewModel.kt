package johan.kovalsikoski.composeplayground.ui.feature.first_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import johan.kovalsikoski.composeplayground.data.FirstScreenState
import johan.kovalsikoski.composeplayground.data.ScreenPage

class FirstScreenViewModel : ViewModel() {

    private val _state = mutableStateOf(FirstScreenState())
    val state: State<FirstScreenState> = _state

    fun onAddNotification() {
        _state.value = state.value.copy(
            notifications = state.value.notifications + 1
        )
    }

    fun clearNotificatons() {
        _state.value = state.value.copy(
            notifications = 0
        )
    }

    fun hasNotifications() = state.value.notifications > 0

    fun getCurrentPage() = state.value.page

    fun getNotificationQuantity() = state.value.notifications

    fun onPageChange(page: ScreenPage) {
        _state.value = state.value.copy(
            page = page
        )
    }

}
