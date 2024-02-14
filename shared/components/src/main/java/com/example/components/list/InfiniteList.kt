package com.example.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun <T>InfiniteList(
    items: List<T>,
    modifier: Modifier = Modifier,
    loadMoreItems: () -> Unit,
    content: @Composable (T) -> Unit
) {
    val listState = rememberLazyListState()
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 2
        }
    }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) loadMoreItems()
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(items) {
            content(it)
        }
    }
}