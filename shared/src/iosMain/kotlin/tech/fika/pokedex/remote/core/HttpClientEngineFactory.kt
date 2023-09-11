package tech.fika.pokedex.remote.core

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

class IosHttpClientEngineFactory : HttpClientEngineFactory {
    override fun create(): HttpClientEngine = Darwin.create()
}