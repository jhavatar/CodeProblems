package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class FindContact(override val index: Int) : Challenge<FindContactInput, String> {

    override val name: String = "Find Contact"

    override val description: String = """
        Given two arrays of size N, contacts and their numbers -- find contact with number that contains substring `numberSubString`.
        
        - all contact strings are lower case alphabet
        - numbers string are just numbers
        - If no match is found, return "NO CONTACT"
        - If multiple matches are found -- return first alphabetical result
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<FindContactInput, String>> by lazy {
        listOf(
            FindContactSample(FindContactInput(arrayOf("pim", "pom"), arrayOf("999999999", "777888999"), "88999"), "pom"),
            FindContactSample(
                FindContactInput(
                    arrayOf("sander", "amy", "ann", "michael"),
                    arrayOf("123456789", "234567890", "789123456", "123123123"),
                    "1"
                ),
                "ann"
            )
        )
    }

    override fun solve(input: FindContactInput): String {
        val FALLBACK = "NO CONTACT"
        val names = input.contacts
        val numbers = input.numbers
        val numberSubString = input.numberSubString

        val matches = mutableListOf<String>()
        numbers.forEachIndexed { index, s ->
            if (s.contains(numberSubString)) {
                matches.add(names[index])
            }
        }

        return if (matches.isEmpty()) {
            FALLBACK
        } else {
            matches.sort()
            matches.first()
        }
    }
}

data class FindContactInput(val contacts: Array<String>, val numbers: Array<String>, val numberSubString: String)

class FindContactSample(override val input: FindContactInput, override val output: String) : ChallengeSample<FindContactInput, String>