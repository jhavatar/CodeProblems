package io.chthonic.codprob.business.challenge

import io.chthonic.codprob.data.challenge.ChallengesRepo
import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber
import javax.inject.Inject

class ChallengeInteractorImpl @Inject constructor(
    private val challengesRepo: ChallengesRepo,
) : ChallengeInteractor {

    private val resultsChannel = MutableSharedFlow<ChallengeSampleResult>()

    override val resultsObservable: SharedFlow<ChallengeSampleResult>
        get() = resultsChannel

    override fun getChallenges(): List<Challenge<*, *>> {
        return challengesRepo.getChallenges()
    }

    private fun getChallenge(index: Int) : Challenge<*, *> {
        return getChallenges()[index]
    }

    override suspend fun solveChallengeSamples(index: Int) {
        val challenge = getChallenge(index)
        challenge.samples.forEachIndexed { index, challengeSample ->
            val result = challenge.solveSample(index)
            Timber.v("solveSamples: result = $result")
            resultsChannel.emit(result)
        }
    }

}