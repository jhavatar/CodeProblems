package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class AlternatingCharacters(override val index: Int) : Challenge<String, Int> {
    override val name = "Alternating Characters"
    override val description = """
        You are given a string containing characters A and B only. Your task is to change it into a string such that there are no matching adjacent characters. To do this, you are allowed to delete zero or more characters in the string.

        Your task is to find the minimum number of required deletions.
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<String, Int>> by lazy {
        listOf(
            AlternatingCharactersSample("AAAA", 3),
            AlternatingCharactersSample("BBBBB", 4),
            AlternatingCharactersSample("ABABABAB", 0),
            AlternatingCharactersSample("BABABA", 0),
            AlternatingCharactersSample("AAABBB", 4)
        )

    }

    override fun solve(input: String): Int {
        var deletionCount = 0
        var lastChar: Char? = null
        var minString = ""

        input.forEach { c ->
            if (c == lastChar) {
                deletionCount++

            } else {
                lastChar = c
                minString += c
            }
        }

        Timber.v("minString = $minString")
        return deletionCount
    }

}

class AlternatingCharactersSample(override val input: String, override val output: Int) : ChallengeSample<String, Int>