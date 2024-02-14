package com.example.pokelist.data.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.GetPokemonList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetPokemonList {
    override suspend fun invoke(limit: Int, offset: Int): Flow<SourceResult<List<Pokemon>>> =
        pokemonRepository.getPokemonList(limit, offset)
}