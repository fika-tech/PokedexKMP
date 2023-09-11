package tech.fika.pokedex.features.pokemonList

import tech.fika.macaron.core.contract.Action
import tech.fika.pokedex.entities.Pokemon

sealed class PokemonListAction : Action {
    data object Loading : PokemonListAction()
    data class LoadSuccess(val pokemonList: List<Pokemon>) : PokemonListAction()
    data class LoadError(val error: Throwable) : PokemonListAction()
    data class NavigatePokemonDetails(val pokemon: Pokemon) : PokemonListAction(), Action.Event
}
