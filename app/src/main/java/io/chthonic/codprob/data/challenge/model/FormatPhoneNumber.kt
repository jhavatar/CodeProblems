package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class FormatPhoneNumber(override val index: Int) : Challenge<String, String> {

    override val name: String = "Format Phone Number"

    override val description: String = """
        Given a phone number string consisting of: at least 2 numbers,  spaces (" ") and "-".
        Format number:
        - into groups of numbers separated by "-"
        - 2 numbers in a group allowed for last two groups
        - 3 numbers in a group otherwise
        - 1 number in a group is not allowed
        
        
        
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.EASY

    override val samples: List<ChallengeSample<String, String>> by lazy {
        listOf(
            FormatPhoneNumberSample("00-44  48 5555 8361", "004-448-555-583-61"), // 14
            FormatPhoneNumberSample("0 - 22 1985--324", "022-198-53-24") // 10)
        )
    }

    override fun solve(input: String): String {
        val GROUP_SIZE = 3

        val digits = input.filter { c ->
            c.isDigit()
        }

        val result = StringBuffer("")
        val secondLastGroupHasTwo = digits.length % GROUP_SIZE == 1
        val n = digits.length

        var groupCount = 0
        digits.forEachIndexed { idx, c ->
            if ((groupCount == GROUP_SIZE) || (secondLastGroupHasTwo && (idx == n - 2))) {
                groupCount = 0
                result.append("-")
            }

            result.append(c)
            groupCount++
        }

        return result.toString()
    }

}

class FormatPhoneNumberSample(override val input: String, override val output: String) : ChallengeSample<String, String>
