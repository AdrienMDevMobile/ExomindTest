package m.adrien.exomindtest.view.ui.converter

import m.adrien.exomindtest.R
import m.adrien.exomindtest.domain.model.LoadingMessage

fun LoadingMessage.toStringRes() = when(this){
    LoadingMessage.Downloading -> R.string.weather_waiting_download
    LoadingMessage.AlmostFinished -> R.string.weather_waiting_finish
    LoadingMessage.OnlySeconds -> R.string.weather_waiting_result
}