package com.example.pokelist.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokelist.presentation.detail.components.FullPokemonCard

@Composable
fun PokemonDetailScreen(id: Int) {
    val viewModel = hiltViewModel<PokemonDetailViewModel>()
    viewModel.onEvent(PokemonDetailEvent.OnLoadPokemon(id))

    Page(
        state = viewModel.state.collectAsState(),
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun Page(
    state: State<PokemonDetailState>,
    onEvent: (e: PokemonDetailEvent) -> Unit
) {
    Column {
        if (state.value.isLoading || state.value.pokemon == null) {
            CircularProgressIndicator()
        } else {
            FullPokemonCard(
                modifier = Modifier.padding(16.dp),
                pokemon = state.value.pokemon!!,
                onFavorite = { id, favorite ->
                    onEvent(PokemonDetailEvent.OnToggleFavorite(id, favorite))
                }
            )
        }
    }
}