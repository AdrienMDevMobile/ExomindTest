package m.adrien.exomindtest.view.ui.converter

import m.adrien.exomindtest.R
import m.adrien.exomindtest.domain.model.LoadingMessage

fun LoadingMessage.toStringRes() = when(this){
    LoadingMessage.downloading -> R.string.weather_waiting_download
    LoadingMessage.almost_finished -> R.string.weather_waiting_finish
    LoadingMessage.only_seconds -> R.string.weather_waiting_result
}