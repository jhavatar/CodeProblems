package io.chthonic.codprob.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.chthonic.codprob.data.challenge.ChallengesRepo
import io.chthonic.codprob.data.challenge.ChallengesRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCodeProblemsRepo(impl: ChallengesRepoImpl): ChallengesRepo

}
