package tech.fika.pokedex.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.fika.pokedex.application.Config
import tech.fika.pokedex.local.database.AndroidSqlDriverFactory
import tech.fika.pokedex.local.settings.SharedPreferencesSettingsFactory
import tech.fika.pokedex.local.database.DatabaseFactory
import tech.fika.pokedex.local.database.DefaultDatabaseFactory
import tech.fika.pokedex.local.database.PokemonDatabase
import tech.fika.pokedex.local.database.SqlDriverFactory
import tech.fika.pokedex.local.settings.SettingsFactory
import tech.fika.pokedex.local.settings.SettingsHolder

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideSqlDriverFactory(config: Config): SqlDriverFactory = AndroidSqlDriverFactory(config = config)

    @Provides
    @Singleton
    fun provideDatabaseFactory(
        sqlDriverFactory: SqlDriverFactory,
    ): DatabaseFactory<PokemonDatabase> = DefaultDatabaseFactory(sqlDriverFactory = sqlDriverFactory)

    @Provides
    @Singleton
    fun provideDatabase(
        databaseFactory: DatabaseFactory<PokemonDatabase>,
    ): PokemonDatabase = databaseFactory.create()

    @Provides
    @Singleton
    fun provideSettingsFactory(@ApplicationContext context: Context): SettingsFactory = SharedPreferencesSettingsFactory(
        delegate = context.getSharedPreferences("Default", Context.MODE_PRIVATE)
    )

    @Provides
    @Singleton
    fun provideSettingsHolder(
        settingsFactory: SettingsFactory,
    ): SettingsHolder = settingsFactory.create()
}
