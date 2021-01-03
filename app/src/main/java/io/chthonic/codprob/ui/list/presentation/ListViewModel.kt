package io.chthonic.codprob.ui.list.presentation

import androidx.lifecycle.LiveData
import io.chthonic.codprob.ui.common.model.ChallengeUiModel

interface ListViewModel {

    /**
     * Provides current list of [ChallengeUiModel]
     */
    val challengesObservable: LiveData<List<ChallengeUiModel>>

    /**
     * Refresh [ChallengeUiModel] list provided by [challengesObservable]
     */
    fun refreshChallenges()

}
