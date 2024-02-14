package com.example.pokelist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.SourceResult
import com.example.core.injections.replace
import com.example.navigator.AppNavigator
import com.example.pokelist.domain.usecases.GetPokemonDetail
import com.example.pokelist.domain.usecases.GetPokemonList
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
    private val getPokemonDetail: GetPokemonDetail
): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state.asStateFlow()

    fun onEvent(e: ListEvent) {
        when(e){
            ListEvent.OnGetPokemonList -> fetchPokemonList()
        }
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            getPokemonList(state.value.limit, state.value.currentOffset)
                .onEach {
                    when(it) {
                        is SourceResult.Error -> {}
                        is SourceResult.OnLoading -> {}
                        is SourceResult.Success -> {
                            it.data?.let { data ->
                                _state.update {state ->
                                    state.copy(
                                        currentOffset = state.currentOffset + 1,
                                        pokemonList = state.pokemonList + data
                                    )
                                }

                                // Get detail of avery item
                                data.forEach { p ->
                                    fetchPokemonDetail(p.name)
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
                            }
                        }
                    }
                }.launchIn(this)
        }
    }
}