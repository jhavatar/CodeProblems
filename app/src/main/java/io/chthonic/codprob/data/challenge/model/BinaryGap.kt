package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class BinaryGap(override val index: Int) : Challenge<Int, Int> {

    override val name: String = "Binary Gap"

    override val description: String = """
        A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

        For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

        Write a function:

            fun solution(N: Int): Int

        that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

        For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

        Write an efficient algorithm for the following assumptions:

                N is an integer within the range [1..2,147,483,647].

    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<Int, Int>> by lazy {
        listOf(
            BinaryGapSample(1041, 5),
            BinaryGapSample(32, 0)
        )
    }

    override fun solve(input: Int): Int {
        val binary = Integer.toBinaryString(input) //input.toString(2)
        Timber.v("binary = $binary")
        var gapCount = 0
        var gapCountMax = 0
        binary.forEach { c ->
            if (c == '0') {
                gapCount++
            } else {
                if (gapCount > gapCountMax) {
                    gapCountMax = gapCount
                }
                gapCount = 0
            }
        }

        return gapCountMax
    }

}

class BinaryGapSample(override val input: Int, override val output: Int) : ChallengeSample<Int, Int>