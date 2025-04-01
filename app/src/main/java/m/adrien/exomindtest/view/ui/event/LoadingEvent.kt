package m.adrien.exomindtest.view.ui.event

sealed class LoadingEvent {
    data object OnLoadingClick: LoadingEvent()
    data class OnLoadingAnimationFinished(val value: Float): LoadingEvent()
    data object OnLoadingCompletedAnimationFinished: LoadingEvent()
}