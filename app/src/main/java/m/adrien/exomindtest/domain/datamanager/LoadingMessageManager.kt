package m.adrien.exomindtest.domain.datamanager

import kotlinx.coroutines.flow.Flow
import m.adrien.exomindtest.domain.model.LoadingMessage

interface LoadingMessageManager {
    fun getLoadingMessage(): Flow<LoadingMessage>
}