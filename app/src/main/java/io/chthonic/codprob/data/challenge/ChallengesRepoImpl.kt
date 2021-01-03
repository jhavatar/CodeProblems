package io.chthonic.codprob.data.challenge

import io.chthonic.codprob.data.challenge.model.FizzBuzz
import io.chthonic.codprob.entity.challenge.Challenge
import javax.inject.Inject

class ChallengesRepoImpl @Inject constructor(): ChallengesRepo {

    private val challengeList: List<Challenge<*, *>> by lazy {
        var idx = 0
        listOf(FizzBuzz(idx++))
    }

    override fun getChallenges(): List<Challenge<*, *>> = challengeList

}