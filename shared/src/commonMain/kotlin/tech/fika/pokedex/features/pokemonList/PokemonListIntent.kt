package tech.fika.pokedex.features.pokemonList

import tech.fika.macaron.core.contract.Intent
import tech.fika.pokedex.entities.Pokemon

sealed class PokemonListIntent : Intent {
    data object OnInit : PokemonListIntent()
    data class ClickPokemonEntry(val pokemon: Pokemon) : PokemonListIntent()
    data object OnScrollToBottom : PokemonListIntent()
    data object ClickErrorRetry : PokemonListIntent()
}
