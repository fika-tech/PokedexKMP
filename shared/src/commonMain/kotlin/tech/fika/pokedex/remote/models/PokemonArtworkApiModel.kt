package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonArtworkApiModel(
    @SerialName("front_default") val frontDefault: String,
)
