package johan.kovalsikoski.composeplayground.ui.feature.host_activity

sealed class HostUIViewState {
    object HomeUI: HostUIViewState()
    object ProfileUI: HostUIViewState()
    object SettingsUI: HostUIViewState()
}