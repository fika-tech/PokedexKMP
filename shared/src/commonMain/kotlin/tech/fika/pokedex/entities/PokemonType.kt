package tech.fika.pokedex.entities

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
enum class PokemonType(val id: Int) : Parcelable {
    Normal(id = 1),
    Fighting(id = 2),
    Flying(id = 3),
    Poison(id = 4),
    Ground(id = 5),
    Rock(id = 6),
    Bug(id = 7),
    Ghost(id = 8),
    Steel(id = 9),
    Fire(id = 10),
    Water(id = 11),
    Grass(id = 12),
    Electric(id = 13),
    Psychic(id = 14),
    Ice(id = 15),
    Dragon(id = 16),
    Dark(id = 17),
    Fairy(id = 18),
    Unknown(id = 19),
    Shadow(id = 20),
    ;

    companion object {
        fun fromName(name: String?) = PokemonType.values()
            .find { it.name.lowercase() == name?.lowercase() }
            ?: Unknown
    }
}