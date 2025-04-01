package m.adrien.exomindtest.data.managerimpl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m.adrien.exomindtest.domain.datamanager.LoadingMessageManager
import m.adrien.exomindtest.domain.model.LoadingMessage
import javax.inject.Inject

class LoadingMessageManagerImpl @Inject constructor() : LoadingMessageManager {
    override fun getLoadingMessage(): Flow<LoadingMessage> = flow {
        var messageId = 0
        while (true) {
            emit(
                when (messageId) {
                    0 -> LoadingMessage.Downloading
                    1 -> LoadingMessage.AlmostFinished
                    2 -> LoadingMessage.OnlySeconds
                    else -> LoadingMessage.OnlySeconds
                }
            )
            if (messageId == 2) {
                messageId = 0
            } else messageId++
            delay(6000)
        }
    }

}