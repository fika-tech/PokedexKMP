package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeApiModel(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: NamedApiModel,
)
