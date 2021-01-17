package io.chthonic.codprob.data.challenge

import io.chthonic.codprob.data.challenge.model.*
import io.chthonic.codprob.entity.challenge.Challenge
import javax.inject.Inject

class ChallengesRepoImpl @Inject constructor() : ChallengesRepo {

    private val challengeList: List<Challenge<*, *>> by lazy {
        var idx = 0
        listOf(
            FizzBuzz(idx++),
            ArrayLeftRotation(idx++),
            ArrayManipulation(idx++),
            AlternatingCharacters(idx++),
            StringCommonChild(idx++),
            SpecialSubstringCount(idx++),
            ShareSubstring(idx++),
            SherlockAndAnagrams(idx++),
            MaxArraySum(idx++),
            Candies(idx++),
            RepeatedString(idx++),
            MissingInteger(idx++),
            BinaryGap(idx++),
            OddOccurrencesInArray(idx++),
            FindContact(idx++),
            FormatPhoneNumber(idx++),
            CountHawaiianWeeks(idx++),
            SherlockAndValidString(idx++),
            MergeSortCountingInversion(idx++)
        )
    }

    override fun getChallenges(): List<Challenge<*, *>> = challengeList

}
