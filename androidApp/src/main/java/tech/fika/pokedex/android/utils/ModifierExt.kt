package tech.fika.pokedex.android.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

fun Modifier.screenPadding(
    dispatch: () -> Unit = {},
): Modifier = navigationBarsPadding()
    .statusBarsPadding()
    .imePadding()
    .composed {
        val keyboardController = LocalSoftwareKeyboardController.current
        clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) {
            dispatch()
            keyboardController?.hide()
        }
    }