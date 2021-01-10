package io.chthonic.codprob.entity.challenge

import androidx.annotation.WorkerThread

interface Challenge<Input, Output> {

    enum class Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    val index: Int

    val name: String

    val description: String

    val difficulty: Difficulty

    val samples: List<ChallengeSample<Input, Output>>

    @WorkerThread
    fun solve(input: Input): Output

    @WorkerThread
    fun solveSample(sampleIndex: Int): ChallengeSampleResult {
        val sample = samples[sampleIndex]
        val tStart = System.currentTimeMillis()
        val output = solve(sample.input)
        val duration = System.currentTimeMillis() - tStart
        return ChallengeSampleResult(
            challengeIndex = index,
            sampleIndex = sampleIndex,
            pass = sample.passed(output),
            durationInMillis = duration
        )
    }

    @WorkerThread
    fun solveSamples(): List<ChallengeSampleResult> {
        val results = mutableListOf<ChallengeSampleResult>()
        samples.forEachIndexed { index, challengeSample ->
            results.add(solveSample(index))
        }
        return results
    }

}