package com.example.pokelist.domain.models

data class PokemonDetail(
    val id: Int,
    val image: String,
    val height: Int,
    val weight: Int,
    val types: List<String>
)
