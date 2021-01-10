package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample
import timber.log.Timber

class SherlockAndAnagrams(override val index: Int) : Challenge<String, Int> {

    override val name = "Sherlock and Anagrams"

    override val description = """
        Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string. Given a string, find the number of pairs of substrings of the string that are anagrams of each other. 
    """.trimIndent()

    override val difficulty = Challenge.Difficulty.MEDIUM

    override val samples: List<ChallengeSample<String, Int>> by lazy {
        listOf(
            SherlockAndAnagramsSample("abba", 4),
            SherlockAndAnagramsSample("abcd", 0)
        )
    }

    override fun solve(input: String): Int {
        val n = input.length
        val nMaxAnagram = n - 1 // allow anagram overlap
        var count = 0

        Timber.v("nMaxAnagram = $nMaxAnagram")
        for (nAnagram in 1..nMaxAnagram) { // consider substring on length nAnagram
            Timber.v("nAnagram = $nAnagram")
            for (idx1 in 0..(n - (nAnagram + 1))) { // compare substring starting at idx1
                val s1 = input.substring(idx1, idx1 + nAnagram)
                Timber.v("idx1 = $idx1, s1 = $s1")
                for (idx2 in (idx1 + 1)..(n - nAnagram)) { // to substring starting at idx2
                    val s2 = input.substring(idx2, idx2 + nAnagram).reversed()
                    Timber.v("idx2 = $idx2, s2 = $s2")

                    if (s1 == s2) {
                        Timber.v("anagrams idx1 = $idx1, s1 = $s1, idx2 = $idx2, s2 = $s2")
                        count++
                    }
                }
            }
        }

        return count
    }
}

class SherlockAndAnagramsSample(override val input: String, override val output: Int) :
    ChallengeSample<String, Int>