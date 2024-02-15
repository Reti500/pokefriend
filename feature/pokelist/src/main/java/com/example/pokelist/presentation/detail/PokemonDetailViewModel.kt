package com.example.pokelist.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.SourceResult
import com.example.pokelist.domain.usecases.GetPokemonById
import com.example.pokelist.domain.usecases.ToggleFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonById: GetPokemonById,
    private val toggleFavorite: ToggleFavorite
): ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailState())
    val state: StateFlow<PokemonDetailState> = _state.asStateFlow()

    fun onEvent(e: PokemonDetailEvent) {
        when(e) {
            is PokemonDetailEvent.OnLoadPokemon -> {
                viewModelScope.launch {
                    getPokemonById(e.id)
                        .onEach {
                            when(it) {
                                is SourceResult.Error -> {}
                                is SourceResult.OnLoading -> {}
                                is SourceResult.Success -> {
                                    _state.update { s ->
                                        s.copy(pokemon = it.data)
                                    }
                                }
                            }
                        }
                        .launchIn(this)
                }
            }

            is PokemonDetailEvent.OnToggleFavorite -> {
                viewModelScope.launch {
                    toggleFavorite(e.id, e.favorite)
                    _state.update { s ->
                        s.copy(pokemon = state.value.pokemon?.copy(isFavorite = e.favorite))
                    }
                }
            }
        }
    }
}