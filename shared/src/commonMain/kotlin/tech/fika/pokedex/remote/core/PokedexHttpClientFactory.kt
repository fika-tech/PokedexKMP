package tech.fika.pokedex.remote.core

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol

class PokedexHttpClientFactory(
    private val httpClientEngineFactory: HttpClientEngineFactory,
) : HttpClientFactory {
    override fun create(): HttpClient = HttpClient(httpClientEngineFactory.create()) {
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            header(HttpHeaders.Connection, "close") // to avoid java.io.IOException
            host = "pokeapi.co"
            url.protocol = URLProtocol.HTTPS
            accept(ContentType.Application.Json)
        }
        installJsonSerializer()
//        installLogging()
        installTimeOutConfig()
    }
}