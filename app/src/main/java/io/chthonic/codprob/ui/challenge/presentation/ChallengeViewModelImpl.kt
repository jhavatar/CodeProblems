package io.chthonic.codprob.ui.challenge.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.chthonic.codprob.business.challenge.ChallengeInteractor
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import io.chthonic.codprob.ui.common.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber

class ChallengeViewModelImpl @ViewModelInject constructor(private val challengeInteractor: ChallengeInteractor) :
    ChallengeViewModel,
    BaseViewModel() {

    private val _resultsObservable: MutableLiveData<List<ChallengeSampleResult>> = MutableLiveData()
    override val resultsObservable: LiveData<List<ChallengeSampleResult>>
        get() = _resultsObservable

    private var _challengeIndex: Int = ChallengeUiModel.INDEX_UNKNOWN

    override fun setChallengeIndex(challengeIndex: Int) {
        if (_challengeIndex != challengeIndex) {
            _challengeIndex = challengeIndex
            solveSamples(_challengeIndex)
        }
    }

    init {
        viewModelScope.launch {
            challengeInteractor.resultsObservable.filter {
                it.challengeIndex == _challengeIndex

            }.collect {
                Timber.v("resultsObservable: $it")
                val resultList = _resultsObservable.value?.toMutableSet() ?: mutableSetOf()
                Timber.v("resultsObservable: resultList = $resultList")
                resultList.add(it)
                _resultsObservable.value = resultList.toMutableList().sortedBy { it.sampleIndex }
            }
        }
    }

    private fun solveSamples(challengeIndex: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            challengeInteractor.solveChallengeSamples(challengeIndex)
        }
    }

}
