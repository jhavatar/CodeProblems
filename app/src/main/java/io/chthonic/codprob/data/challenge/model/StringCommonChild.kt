package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class StringCommonChild(override val index: Int) : Challenge<StringCommonChildInput, String> {

    override val name = "String Common Child"

    override val description = """
        A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?

        For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be formed by eliminating either the D or C from both strings. Note that we will not consider ABCD as a common child because we can't rearrange characters and ABCD != ABDC. 
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<StringCommonChildInput, String>> by lazy {
        listOf(
            StringCommonChildSample(StringCommonChildInput("HARRY", "SALLY"), "AY"),
            StringCommonChildSample(StringCommonChildInput("SHINCHAN", "NOHARAAA"), "NHA"),
        )
    }

    override fun solve(input: StringCommonChildInput): String {
        val s1 = input.s1
        val s2 = input.s2
        val n = s1.length

        var maxChild = ""
        s1.indices.forEach { idx ->
            var idx1 = idx
            var idx2 = 0
            var child = ""
            Timber.v("idx = $idx, idx1 = $idx1, idx2 = $idx2")
            while ((idx1 < n) && (idx2 < n - 1)) {
                val c1: Char = s1.get(idx1)
                val s2Start = idx2
                val s2Sub = s2.substring(s2Start)
                val s2SubIdx = s2Sub.indexOf(c1)
                if (s2SubIdx >= 0) { // does contain
                    idx2 = s2Start + s2SubIdx
                    child += c1
                    Timber.v("idx = $idx, idx1 = $idx1, idx2 = $idx2, child = $child, maxChild = $maxChild")
                    if (child.length > maxChild.length) {
                        maxChild = child
                    }
                }
                idx1++
            }
        }

        return maxChild
    }

}

data class StringCommonChildInput(val s1: String, val s2: String)

class StringCommonChildSample(
    override val input: StringCommonChildInput,
    override val output: String
) : ChallengeSample<StringCommonChildInput, String> {

    override fun passed(actualOutput: String): Boolean {
        Timber.v("passed: actualOutput = $actualOutput, output = $output")
        return output.length == actualOutput.length // might be multiple options
    }

}