package com.example.pokelist.presentation.detail

sealed class PokemonDetailEvent {
    class OnLoadPokemon(val id: Int): PokemonDetailEvent()
    class OnToggleFavorite(val id: Int, val favorite: Boolean): PokemonDetailEvent()
}