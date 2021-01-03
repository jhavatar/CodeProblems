package io.chthonic.codprob.ui.list.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.chthonic.codprob.business.challenge.ChallengeInteractor
import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.ui.common.model.UiModelConverter
import io.chthonic.codprob.ui.common.presentation.BaseViewModel
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import kotlinx.coroutines.*

class ListViewModelImpl @ViewModelInject constructor(
    private val challengeInteractor: ChallengeInteractor,
    private val challengeUiModelConverter: UiModelConverter<@JvmSuppressWildcards Pair<Int, Challenge<*,*>>, ChallengeUiModel>
) : ListViewModel, BaseViewModel() {

    private val _challengesObservable: MutableLiveData<List<ChallengeUiModel>> = MutableLiveData()
    override val challengesObservable: LiveData<List<ChallengeUiModel>>
        get() = _challengesObservable

    @Volatile
    private var lastPhotoImagePath: String? = null

    init {
        // fetch latest at startup
        refreshChallenges()
    }

    private suspend fun getChallenges() {
        withContext(Dispatchers.IO) {
            val challenges = challengeInteractor.getChallenges().mapIndexed { index: Int, challenge: Challenge<*,*> ->
                challengeUiModelConverter.convert(Pair(index, challenge))
            }

            // update liveData
            _challengesObservable.postValue(challenges)
        }
    }

    override fun refreshChallenges() {
        viewModelScope.launch {
            getChallenges()
        }
    }

}
