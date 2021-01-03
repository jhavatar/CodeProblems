package io.chthonic.codprob.ui.common.model

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

/**
 * Redraw only changed/new values when updating values
 */
fun <T> RecyclerView.Adapter<*>.notifyChanges(oldValues: List<T>, newValues: List<T>) {
    if (oldValues.isEmpty()) {
        notifyItemRangeInserted(0, newValues.size)

    } else if (newValues.isEmpty()) {
        notifyItemRangeRemoved(0, oldValues.size)

    } else {
        val nOld = oldValues.size
        val nNew = newValues.size
        val n = min(nOld, nNew)

        oldValues.forEachIndexed { idx, oldVal ->
            if (idx >= n) return@forEachIndexed

            val newVal = newValues[idx]
            if (newVal != oldVal) {
                // only update if changed
                notifyItemChanged(idx)
            }
        }

        if (nOld > nNew) {
            notifyItemRangeRemoved(n, nOld)

        } else if (nNew > nOld) {
            notifyItemRangeInserted(n, nNew)
        }
    }
}