package io.chthonic.codprob.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChallengeUiModel(
    val index: Int,
    val name: String,
    val description: String
) : Parcelable {

    companion object {
        const val INDEX_UNKNOWN = -1
    }

}