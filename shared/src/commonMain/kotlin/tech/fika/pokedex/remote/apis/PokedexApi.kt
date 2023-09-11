package tech.fika.pokedex.remote.apis

import tech.fika.pokedex.remote.models.PaginationApiModel
import tech.fika.pokedex.remote.models.PokemonApiModel

interface PokedexApi {
    suspend fun getPokemonList(limit: Int?, offset: Int?): PaginationApiModel
    suspend fun getPokemon(id: Int): PokemonApiModel
    suspend fun getPokemon(name: String): PokemonApiModel
}