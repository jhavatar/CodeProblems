package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber
import kotlin.math.max

class Candies(override val index: Int) : Challenge<IntArray, Int> {

    override val name = "Candies"

    override val description = """
        Alice is a kindergarten teacher. She wants to give some candies to the children in her class.  All the children sit in a line and each of them has a rating score according to his or her performance in the class.  Alice wants to give at least 1 candy to each child. If two children sit next to each other, then the one with the higher rating must get more candies. Alice wants to minimize the total number of candies she must buy.
         Note that when two children have equal rating, they are allowed to have different number of candies. 
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<IntArray, Int>> by lazy {
        listOf(
            CandiesSample(intArrayOf(1, 2, 2), 4),
            CandiesSample(intArrayOf(2, 4, 2, 6, 1, 7, 8, 9, 2, 1), 19)
        )
    }

    override fun solve(input: IntArray): Int {
        val NO_VALUE = -1
        val MIN_CANDIES = 1
        val allCandies = IntArray(input.size) { NO_VALUE }

        var idx = 0
        while (idx < input.size) {
            val rating = input[idx]
            var candies = MIN_CANDIES

            if (idx > 0) {
                val lRating = input[idx - 1]
                val lCandies = allCandies[idx - 1]
                if (rating > lRating) {
                    candies = lCandies + 1
                }
            }
            Timber.v("idx = $idx, after l candies = $candies")

            if ((idx < input.size - 1) && (allCandies[idx + 1] != NO_VALUE)) {
                val rRating = input[idx + 1]
                val rCandies = allCandies[idx + 1]
                if (rating > rRating) {
                    candies = max(candies, rCandies + 1)
                }
            }
            Timber.v("idx = $idx, after r candies = $candies")

            allCandies[idx] = candies

            if ((idx > 0) && (rating < input[idx - 1]) && (candies >= allCandies[idx - 1])) {
                idx--
            } else {
                idx++
            }
        }

        Timber.v("allCandies = ${allCandies.toList()}")
        return allCandies.sum()
    }

}

class CandiesSample(override val input: IntArray, override val output: Int) : ChallengeSample<IntArray, Int>