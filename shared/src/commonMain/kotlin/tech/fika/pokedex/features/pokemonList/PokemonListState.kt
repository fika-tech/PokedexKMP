package tech.fika.pokedex.features.pokemonList

import tech.fika.macaron.core.contract.State
import tech.fika.pokedex.entities.Pokemon

sealed class PokemonListState : State {

    data object Initial : PokemonListState()

    data object Loading : PokemonListState()

    sealed class Stable : PokemonListState() {
        abstract val pokemonList: List<Pokemon>
        internal val currentOffset: Int get() = pokemonList.size

        data class Initial(
            override val pokemonList: List<Pokemon>,
        ) : Stable()

        data class PageLoading(
            override val pokemonList: List<Pokemon>,
        ) : Stable()

        data class PageError(
            override val pokemonList: List<Pokemon>,
            val error: Throwable,
        ) : Stable()
    }

    data class Error(
        val error: Throwable,
    ) : PokemonListState()
}
