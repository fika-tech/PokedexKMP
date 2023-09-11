package tech.fika.pokedex.android.features.pokemonList

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.pokedex.data.repository.PokemonRepository
import tech.fika.pokedex.features.pokemonList.PokemonListAction
import tech.fika.pokedex.features.pokemonList.PokemonListIntent
import tech.fika.pokedex.features.pokemonList.PokemonListState
import tech.fika.pokedex.features.pokemonList.PokemonListStateMachine

@Module
@InstallIn(ActivityRetainedComponent::class)
class PokemonListModule {
    @Provides
    fun providePokemonListStateMachine(
        pokemonRepository: PokemonRepository,
    ): StateMachine<PokemonListIntent, PokemonListAction, PokemonListState> = PokemonListStateMachine(pokemonRepository = pokemonRepository)
}
