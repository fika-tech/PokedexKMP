
package tech.fika.pokedex.remote.core

import co.touchlab.kermit.Logger.Companion as KermitLogger
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/**
 * Install json serializer
 *
 * Serializer config for [HttpClient]
 */
fun HttpClientConfig<*>.installJsonSerializer() {
    install(ContentNegotiation) {
        json(defaultJson)
    }
}

fun HttpClientConfig<*>.installHtmlJsonSerializer() {
    install(ContentNegotiation) {
        json(json = defaultJson, contentType = ContentType.Text.Html)
    }
}

/**
 * Install time out config
 *
 * Timeout config for [HttpClient]
 */
fun HttpClientConfig<*>.installTimeOutConfig() = install(HttpTimeout) {
    requestTimeoutMillis = 50000
    connectTimeoutMillis = 50000
    socketTimeoutMillis = 50000
}

/**
 * Install logging
 *
 * Logging config for [HttpClient]
 */
fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        level = LogLevel.ALL
        this.logger = object : Logger {
            override fun log(message: String) {
                KermitLogger.d(tag = "Ktor") { message }
            }
        }
    }
}

/**
 * Install error handling
 *
 * Response validation for errors
 */
fun HttpClientConfig<*>.installErrorHandling(
    block: suspend (HttpResponse) -> Unit,
) {
    expectSuccess = true
    install(HttpCallValidator) {
        handleResponseExceptionWithRequest { exception, _ ->
            when (exception) {
                is ResponseException -> block(exception.response)
            }
        }
    }
}

/**
 * Parse
 *
 * Extension to decode responses
 */
internal suspend inline fun <reified R> HttpResponse.parse(): R = defaultJson.decodeFromString(serializer(), bodyAsText())

internal suspend inline fun <reified R> HttpResponse.parseOrNull(): R? = runCatching { parse<R>() }.getOrNull()

/**
 * Json
 *
 * Default Json serialization config
 */
private val defaultJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
    encodeDefaults = false
    useAlternativeNames = false
}
