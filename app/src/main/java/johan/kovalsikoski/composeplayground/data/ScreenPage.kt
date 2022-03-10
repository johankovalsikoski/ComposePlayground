package johan.kovalsikoski.composeplayground.data

sealed class ScreenPage {
    object MainPage : ScreenPage()
    object MessagePage: ScreenPage()
}