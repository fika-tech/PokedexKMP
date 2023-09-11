package tech.fika.pokedex.android.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged

// ref: https://manavtamboli.medium.com/infinite-list-paged-list-in-jetpack-compose-b10fc7e74768
@Composable
fun LazyListState.isAtTop(): Boolean {
    val isAtTop = remember {
        derivedStateOf {
            val firstVisibleItem = firstVisibleItem ?: return@derivedStateOf true
            firstVisibleItem.index == 0 && firstVisibleItem.offset == 0
        }
    }
    return isAtTop.value
}

@Composable
fun LazyListState.isAtBottom(): Boolean {
    val isAtBottom = remember {
        derivedStateOf {
            val lastVisibleItem = lastVisibleItem ?: return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 && lastVisibleItem.offset == layoutInfo.viewportSize.height - lastVisibleItem.size
        }
    }
    return isAtBottom.value
}

@Composable
fun LazyListState.elevation(): Dp {
    val elevation = remember {
        derivedStateOf {
            val firstVisibleItem = firstVisibleItem ?: return@derivedStateOf 0.dp
            val isAtTop = firstVisibleItem.index == 0 && firstVisibleItem.offset == 0
            if (isAtTop) 0.dp else 4.dp
        }
    }
    return elevation.value
}

@SuppressLint("ComposableNaming")
@Composable
fun LazyListState.onScrolledToTop(
    buffer: Int = 0,
    onNotify: () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }
    val shouldLoadMore = remember {
        derivedStateOf {
            val firstVisibleItem = firstVisibleItem ?: return@derivedStateOf true
            firstVisibleItem.index <= buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect { if (it) onNotify() }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun LazyListState.onScrolledToBottom(
    buffer: Int = 3,
    onNotify: () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = lastVisibleItem ?: return@derivedStateOf true
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect { if (it) onNotify() }
    }
}

private val LazyListState.firstVisibleItem get() = layoutInfo.visibleItemsInfo.firstOrNull()
private val LazyListState.lastVisibleItem get() = layoutInfo.visibleItemsInfo.lastOrNull()
