package io.chthonic.codprob.ui.list.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import io.chthonic.codprob.ui.common.model.notifyChanges
import kotlin.math.min

class ChallengeListAdapter : RecyclerView.Adapter<ChallengeViewHolder>() {

    var challenges: List<ChallengeUiModel> = emptyList()
        set(value) {
            if (field != value) {
                val oldValues = field
                field = value
                notifyChanges(oldValues, value)
            }
        }

    var onClickListener: ((ChallengeUiModel) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        return ChallengeViewHolder(ChallengeViewHolder.createBinding(parent))
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = challenges[position]
        holder.bind(challenge)

        holder.clickView.setOnClickListener {
            onClickListener?.invoke(challenge)
        }
    }

    override fun getItemCount(): Int {
        return challenges.size
    }

}