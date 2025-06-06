package m.adrien.exomindtest.view.ui.loadingBar

sealed class LoadingBarUiState {
    class Loading(
        val progress: Float,
    ) : LoadingBarUiState()

    data object Waiting : LoadingBarUiState()
    data object Finished : LoadingBarUiState()
    data object WaitRestart : LoadingBarUiState()
}