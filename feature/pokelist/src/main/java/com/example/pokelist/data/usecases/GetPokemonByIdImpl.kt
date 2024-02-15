package com.example.pokelist.data.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.GetPokemonById
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonByIdImpl @Inject constructor(
    private val repository: PokemonRepository
): GetPokemonById {
    override suspend fun invoke(id: Int): Flow<SourceResult<Pokemon>> {
        return repository.getPokemonById(id)
    }
}