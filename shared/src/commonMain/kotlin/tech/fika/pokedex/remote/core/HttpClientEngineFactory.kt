package tech.fika.pokedex.remote.core

import io.ktor.client.engine.HttpClientEngine

fun interface HttpClientEngineFactory {
    fun create(): HttpClientEngine
}