package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class RepeatedString(override val index: Int) : Challenge<RepeatedStringInput, Long> {
    override val name = "Repeated String"

    override val description = """
        There is a string, , of lowercase English letters that is repeated infinitely many times. Given an integer, n, find and print the number of letter a's in the first n letters of the infinite string.
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<RepeatedStringInput, Long>> by lazy {
        listOf(
            RepeatedStringSample(RepeatedStringInput("aba", 10), 7),
            RepeatedStringSample(RepeatedStringInput("a", 1000000000000), 1000000000000)
        )
    }

    override fun solve(input: RepeatedStringInput): Long {
        val s = input.s
        val n = input.n

        var aCount: Long
        if (n > s.length) {
            aCount = s.filter {
                it == 'a'
            }.count() * (n / s.length)

            if (n % s.length != 0L) {
                val s2 = s.substring(0, (n % s.length).toInt())
                aCount += s2.filter {
                    it == 'a'
                }.count()
            }

        } else {
            val s2 = s.substring(0, n.toInt())
            aCount = s2.filter {
                it == 'a'
            }.count().toLong()
        }

        return aCount
    }

}

data class RepeatedStringInput(val s: String, val n: Long)

class RepeatedStringSample(override val input: RepeatedStringInput, override val output: Long) : ChallengeSample<RepeatedStringInput, Long>
