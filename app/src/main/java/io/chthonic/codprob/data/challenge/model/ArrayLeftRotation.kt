package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class ArrayLeftRotation(override val index: Int) : Challenge<ArrayLeftRotationInput, IntArray> {

    override val difficulty = Challenge.Difficulty.EASY

    override val name: String = "Arrays: Left Rotation"

    override val description: String = """
        A left rotation operation on an array shifts each of the array's elements unit to the left. 
        Note that the lowest index item moves to the highest index in a rotation. This is called a circular array.
        """

    override val samples: List<ChallengeSample<ArrayLeftRotationInput, IntArray>> by lazy {
        listOf(ArrayLeftRotationSample54())
    }

    override fun solve(input: ArrayLeftRotationInput): IntArray {
        val array = input.array
        val n = array.size
        val nRotations = input.leftRotations

        var arrNew = array
        for (i in 0 until nRotations) {
            val arrOld = arrNew
            arrNew = IntArray(n) { 0 }
            arrOld.forEachIndexed { idx, value ->
                val newIdx = idx - 1
                arrNew[if (newIdx < 0) n - 1 else newIdx] = value
            }
        }

        return arrNew
    }

}

data class ArrayLeftRotationInput(val array: IntArray, val leftRotations: Int)

class ArrayLeftRotationSample54 : ChallengeSample<ArrayLeftRotationInput, IntArray> {
    override val input: ArrayLeftRotationInput = ArrayLeftRotationInput(array = intArrayOf(1, 2, 3, 4, 5), leftRotations = 4)
    override val output: IntArray = intArrayOf(5, 1, 2, 3, 4)

    override fun passed(actualOutput: IntArray): Boolean = output contentEquals actualOutput
}