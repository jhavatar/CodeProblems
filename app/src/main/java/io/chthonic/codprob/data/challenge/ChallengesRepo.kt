package io.chthonic.codprob.data.challenge

import io.chthonic.codprob.entity.challenge.Challenge

interface ChallengesRepo {

    fun getChallenges(): List<Challenge<*, *>>

}