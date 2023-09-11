package tech.fika.pokedex.android.core

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import tech.fika.macaron.core.contract.State

@Composable
inline fun <reified S : State> State.render(block: @Composable S.() -> Unit) {
    if (this is S) {
        block(this)
    }
}

context(LazyListScope)
inline fun <reified S : State> State.renderItems(block: LazyListScope.(S) -> Unit) {
    if (this@State is S) {
        block(this@LazyListScope, this@State)
    }
}