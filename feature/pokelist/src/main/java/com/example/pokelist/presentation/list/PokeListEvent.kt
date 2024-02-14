package com.example.pokelist.presentation.list

sealed class ListEvent {
    data object OnGetPokemonList: ListEvent()
}