package io.chthonic.codprob.ui.challenge.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import io.chthonic.codprob.ui.common.model.notifyChanges
import timber.log.Timber

class SampleListAdapter : RecyclerView.Adapter<SampleViewHolder>() {

    var results: List<ChallengeSampleResult> = emptyList()
        set(value) {
            if (field != value) {
                Timber.v("results = $value")
                val oldValues = field
                field = value
                notifyChanges(oldValues, value)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(SampleViewHolder.createBinding(parent))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }

}