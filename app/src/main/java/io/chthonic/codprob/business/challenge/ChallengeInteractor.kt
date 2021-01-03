package io.chthonic.codprob.business.challenge

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSampleResult
import kotlinx.coroutines.flow.SharedFlow

interface ChallengeInteractor {

    val resultsObservable: SharedFlow<ChallengeSampleResult>

    fun getChallenges() : List<Challenge<*, *>>

    suspend fun solveChallengeSamples(index: Int)

}