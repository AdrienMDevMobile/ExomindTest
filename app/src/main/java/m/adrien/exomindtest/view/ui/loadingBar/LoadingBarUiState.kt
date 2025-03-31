package m.adrien.exomindtest.view.ui.loadingBar

import m.adrien.exomindtest.domain.model.LoadingMessage

sealed class LoadingBarUiState {
    class Loading(
        val progress: Float,
        val message: LoadingMessage,
    ) : LoadingBarUiState()

    data object Waiting : LoadingBarUiState()
    data object Finished : LoadingBarUiState()
}