package io.chthonic.codprob.data.challenge.sample

import io.chthonic.codprob.entity.challenge.ChallengeSample

class FizzBuzz20 : ChallengeSample<List<Int>, List<String>> {

    override val input: List<Int> by lazy {
        (1 .. 20).toList()
    }

    override val output: List<String> = listOf("1",
        "2",
        "FIZZ",
        "4",
        "BUZZ",
        "FIZZ",
        "7",
        "8",
        "FIZZ",
        "BUZZ",
        "11",
        "FIZZ",
        "13",
        "14",
        "FIZZBUZZ",
        "16",
        "17",
        "FIZZ",
        "19",
        "BUZZ")

}