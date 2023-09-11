package tech.fika.pokedex.remote.core

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

class AndroidHttpClientEngineFactory : HttpClientEngineFactory {
    override fun create(): HttpClientEngine = OkHttp.create()
}