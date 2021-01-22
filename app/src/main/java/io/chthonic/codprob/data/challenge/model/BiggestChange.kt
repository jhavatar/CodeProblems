package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class BiggestChange(override val index: Int) : Challenge<BiggestChangeInput, List<Int>> {
    override val name: String = "Biggest Change"

    override val description: String = """
        Get the change in biggest denomination when buy something with money M that costs price P.
        
        All the possible change denominations are:
        1c, 5c, 10c, 25c, 50c, $1
        
        Return list where at index of denomination is the number of times it occurs in the change. Want most bigger denominations in the change.
        
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<BiggestChangeInput, List<Int>>> by lazy {
        listOf(
            BiggestChangeSample(BiggestChangeInput(5.0, 0.99), listOf(1, 0, 0, 0, 4))
//println("output = " + getChange(3.0, 0.01))
//println("output = " + getChange(4.0, 3.14))
//println("output = " + getChange(0.45, 0.34))
        )
    }

    override fun solve(input: BiggestChangeInput): List<Int> {
        val CHANGE = listOf(1, 5, 25, 50, 100)
        val CHANGE_REVERSED = CHANGE.reversed()
        val money = (input.money * 100).toInt()
        val price = (input.price * 100).toInt()

        var remain = money - price
        val output = mutableListOf<Int>(0, 0, 0, 0, 0)
        CHANGE_REVERSED.forEachIndexed { idx, value ->
            if (remain == 0) return@forEachIndexed

            val x = remain / value
            if (x >= 1) { // at least 1 instance of value
                output[idx] = x
                remain = remain - value * x
            }

        }

        return output.reversed()
    }

}

data class BiggestChangeInput(val money: Double, val price: Double)

data class BiggestChangeSample(override val input: BiggestChangeInput, override val output: List<Int>) :
    ChallengeSample<BiggestChangeInput, List<Int>>
