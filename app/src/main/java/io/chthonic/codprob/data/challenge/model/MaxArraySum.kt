package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class MaxArraySum(override val index: Int) : Challenge<IntArray, Int> {

    override val name = "Max Array Sum"

    override val description = """
        Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset. It is possible that the maximum sum is 0, the case when all elements are negative. 
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<IntArray, Int>> by lazy {
        listOf(
            MaxArraySumSample(intArrayOf(3, 7, 4, 6, 5), 13),
            MaxArraySumSample(intArrayOf(2, 1, 5, 8, 4), 11),
            MaxArraySumSample(intArrayOf(3, 5, -7, 8, 10), 15)
        )
    }

    override fun solve(input: IntArray): Int {
        val subsets = mutableListOf<List<Int>>()
        val n = input.size

        // all possible variations of length n is 2**n
        for (i in 0 until (1 shl n)) {

            // A number i doesn't contain
            // consecutive set bits if
            // bitwise and of i and left
            // shifted i do't contain a
            // commons set bit.
            if (i and (i shl 1) == 0) {

                val subset = mutableListOf<Int>()
                for (j in 0 until n) {

                    // (1<<j) is a number with jth bit 1
                    // so when we 'and' them with the
                    // subset number we get which numbers
                    // are present in the subset and which
                    // are not
                    if (i and (1 shl j) > 0) {
                        subset.add(input[j])
                    }
                }
                Timber.v("i = $i, subset = $subset")
                if (subset.isNotEmpty()) {
                    subsets.add(subset)
                }
            }
        }

        Timber.v("subsets = $subsets")

        val subSetMax = subsets.maxOfOrNull {
            it.sum()
        }

        return (subSetMax ?: 0).let {
            if (it < 0) 0 else it
        }
    }

}

class MaxArraySumSample(override val input: IntArray, override val output: Int) : ChallengeSample<IntArray, Int>