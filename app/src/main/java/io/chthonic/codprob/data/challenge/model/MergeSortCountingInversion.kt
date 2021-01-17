package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class MergeSortCountingInversion(override val index: Int) : Challenge<IntArray, Long> {

    override val name: String = "Merge Sort: Counting Inversion"

    override val description: String = """
        https://www.hackerrank.com/challenges/ctci-merge-sort/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
        
        In an array, the elements at indices i and j (where i < j ) form an inversion if arr[i] > arr[j]. 
        Inverted elements arr[i] and arr[j] are considered to be "out of order".
        To correct an inversion, we can swap adjacent elements.
        
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.HARD

    override val samples: List<ChallengeSample<IntArray, Long>> by lazy {
        listOf(
            MergeSortCountingInversionSample(intArrayOf(2, 4, 1), 2L),
            MergeSortCountingInversionSample(intArrayOf(7, 5, 3, 1), 4L),
            MergeSortCountingInversionSample(intArrayOf(1, 1, 1, 2, 2), 0L),
            MergeSortCountingInversionSample(intArrayOf(2, 1, 3, 1, 2), 4L)
        )
    }


    var swopCount = 0L

    private fun merge(left: List<Int>, right: List<Int>): List<Int> {
        Timber.v("merge: left = $left, right = $right")
        var indexLeft = 0
        var indexRight = 0
        var newList: MutableList<Int> = mutableListOf()

        while ((indexLeft < left.size) && (indexRight < right.size)) {
            val leftVal = left[indexLeft]
            val rightVal = right[indexRight]

            if (leftVal <= rightVal) {
                newList.add(leftVal)
                indexLeft++

            } else {
                swopCount++
                Timber.v("swop!, $leftVal > $rightVal, count = $swopCount")
                newList.add(rightVal)
                indexRight++

            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }

        Timber.v("merge: newList = $newList")
        return newList
    }

    private fun mergeSort(input: List<Int>): List<Int> {
        Timber.v("mergeSort: input = $input")

        if (input.size == 1) {
            return input
        }

        val middle = input.size / 2
        var left = input.subList(0, middle)
        var right = input.subList(middle, input.size)

        return merge(mergeSort(left), mergeSort(right))
    }


    override fun solve(input: IntArray): Long {
        Timber.v("input = $input")

        swopCount = 0L
        val sorted = mergeSort(input.toList())
        Timber.v("sorted = $sorted")
        return swopCount
    }

}

data class MergeSortCountingInversionSample(override val input: IntArray, override val output: Long) : ChallengeSample<IntArray, Long>
