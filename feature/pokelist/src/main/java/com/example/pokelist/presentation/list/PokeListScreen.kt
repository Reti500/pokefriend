package com.example.pokelist.presentation.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.list.InfiniteList

@Composable
fun PokeListScreen() {
    val viewModel = hiltViewModel<PokemonListViewModel>()
    viewModel.onEvent(ListEvent.OnGetPokemonList)

    Page(
        state = viewModel.state.collectAsState(),
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun Page(
    state: State<PokemonListState>,
    onEvent: (e: ListEvent) -> Unit
) {
    InfiniteList(
        items = state.value.pokemonList,
        loadMoreItems = {
            onEvent(ListEvent.OnGetPokemonList)
        }
    ) {
        Text(text = "Pokemon ${it.name}")
        Text(text = "ID -> ${it.detail?.id}")
        Spacer(modifier = Modifier.height(64.dp))
    }
}