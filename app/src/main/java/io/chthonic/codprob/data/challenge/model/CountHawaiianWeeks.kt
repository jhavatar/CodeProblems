package io.chthonic.codprob.data.challenge.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.entity.challenge.ChallengeSample

class CountHawaiianWeeks(override val index: Int) : Challenge<CountHawaiianWeeksInput, Int> {
    override val name: String = "Count Hawaiian Weeks"

    override val description: String = """
        Calculate how many weeks employee can spend in hawaii when:
        - is year `year` where 1 january is weekday `jan1WeekDay`
        - has full months holiday starting with month `monthStart' and ending with month `monthEnd`
        - plane to hawaii only leaves on Monday and returns on Sunday
    """.trimIndent()

    override val difficulty: Challenge.Difficulty = Challenge.Difficulty.HARD

    override val samples: List<ChallengeSample<CountHawaiianWeeksInput, Int>> by lazy {
        listOf(
            CountHawaiianWeeksSample(CountHawaiianWeeksInput(2014, "April", "May", "Wednesday"), 7)
        )
    }

    override fun solve(input: CountHawaiianWeeksInput): Int {
        val year = input.year
        val isLeapYear = year % 4 == 0
        val monthStart = input.monthStart
        val monthEnd = input.monthEnd
        val jan1Day = input.jan1WeekDay

        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val daysMap = days.mapIndexed { index, s ->
            s to index
        }.toMap()

        val months =
            listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val monthsMap = months.mapIndexed { index, s ->
            s to index
        }.toMap()
        val monthLength = listOf(
            31, //jan
            if (isLeapYear) 29 else 28, //feb
            31, //mrch
            30, //apr
            31, // may
            30, //june
            31, //july
            31, //aug
            30, //sept
            31, //oct
            30, //nov
            31
        ) //des


        val jan1Index = daysMap[jan1Day]!!

        val monthStartIndex = monthsMap[monthStart]!!
        val monthEndIndex = monthsMap[monthEnd]!!
        println("monthStartIndex = $monthStartIndex, monthEndIndex = $monthEndIndex")

        var holDays = 0
        for (i in monthStartIndex..monthEndIndex) {
            holDays += monthLength[i]
        }
        println("holDays = $holDays")

        var daysUntilBeforeHol = 0
        for (i in 0 until monthStartIndex) {
            //println("monthLength = ${monthLength[i]}")
            daysUntilBeforeHol += monthLength[i]
        }
        println("daysUntilBeforeHol = $daysUntilBeforeHol, if year started on a monday, day = ${daysUntilBeforeHol % 7}")

        val daysUpToFirstDayHol = (daysUntilBeforeHol + 1)

        // NB: convert to index before calculate day of week
        val firstDayOfHolYearIndex = daysUpToFirstDayHol - 1
        val firstDayOfHolWeekIndex = (firstDayOfHolYearIndex + jan1Index) % 7
        println("firstDayOfHolYearIndex = $firstDayOfHolYearIndex, jan1Index = $jan1Index,  firstDayOfHolWeekIndex = $firstDayOfHolWeekIndex, ${days[firstDayOfHolWeekIndex]}")

        val holDepartOffset = if (firstDayOfHolWeekIndex == 0) 0 else 7 - firstDayOfHolWeekIndex
        holDays -= holDepartOffset
        println("holDepartOffset = $holDepartOffset, holDays = $holDays")

        return holDays / 7
    }

}

data class CountHawaiianWeeksInput(val year: Int, val monthStart: String, val monthEnd: String, val jan1WeekDay: String)

class CountHawaiianWeeksSample(override val input: CountHawaiianWeeksInput, override val output: Int) :
    ChallengeSample<CountHawaiianWeeksInput, Int>