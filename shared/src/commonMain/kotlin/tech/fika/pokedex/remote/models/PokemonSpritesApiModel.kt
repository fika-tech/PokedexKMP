package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpritesApiModel(
    @SerialName("front_default") val frontDefault: String,
    @SerialName("other") val other: PokemonOtherSpriteApiModel,
)

