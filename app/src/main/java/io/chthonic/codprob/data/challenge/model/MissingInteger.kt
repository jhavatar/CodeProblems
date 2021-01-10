package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class MissingInteger(override val index: Int) : Challenge<IntArray, Int> {

    override val name: String = "Missing Integer"

    override val description: String = """
        Given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

        For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

        Given A = [1, 2, 3], the function should return 4.

        Given A = [−1, −3], the function should return 1.

        Write an efficient algorithm for the following assumptions:

                N is an integer within the range [1..100,000];
                each element of array A is an integer within the range [−1,000,000..1,000,000].

    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<IntArray, Int>> by lazy {
        listOf(
            MissingIntegerSample(intArrayOf(1, 2, 3), 4),
            MissingIntegerSample(intArrayOf(1, 3, 6, 4, 1, 2), 5),
            MissingIntegerSample(intArrayOf(-1, -3), 1)
        )
    }

    override fun solve(input: IntArray): Int {
        val n = input.size
        val intSet = (1..n).toMutableSet()

        // Note, input.toSet() is more expensive than expected

        input.forEach { a ->
            intSet.remove(a)
        }
        return intSet.minOrNull() ?: (n + 1)
    }
}

class MissingIntegerSample(override val input: IntArray, override val output: Int) : ChallengeSample<IntArray, Int>