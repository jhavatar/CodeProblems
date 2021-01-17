package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class SherlockAndValidString(override val index: Int) : Challenge<String, Boolean> {

    override val name: String = "Sherlock and Valid String"

    override val description: String = """
        Sherlock considers a string to be valid if all characters of the string appear the same number of times. It is also valid if he can remove just character at index in the string, and the remaining characters will occur the same number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<String, Boolean>> by lazy {
        listOf(
            SherlockAndValidStringSample("abc", true),
            SherlockAndValidStringSample("abcc", true),
            SherlockAndValidStringSample("abccc", false),
            SherlockAndValidStringSample("aabbcd", false),
            SherlockAndValidStringSample("aabbccddeefghi", false),
            SherlockAndValidStringSample("abcdefghhgfedecba", true)
        )
    }

    override fun solve(input: String): Boolean {
        Timber.v("input = $input")
        val charCountMap = mutableMapOf<Char, Int>()
        input.forEach { c ->
            charCountMap[c] = (charCountMap[c] ?: 0) + 1
        }
        Timber.v("charCount = $charCountMap")

        val countMap = mutableMapOf<Int, Int>()
        charCountMap.values.forEach {
            countMap[it] = (countMap[it] ?: 0) + 1
        }

        Timber.v("countMap = $countMap")
        if (countMap.size == 1) return true

        val maxKey = countMap.keys.maxOrNull() ?: 0
        val minKey = countMap.keys.minOrNull() ?: 0

        return (countMap.size == 2) && (maxKey - minKey == 1) && ((countMap[maxKey] == 1) || (countMap[minKey] == 1))
    }

}

data class SherlockAndValidStringSample(override val input: String, override val output: Boolean) : ChallengeSample<String, Boolean>
