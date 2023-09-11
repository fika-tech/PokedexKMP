package tech.fika.pokedex.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamedApiModel(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)
