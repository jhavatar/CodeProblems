package io.chthonic.codprob.entity.challenge

data class ChallengeSampleResult(
    val challengeIndex: Int,
    val sampleIndex: Int,
    val pass: Boolean,
    val durationInMillis: Long
)