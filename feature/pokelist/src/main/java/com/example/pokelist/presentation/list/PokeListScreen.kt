package com.example.pokelist.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.buttons.FavoriteButton
import com.example.components.images.InitialsOrPhotoComponent
import com.example.components.list.InfiniteList
import com.example.pokelist.R
import com.example.pokelist.presentation.list.components.SimplePokemonCard

@Composable
fun PokeListScreen() {
    val viewModel = hiltViewModel<PokemonListViewModel>()
    viewModel.onEvent(PokeListEvent.OnGetPokemonList)

    Page(
        state = viewModel.state.collectAsState(),
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun Page(
    state: State<PokemonListState>,
    onEvent: (e: PokeListEvent) -> Unit
) {
    InfiniteList(
        modifier = Modifier.padding(16.dp),
        spaceBetweenItems = 22.dp,
        items = state.value.pokemonList,
        isLoading = state.value.isLoading,
        loadingView = {
            CircularProgressIndicator()
        },
        loadMoreItems = {
            onEvent(PokeListEvent.OnGetPokemonList)
        }
    ) {
        SimplePokemonCard(
            id = it.detail?.id ?: 0,
            name = it.name,
            image = it.detail?.image ?: "",
            isFavorite = it.isFavorite,
            onSelected = { id -> onEvent(PokeListEvent.OnGoToDetail(id))},
            onToggleFavorite = {id, favorite -> onEvent(PokeListEvent.ToggleFavorite(id, favorite)) }
        )
    }
}