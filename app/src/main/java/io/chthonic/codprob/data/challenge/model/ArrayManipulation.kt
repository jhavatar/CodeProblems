package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class ArrayManipulation(override val index: Int) : Challenge<ArrayManipulationInput, Int> {

    override val name = "Array Manipulation"

    override val description = """Starting with a 1-indexed array of zeros and a list of operations, 
        for each operation add a value to each of the array element between two given indices, inclusive. 
        Once all operations have been performed, return the maximum value in the array. 
        """

    override val difficulty = Challenge.Difficulty.HARD

    override val samples: List<ChallengeSample<ArrayManipulationInput, Int>>
        get() = listOf(ArrayManipulationSample_10_3(), ArrayManipulationSample_5_3())

    override fun solve(input: ArrayManipulationInput): Int {
        val arr = IntArray(input.arrayLength, { 0 })

        input.manipulations.forEach { manip ->
            val value = manip[2]
            val startIndex = manip[0] - 1
            val endIndexInclusive = manip[1] - 1
            for (idx in startIndex..endIndexInclusive) {
                arr[idx] = arr[idx] + value
            }
        }

        return arr.maxOrNull() ?: 0
    }

}

data class ArrayManipulationInput(val arrayLength: Int, val manipulations: Array<IntArray>)

class ArrayManipulationSample_10_3 : ChallengeSample<ArrayManipulationInput, Int> {
    override val input: ArrayManipulationInput = ArrayManipulationInput(
        10,
        arrayOf(
            intArrayOf(1, 5, 3),
            intArrayOf(4, 8, 7),
            intArrayOf(6, 9, 1)
        )
    )
    override val output = 10
}

class ArrayManipulationSample_5_3 : ChallengeSample<ArrayManipulationInput, Int> {
    override val input: ArrayManipulationInput = ArrayManipulationInput(
        5,
        arrayOf(
            intArrayOf(1, 2, 100),
            intArrayOf(2, 5, 100),
            intArrayOf(3, 4, 100)
        )
    )
    override val output = 200
}