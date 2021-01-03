package io.chthonic.codprob.entity.challenge

import timber.log.Timber

interface ChallengeSample<Input, Output> {

    val input: Input

    val output: Output

    open fun passed(actualOutput: Output) : Boolean {
        Timber.v("passed: actualOutput = $actualOutput, output = $output")
        return actualOutput == output
    }

}