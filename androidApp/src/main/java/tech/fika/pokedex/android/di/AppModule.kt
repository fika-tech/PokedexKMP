package tech.fika.pokedex.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.fika.macaron.core.factory.DefaultStoreFactory
import tech.fika.macaron.core.factory.StoreFactory
import tech.fika.pokedex.application.Platform
import tech.fika.pokedex.application.BuildType
import tech.fika.pokedex.application.Config

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()

    @Provides
    @Singleton
    fun provideConfig(@ApplicationContext context: Context): Config {
        // Inject different configurations for product flavors
        return Config(platform = Platform(context = context), buildType = BuildType.Develop, isLoggingEnabled = true)
    }
}