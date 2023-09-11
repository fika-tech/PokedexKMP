package tech.fika.pokedex.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.fika.pokedex.android.ui.PokemonTheme
import tech.fika.pokedex.android.core.contract
import tech.fika.pokedex.android.features.pokemonList.PokemonListScreen
import tech.fika.pokedex.android.features.pokemonList.PokemonListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokemonTheme {
                PokemonListScreen(contract = contract(store = viewModel.store))
            }
        }
    }
}
