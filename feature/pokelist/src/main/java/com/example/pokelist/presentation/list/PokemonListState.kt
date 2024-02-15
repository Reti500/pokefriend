package com.example.pokelist.presentation.list

import com.example.pokelist.domain.models.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val limit: Int = 25,
    val pokemonList: List<Pokemon> = emptyList()
)
