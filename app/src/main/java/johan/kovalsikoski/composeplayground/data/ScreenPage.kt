package johan.kovalsikoski.composeplayground.data

sealed class ScreenPage {
    object HomePage : ScreenPage()
    object ProfilePage: ScreenPage()
    object SettingsPage: ScreenPage()
}