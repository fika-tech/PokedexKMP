package tech.fika.pokedex.remote.core

import io.ktor.client.HttpClient

fun interface HttpClientFactory {
    fun create(): HttpClient
}