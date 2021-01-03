package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.data.challenge.sample.FizzBuzz20
import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class FizzBuzz(override val index: Int) : Challenge<List<Int>, List<String>> {

    companion object {
        const val  FIZZ = "FIZZ"
        const val BUZZ = "BUZZ"
    }

    override val name: String = "FizzBuzz"
    override val description: String = """Write a program that prints the numbers from 1 to 100. 
        |But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”. 
        |For numbers which are multiples of both three and five print “FizzBuzz”."""

    override val samples: List<ChallengeSample<List<Int>, List<String>>> by lazy {
        listOf(FizzBuzz20())
    }

    override fun solve(input: List<Int>): List<String> {
        return input.map {
            val fizz = it % 3 == 0
            val buzz = it % 5 == 0

            when {
                fizz && buzz -> "$FIZZ$BUZZ"
                fizz -> FIZZ
                buzz -> BUZZ
                else -> it.toString()
            }
        }
    }

}