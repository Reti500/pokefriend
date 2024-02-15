package com.example.pokelist.domain.usecases

import com.example.pokelist.domain.models.Pokemon

interface SavePokemonList {
    suspend operator fun invoke(pokemonList: List<Pokemon>)
}