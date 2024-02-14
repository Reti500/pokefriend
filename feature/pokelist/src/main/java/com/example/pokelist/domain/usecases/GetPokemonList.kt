package com.example.pokelist.domain.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonList {
    suspend operator fun invoke(limit: Int, offset: Int): Flow<SourceResult<List<Pokemon>>>
}