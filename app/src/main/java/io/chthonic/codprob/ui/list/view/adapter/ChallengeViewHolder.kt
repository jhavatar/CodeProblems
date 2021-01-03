package io.chthonic.codprob.ui.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.chthonic.codprob.databinding.ChallengeHolderBinding
import io.chthonic.codprob.ui.common.model.ChallengeUiModel

class ChallengeViewHolder(private val binding: ChallengeHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun createBinding(parent: ViewGroup): ChallengeHolderBinding {
            return ChallengeHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }

    }

    /**
     * View that accepts clicks
     */
    val clickView: View
        get() = binding.challengeName

    fun bind(challenge: ChallengeUiModel) {
        binding.challengeName.text = "${challenge.index} ${challenge.name}"
    }

}