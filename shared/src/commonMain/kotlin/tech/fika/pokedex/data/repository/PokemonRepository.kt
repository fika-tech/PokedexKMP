package tech.fika.pokedex.data.repository

import tech.fika.pokedex.entities.Pokemon

interface PokemonRepository {

    suspend fun getPokemon(offset: Int, limit: Int): List<Pokemon>
}