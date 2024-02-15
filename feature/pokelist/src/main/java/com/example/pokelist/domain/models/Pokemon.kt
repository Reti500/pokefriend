package com.example.pokelist.domain.models

data class Pokemon(
    val name: String,
    val isFavorite: Boolean,
    val detail: PokemonDetail?
)
