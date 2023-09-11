package tech.fika.pokedex.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.fika.pokedex.data.repository.PokemonDataRepository
import tech.fika.pokedex.data.repository.PokemonRepository
import tech.fika.pokedex.local.database.PokemonDatabase
import tech.fika.pokedex.local.settings.SettingsHolder
import tech.fika.pokedex.remote.apis.PokedexApi

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun providePokemonRepository(
        api: PokedexApi,
        database: PokemonDatabase,
        settings: SettingsHolder,
    ): PokemonRepository = PokemonDataRepository(api = api, database = database, settings = settings)
}
