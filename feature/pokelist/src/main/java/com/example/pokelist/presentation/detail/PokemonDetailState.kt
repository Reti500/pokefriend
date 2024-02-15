package com.example.pokelist.presentation.detail

import com.example.pokelist.domain.models.Pokemon

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null
)
