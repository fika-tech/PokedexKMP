package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonApiModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("sprites") val sprites: PokemonSpritesApiModel,
    @SerialName("types") val types: List<PokemonTypeApiModel>,
)
