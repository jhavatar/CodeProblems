package io.chthonic.codprob.ui.challenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.chthonic.codprob.databinding.SampleHolderBinding
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult

class SampleViewHolder(private val binding: SampleHolderBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun createBinding(parent: ViewGroup): SampleHolderBinding {
            return SampleHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }

    }

    fun bind(result: ChallengeSampleResult) {
        binding.sampleName.text = "${result.sampleIndex} ${if (result.pass) "pass" else "fail"}"
    }

}