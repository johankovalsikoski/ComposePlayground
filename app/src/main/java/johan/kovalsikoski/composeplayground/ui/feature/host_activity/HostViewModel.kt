package johan.kovalsikoski.composeplayground.ui.feature.host_activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HostViewModel : ViewModel() {

    private val _uiViewState: MutableState<HostUIViewState> = mutableStateOf(HostUIViewState.HomeUI)
    val uiViewState = _uiViewState

    fun navigateToHome() {
        _uiViewState.value = HostUIViewState.HomeUI
    }

    fun navigateToProfile() {
        _uiViewState.value = HostUIViewState.ProfileUI
    }

    fun navigateToSettings() {
        _uiViewState.value = HostUIViewState.SettingsUI
    }

}