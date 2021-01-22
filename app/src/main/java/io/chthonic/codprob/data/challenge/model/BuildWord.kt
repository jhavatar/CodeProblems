package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class BuildWord(override val index: Int) : Challenge<List<String>, String> {

    override val name: String = "Build Word"

    override val description: String = """
        Calculate the word specified by input list.
        Each input list instance specifies 2 letters that are next to each other with code "A>B" which means A is left of B right next to it.
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<List<String>, String>> by lazy {
        listOf(
            BuildWordSample(listOf("P>E", "E>R", "R>U"), "PERU"),
            BuildWordSample(listOf("I>N", "A>I", "P>A", "S>P"), "SPAIN"),
            BuildWordSample(listOf("U>N", "G>A", "R>Y", "H>U", "N>G", "A>R"), "HUNGARY"),
            BuildWordSample(listOf("I>F", "W>I", "S>W", "F>T"), "SWIFT"),
            BuildWordSample(listOf("R>T", "A>L", "P>O", "O>R", "G>A", "T>U", "U>G"), "PORTUGAL"),
            BuildWordSample(listOf("W>I", "R>L", "T>Z", "Z>E", "S>W", "E>R", "L>A", "A>N", "N>D", "I>T"), "SWITZERLAND")
        )
    }

    override fun solve(input: List<String>): String {
        val nextMap = mutableMapOf<Char, Char>()
        val prevMap = mutableMapOf<Char, Char>()

        var firstChar: Char? = null
        input.forEach {
            val left = it[0]
            val right = it[2]
            nextMap[left] = right
            prevMap[right] = left

            if (firstChar == null) {
                firstChar = left

            } else if (firstChar == right) {
                firstChar = left
            }
        }

        if (firstChar == null) return ""

        // verify correct firstChar
        var prevChar: Char? = firstChar
        while (prevChar != null) {
            val left = prevMap[prevChar]
            if (left != null) {
                firstChar = left
            }

            prevChar = left
        }
        Timber.v("firstChar = $firstChar")

        var word = "$firstChar"
        var nextChar: Char? = firstChar
        while (nextChar != null) {
            val left = nextChar
            val right = nextMap[left]
            Timber.v("left = $left, right = $right, word = $word")

            if (right != null) {
                word = word + right
            }
            nextChar = right
        }

        Timber.v("word = $word")
        return word
    }

}

data class BuildWordSample(override val input: List<String>, override val output: String) : ChallengeSample<List<String>, String>
