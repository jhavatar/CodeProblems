package io.chthonic.codprob.ui.list.model

import io.chthonic.codprob.entity.challenge.Challenge
import io.chthonic.codprob.ui.common.model.ChallengeUiModel
import io.chthonic.codprob.ui.common.model.UiModelConverter
import javax.inject.Inject

class ChallengeUiModelConverter @Inject constructor() :
    UiModelConverter<@JvmSuppressWildcards Pair<Int, Challenge<*,*>>, ChallengeUiModel> {

    override fun convert(entity: Pair<Int, Challenge<*,*>>): ChallengeUiModel {
        return ChallengeUiModel(index = entity.first, name = entity.second.name, description = entity.second.description)
    }

}