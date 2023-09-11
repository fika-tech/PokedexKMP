package tech.fika.pokedex.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.fika.pokedex.remote.apis.DefaultPokedexApi
import tech.fika.pokedex.remote.apis.PokedexApi
import tech.fika.pokedex.remote.core.AndroidHttpClientEngineFactory
import tech.fika.pokedex.remote.core.HttpClientEngineFactory
import tech.fika.pokedex.remote.core.HttpClientFactory
import tech.fika.pokedex.remote.core.PokedexHttpClientFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideHttpClientEngineFactory(): HttpClientEngineFactory = AndroidHttpClientEngineFactory()

    @Provides
    @Singleton
    fun provideHttpClientFactory(
        httpClientEngineFactory: HttpClientEngineFactory,
    ): HttpClientFactory = PokedexHttpClientFactory(httpClientEngineFactory = httpClientEngineFactory)

    @Provides
    @Singleton
    fun providePokedexApi(
        httpClientFactory: HttpClientFactory,
    ): PokedexApi = DefaultPokedexApi(httpClientFactory = httpClientFactory)
}
