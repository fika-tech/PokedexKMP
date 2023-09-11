package tech.fika.pokedex.entities

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val type: PokemonType,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val thumbnailUrl: String,
) : Parcelable

