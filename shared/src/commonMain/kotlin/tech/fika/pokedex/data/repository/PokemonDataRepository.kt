package tech.fika.pokedex.data.repository

import co.touchlab.kermit.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import tech.fika.pokedex.data.mapper.PokemonMapper
import tech.fika.pokedex.entities.Pokemon
import tech.fika.pokedex.local.database.PokemonDatabase
import tech.fika.pokedex.local.settings.SettingsHolder
import tech.fika.pokedex.remote.apis.PokedexApi

class PokemonDataRepository(
    private val api: PokedexApi,
    private val database: PokemonDatabase,
    private val settings: SettingsHolder,
) : PokemonRepository {
    override suspend fun getPokemon(offset: Int, limit: Int): List<Pokemon> {
        Logger.d { "Offset: $offset" }
        val localPokemonList = database.pokemonTableQueries.getList(offset = offset.toLong(), limit = limit.toLong()).executeAsList()
        if (localPokemonList.isEmpty()) {
            val namedList = api.getPokemonList(offset = offset, limit = limit)
            val pokemonList = coroutineScope {
                namedList.results.map {
                    async {
                        api.getPokemon(name = it.name)
                    }
                }.awaitAll()
            }
            database.transaction {
                pokemonList.map(PokemonMapper::toDbModel).forEach {
                    database.pokemonTableQueries.insert(it)
                }
            }
            return pokemonList.map(PokemonMapper::toEntity)
        }
        return localPokemonList.map(PokemonMapper::toEntity)
    }
}
