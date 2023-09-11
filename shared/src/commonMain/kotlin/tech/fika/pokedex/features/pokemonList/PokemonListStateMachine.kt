package tech.fika.pokedex.features.pokemonList

import tech.fika.macaron.statemachine.components.StateMachine
import tech.fika.pokedex.data.repository.PokemonRepository

class PokemonListStateMachine(
    private val pokemonRepository: PokemonRepository,
) : StateMachine<PokemonListIntent, PokemonListAction, PokemonListState>(
    builder = {
        state<PokemonListState.Initial> {
            process<PokemonListIntent.OnInit> {
                loadPokemonList(offset = 0, pokemonRepository = pokemonRepository, emit = ::emit)
            }
            reduce<PokemonListAction.Loading> {
                PokemonListState.Loading
            }
        }

        state<PokemonListState.Loading> {
            reduce<PokemonListAction.LoadSuccess> {
                PokemonListState.Stable.Initial(pokemonList = action.pokemonList)
            }
            reduce<PokemonListAction.LoadError> {
                PokemonListState.Error(error = action.error)
            }
        }

        state<PokemonListState.Stable> {
            process<PokemonListIntent.ClickPokemonEntry> {
                emit(PokemonListAction.NavigatePokemonDetails(pokemon = intent.pokemon))
            }
        }

        state<PokemonListState.Stable.Initial> {
            process<PokemonListIntent.OnScrollToBottom> {
                loadPokemonList(offset = state.currentOffset, pokemonRepository = pokemonRepository, emit = ::emit)
            }
            reduce<PokemonListAction.Loading> {
                PokemonListState.Stable.PageLoading(pokemonList = state.pokemonList)
            }
        }

        state<PokemonListState.Stable.PageLoading> {
            reduce<PokemonListAction.LoadSuccess> {
                PokemonListState.Stable.Initial(pokemonList = state.pokemonList + action.pokemonList)
            }
            reduce<PokemonListAction.LoadError> {
                PokemonListState.Stable.PageError(pokemonList = state.pokemonList, error = action.error)
            }
        }

        state<PokemonListState.Stable.PageError> {
            process<PokemonListIntent.ClickErrorRetry> {
                loadPokemonList(offset = state.currentOffset, pokemonRepository = pokemonRepository, emit = ::emit)
            }
            reduce<PokemonListAction.Loading> {
                PokemonListState.Stable.PageLoading(pokemonList = state.pokemonList)
            }
        }

        state<PokemonListState.Error> {
            process<PokemonListIntent.ClickErrorRetry> {
                loadPokemonList(offset = 0, pokemonRepository = pokemonRepository, emit = ::emit)
            }
        }
    }
)

private const val PAGE_LIMIT = 20

private suspend fun loadPokemonList(
    offset: Int,
    pokemonRepository: PokemonRepository,
    emit: suspend (PokemonListAction) -> Unit,
) {
    emit(PokemonListAction.Loading)
    runCatching {
        pokemonRepository.getPokemon(offset = offset, limit = PAGE_LIMIT)
    }.onSuccess { pokemonList ->
        emit(PokemonListAction.LoadSuccess(pokemonList = pokemonList))
    }.onFailure { error ->
        emit(PokemonListAction.LoadError(error = error))
    }
}
