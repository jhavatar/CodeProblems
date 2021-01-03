package io.chthonic.codprob.ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.ui.common.model.UiModelConverter
import io.chthonic.codprob.ui.list.model.ChallengeUiModelConverter
import io.chthonic.codprob.ui.common.model.ChallengeUiModel

@Module
@InstallIn(ActivityComponent::class)
abstract class UiModule {

    @Binds
    abstract fun bindChallengeUiModelConverter(impl: ChallengeUiModelConverter):
            UiModelConverter<@JvmSuppressWildcards Pair<Int, Challenge<*,*>>, ChallengeUiModel>

}