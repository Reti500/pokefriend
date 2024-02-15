package com.example.components.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.text.MediumDarkText

@Composable
fun <T>InfiniteList(
    items: List<T>,
    modifier: Modifier = Modifier,
    spaceBetweenItems: Dp = 16.dp,
    isLoading: Boolean,
    loadMoreItems: () -> Unit,
    loadingView: @Composable () -> Unit,
    content: @Composable (T) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spaceBetweenItems),
        state = listState,
    ) {
        items(items) {
            content(it)
        }
    }

    if (isLoading) {
        loadingView()
    }

    // call the extension function
    listState.OnBottomReached(buffer = 2) {
        Log.e("INFINITE", "LOAD MORE")
        loadMoreItems()
    }
}