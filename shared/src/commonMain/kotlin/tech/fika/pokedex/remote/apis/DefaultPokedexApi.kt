package tech.fika.pokedex.remote.apis

import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.encodedPath
import tech.fika.pokedex.remote.core.HttpClientFactory
import tech.fika.pokedex.remote.models.NamedApiModel
import tech.fika.pokedex.remote.models.PaginationApiModel
import tech.fika.pokedex.remote.models.PokemonApiModel

class DefaultPokedexApi(httpClientFactory: HttpClientFactory) : PokedexApi {

    private val httpClient = httpClientFactory.create()

    override suspend fun getPokemonList(limit: Int?, offset: Int?): PaginationApiModel {
        val path = "api/v2/pokemon"

        return httpClient.request {
            url.encodedPath = path
            method = HttpMethod.Get
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

    override suspend fun getPokemon(id: Int): PokemonApiModel {
        val path = "api/v2/pokemon/$id"

        return httpClient.request {
            url.encodedPath = path
            method = HttpMethod.Get
        }.body()
    }

    override suspend fun getPokemon(name: String): PokemonApiModel {
        val path = "api/v2/pokemon/$name"

        return httpClient.request {
            url.encodedPath = path
            method = HttpMethod.Get
        }.body()
    }
}