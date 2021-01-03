package io.chthonic.codprob.ui.common.model

interface UiModelConverter<Entity, UiModel> {

    fun convert(entity: Entity) : UiModel

}