package io.chthonic.codprob.ui.common.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearItemDecorator(private val spacingPx: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = spacingPx
        outRect.bottom = 0
    }

}
