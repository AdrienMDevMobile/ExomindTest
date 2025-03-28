package m.adrien.exomindtest.view.ui.event

sealed class LoadingEvent {
    object OnLoadingClick: LoadingEvent()
    class OnLoadingAnimationFinished(val value: Float): LoadingEvent()
}