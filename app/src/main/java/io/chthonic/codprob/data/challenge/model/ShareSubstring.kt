package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class ShareSubstring(override val index: Int) : Challenge<ShareSubstringInput, Boolean> {

    override val name = "Share Substring"

    override val description =
        """Given two strings, determine if they share a common substring. A substring may be as small as one character. """

    override val difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<ShareSubstringInput, Boolean>> by lazy {
        listOf(
            ShareSubstringSample(ShareSubstringInput("hello", "world"), true),
            ShareSubstringSample(ShareSubstringInput("hi", "world"), false)
        )
    }

    override fun solve(input: ShareSubstringInput): Boolean {
        val s1 = input.s1
        val s2 = input.s2

        val s1Set = s1.toSet()
        val s2Set = s2.toSet()
        return s1Set.intersect(s2Set).isNotEmpty()
    }

}

data class ShareSubstringInput(val s1: String, val s2: String)

class ShareSubstringSample(override val input: ShareSubstringInput, override val output: Boolean) :
    ChallengeSample<ShareSubstringInput, Boolean>