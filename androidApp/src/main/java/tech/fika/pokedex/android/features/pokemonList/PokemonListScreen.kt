package tech.fika.pokedex.android.features.pokemonList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.fika.pokedex.android.core.Contract
import tech.fika.pokedex.android.core.handleEvents
import tech.fika.pokedex.android.core.render
import tech.fika.pokedex.android.core.renderItems
import tech.fika.pokedex.android.utils.screenPadding
import tech.fika.pokedex.android.utils.onScrolledToBottom
import tech.fika.pokedex.features.pokemonList.PokemonListAction
import tech.fika.pokedex.features.pokemonList.PokemonListIntent
import tech.fika.pokedex.features.pokemonList.PokemonListState
import timber.log.Timber

@Composable
fun PokemonListScreen(
    contract: Contract<PokemonListIntent, PokemonListAction, PokemonListState>,
) {
    LaunchedEffect(Unit) {
        contract.dispatch(PokemonListIntent.OnInit)
    }

    contract.handleEvents { action ->
        when (action) {
            is PokemonListAction.NavigatePokemonDetails -> Timber.d("Handle Navigation")
            else -> Unit
        }
    }

    PokemonListContent(state = contract.state, dispatch = contract.dispatch)
}

@Composable
fun PokemonListContent(
    state: PokemonListState,
    dispatch: (PokemonListIntent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.screenPadding(),
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = contentPadding)
        ) {
            state.render<PokemonListState.Loading> {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            state.render<PokemonListState.Stable> {
                val lazyListState = rememberLazyListState().apply {
                    onScrolledToBottom { dispatch(PokemonListIntent.OnScrollToBottom) }
                }

                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    state = lazyListState,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items = pokemonList, key = { it.id }) { pokemon ->
                        PokemonListEntry(name = pokemon.name, type = pokemon.type.name, imageUrl = pokemon.thumbnailUrl) {
                            dispatch(PokemonListIntent.ClickPokemonEntry(pokemon = pokemon))
                        }
                    }
                    state.renderItems<PokemonListState.Stable.PageLoading> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                    state.renderItems<PokemonListState.Stable.PageError> {
                        item {
                            Button(
                                onClick = { dispatch(PokemonListIntent.ClickErrorRetry) },
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center)
                            ) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }
            }

            state.render<PokemonListState.Error> {
                Button(
                    onClick = { dispatch(PokemonListIntent.ClickErrorRetry) }, modifier = Modifier.wrapContentSize()
                ) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

@Composable
private fun PokemonListEntry(
    name: String,
    type: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(text = name)
        Text(text = type)
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
        )
    }
}