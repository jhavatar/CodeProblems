package io.chthonic.codprob.ui.common.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import io.chthonic.codprob.R
import io.chthonic.codprob.ui.common.model.Message
import io.chthonic.codprob.ui.common.model.MessageType
import io.chthonic.codprob.ui.common.presentation.BaseViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    protected abstract val coordLayout: CoordinatorLayout

    protected abstract val viewModel: BaseViewModel

    @Suppress("unused")
    fun showInfo(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.INFO))

    @Suppress("unused")
    fun showWarning(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.WARNING))

    @Suppress("unused")
    fun showError(@StringRes stringRes: Int) = showMessage(Message(stringRes, MessageType.ERROR))

    private fun showMessage(message: Message) {
        val length = when (message.type) {
            MessageType.INFO -> Snackbar.LENGTH_SHORT
            else -> Snackbar.LENGTH_LONG
        }
        val textColor = when (message.type) {
            MessageType.INFO -> null
            MessageType.ERROR -> R.color.error
            MessageType.WARNING -> R.color.warn
        }

        Snackbar
            .make(coordLayout, message.stringRes, length)
            .run {
                textColor?.let {
                    setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            it,
                            requireContext().theme
                        )
                    )

                } ?: this
            }
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.messageObservable.consumeEach {
                showMessage(it)
            }
        }
    }

}
