package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class SpecialSubstringCount(override val index: Int) : Challenge<String, Int> {

    override val name = "Special Substring Count"

    override val description = """
        A string is said to be a special string if either of two conditions is met:

            All of the characters are the same, e.g. aaa.
            All characters except the middle one are the same, e.g. aadaa.

        A special substring is any substring of a string which meets one of those criteria. Given a string, determine how many special substrings can be formed from it. 
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<String, Int>> by lazy {
        listOf(SpecialSubstringCountSample("asasd", 5), SpecialSubstringCountSample("aaaa", 10))
    }

    override fun solve(input: String): Int {
        val n = input.length
        var specialCount = n

        var idxs = 0
        while (idxs < n - 1) {
            val c = input[idxs]
            val set = mutableSetOf<Char>(c)
            for (idxe in (idxs + 1) until n) {
                val sub = input.substring(idxs, idxe + 1)
                val nSub = sub.length

                val c2 = input[idxe]

                if ((set.contains(c2) && (c2 != c)) || (!set.contains(c2) && (set.size >= 2))) {
                    break
                }

                set.add(c2)

                if (set.size > 2) {
                    break
                }

                if (set.size == 1) {
                    specialCount++
                    Timber.v("idxs = $idxs, idxe = $idxe, sub = $sub, specialCount = $specialCount")

                } else if ((set.size == 2) && (nSub % 2 == 1)) {
                    val same = "c".repeat(nSub / 2)
                    if (sub.substring(0, nSub / 2 + 1) != same) {
                        break // left of center

                    } else if (sub.substring(nSub / 2, nSub) == same) {
                        specialCount++
                        Timber.v("idxs = $idxs, idxe = $idxe, sub = $sub, specialCount = $specialCount")
                    }
                }
            }

            idxs++
        }

        return specialCount
    }

}

class SpecialSubstringCountSample(override val input: String, override val output: Int) : ChallengeSample<String, Int>