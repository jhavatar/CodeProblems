package io.chthonic.codprob.ui.challenge.presentation

import androidx.lifecycle.LiveData
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import kotlinx.coroutines.Deferred

interface ChallengeViewModel {

    val resultsObservable: LiveData<List<ChallengeSampleResult>>

    fun setChallengeIndex(challengeIndex: Int)

}
