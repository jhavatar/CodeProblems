package io.chthonic.codprob.business.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.chthonic.codprob.business.challenge.ChallengeInteractor
import io.chthonic.codprob.business.challenge.ChallengeInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class BusinessModule {

    @Binds
    @Singleton
    abstract fun bindCodeProblemInteractor(impl: ChallengeInteractorImpl): ChallengeInteractor

}
