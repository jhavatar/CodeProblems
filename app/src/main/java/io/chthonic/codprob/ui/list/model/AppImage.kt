package io.chthonic.codprob.ui.list.model

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.Deferred

abstract class AppImage(val filePath: String) {

    companion object {
        const val TRANSITION_PREFIX = "image_"
    }

    val transitionName = TRANSITION_PREFIX + filePath

    val title: String by lazy {
        Uri.parse(filePath).lastPathSegment ?: filePath
    }

    abstract fun getBitmapAsync(): Deferred<Bitmap>

    override fun toString(): String {
        return "${AppImage::class.java.simpleName}:$filePath"
    }

    override fun hashCode(): Int {
        return filePath.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return (other is AppImage) && (other.filePath == filePath)
    }

}
