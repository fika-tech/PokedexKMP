package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonOtherSpriteApiModel(
    @SerialName("home") val home: PokemonArtworkApiModel,
    @SerialName("dream_world") val dreamWorld: PokemonArtworkApiModel,
    @SerialName("official-artwork") val artwork: PokemonArtworkApiModel,
)