package io.chthonic.codprob.ui.common.model

import androidx.annotation.StringRes

data class Message(@StringRes val stringRes: Int, val type: MessageType)

enum class MessageType {
    INFO, WARNING, ERROR
}
