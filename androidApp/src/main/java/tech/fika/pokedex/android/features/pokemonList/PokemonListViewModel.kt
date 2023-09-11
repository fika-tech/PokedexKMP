package tech.fika.pokedex.android.features.pokemonList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import tech.fika.macaron.core.components.Store
import tech.fika.macaron.core.factory.StoreFactory
import tech.fika.macaron.logging.DefaultLogger
import tech.fika.macaron.logging.LoggingMiddleware
import tech.fika.pokedex.android.core.StoreViewModel
import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.pokedex.features.pokemonList.PokemonListAction
import tech.fika.pokedex.features.pokemonList.PokemonListIntent
import tech.fika.pokedex.features.pokemonList.PokemonListState

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    storeFactory: StoreFactory,
    stateMachine: StateMachine<PokemonListIntent, PokemonListAction, PokemonListState>,
    savedStateHandle: SavedStateHandle,
) : StoreViewModel<PokemonListIntent, PokemonListAction, PokemonListState>() {
    override val store: Store<PokemonListIntent, PokemonListAction, PokemonListState> = storeFactory.create(
        initialState = PokemonListState.Initial,
        processor = stateMachine.processor,
        reducer = stateMachine.reducer,
        middlewares = listOf(
//            StateSaverMiddleware(stateSaver = DefaultStateSaver(savedStateHandle = savedStateHandle)),
            LoggingMiddleware(logger = DefaultLogger()),
        ),
        coroutineContext = viewModelScope.coroutineContext,
    )
}
