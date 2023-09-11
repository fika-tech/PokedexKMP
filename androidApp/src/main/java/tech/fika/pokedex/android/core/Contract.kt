package tech.fika.pokedex.android.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import tech.fika.macaron.core.components.Store
import tech.fika.macaron.core.contract.Action
import tech.fika.macaron.core.contract.Intent
import tech.fika.macaron.core.contract.State

data class Contract<I : Intent, A : Action, S : State>(
    val state: S,
    val dispatch: (I) -> Unit = {},
    internal val event: A? = null,
    internal val process: (A) -> Unit = {},
)

@Composable
fun <I : Intent, A : Action, S : State> contract(
    store: Store<I, A, S>,
): Contract<I, A, S> {
    val state by store.state.collectAsState()
    val event by store.event.collectAsState(initial = null)

    return Contract(state = state, event = event, dispatch = store::dispatch, process = store::process)
}
