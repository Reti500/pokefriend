package com.example.pokelist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.SourceResult
import com.example.core.injections.replace
import com.example.navigator.AppNavigator
import com.example.navigator.NavigationRoute
import com.example.pokelist.domain.usecases.GetPokemonDetail
import com.example.pokelist.domain.usecases.GetPokemonList
import com.example.pokelist.domain.usecases.SavePokemonList
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
class PokemonListViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val getPokemonList: GetPokemonList,
    private val getPokemonDetail: GetPokemonDetail,
    private val savePokemonList: SavePokemonList,
    private val toggleFavorite: ToggleFavorite
): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state.asStateFlow()

    fun onEvent(e: PokeListEvent) {
        when(e){
            PokeListEvent.OnGetPokemonList -> fetchPokemonList()
            is PokeListEvent.OnGoToDetail -> {
                viewModelScope.launch { navigator.navigateTo(NavigationRoute.PokeDetail(
                    mapOf("id" to e.id)
                )) }
            }

            is PokeListEvent.ToggleFavorite -> markAsFavorite(e.id, e.favorite)
        }
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            getPokemonList(state.value.limit, state.value.pokemonList.size)
                .onEach {
                    _state.update { s -> s.copy(isLoading = false) }

                    when(it) {
                        is SourceResult.Error -> {}
                        is SourceResult.OnLoading -> {
                            _state.update { s -> s.copy(isLoading = false) }
                        }
                        is SourceResult.Success -> {
                            it.data?.let { data ->
                                if (state.value.pokemonList.isEmpty()) {
                                    _state.update {state ->
                                        state.copy(
                                            pokemonList = state.pokemonList + data
                                        )
                                    }

                                    // Get detail of avery item
                                    data.forEach { p ->
                                        if (p.detail == null) fetchPokemonDetail(p.name)
                                    }
                                } else {
                                    data.forEach { p ->
                                        val existsPokemon = state.value.pokemonList.firstOrNull {
                                            poke -> poke.name == p.name
                                        }

                                        if (existsPokemon == null) {
                                            _state.update { s ->
                                                s.copy(pokemonList = state.value.pokemonList + listOf(p))
                                            }
                                            fetchPokemonDetail(p.name)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            getPokemonDetail(name)
                .onEach {
                    when(it) {
                        is SourceResult.Error -> {}
                        is SourceResult.OnLoading -> {}
                        is SourceResult.Success -> {
                            it.data?.let { pokemon ->
                                _state.update { s ->
                                    s.copy(pokemonList = s.pokemonList
                                        .replace(pokemon) { p -> p.name == name })
                                }
                                savePokemonList(_state.value.pokemonList)
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun markAsFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch {
            toggleFavorite(id, favorite)

            val newPokemon = state.value.pokemonList.first { p -> p.detail?.id == id }
                .copy(isFavorite = favorite)
            _state.update { s ->
                s.copy(pokemonList = s.pokemonList
                    .replace(newPokemon) { p -> p.name == newPokemon.name })
            }
        }
    }
}