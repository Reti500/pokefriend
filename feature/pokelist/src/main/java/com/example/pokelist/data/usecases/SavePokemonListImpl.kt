package com.example.pokelist.data.usecases

import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.SavePokemonList
import javax.inject.Inject

class SavePokemonListImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): SavePokemonList {
    override suspend fun invoke(pokemonList: List<Pokemon>) {
        pokemonRepository.savePokemonList(pokemonList)
    }
}