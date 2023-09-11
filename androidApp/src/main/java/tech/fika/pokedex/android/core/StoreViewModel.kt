package tech.fika.pokedex.android.core

import androidx.lifecycle.ViewModel
import tech.fika.macaron.core.components.Store
import tech.fika.macaron.core.contract.Action
import tech.fika.macaron.core.contract.Intent
import tech.fika.macaron.core.contract.State

abstract class StoreViewModel<I : Intent, A : Action, S : State> : ViewModel() {

    abstract val store: Store<I, A, S>

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }
}
