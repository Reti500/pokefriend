package com.example.pokelist.presentation.list

sealed class PokeListEvent {
    data object OnGetPokemonList: PokeListEvent()
    class OnGoToDetail(val id: Int): PokeListEvent()
    class ToggleFavorite(val id: Int, val favorite: Boolean): PokeListEvent()
}