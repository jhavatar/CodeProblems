package io.chthonic.codprob.ui.common.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import io.chthonic.codprob.ui.common.model.Message
import io.chthonic.codprob.ui.common.model.MessageType
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

abstract class BaseViewModel : ViewModel() {

    private val messageChannel = BroadcastChannel<Message>(1)

    val messageObservable: ReceiveChannel<Message>
        get() = messageChannel.openSubscription()

    @Suppress("unused")
    suspend fun showInfo(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.INFO))

    @Suppress("unused")
    suspend fun showWarning(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.WARNING))

    @Suppress("unused")
    suspend fun showError(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.ERROR))

    private suspend fun showMessage(message: Message) {
        messageChannel.send(message)
    }

}
