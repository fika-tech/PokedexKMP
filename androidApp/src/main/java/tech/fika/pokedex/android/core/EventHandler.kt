package tech.fika.pokedex.android.core

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import tech.fika.macaron.core.contract.Action

@SuppressLint("ComposableNaming")
@Composable
fun <A : Action> A?.handle(process: (A) -> Unit, block: CoroutineScope.(A) -> Unit) {
    this?.let {
        LaunchedEffect(it) {
            block(it)
            process(it)
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <A : Action> Contract<*, A, *>.handleEvents(block: CoroutineScope.(A) -> Unit) {
    event?.let { action ->
        if (action is Action.Event) {
            action.handle(process = process, block = block)
        }
    }
}
