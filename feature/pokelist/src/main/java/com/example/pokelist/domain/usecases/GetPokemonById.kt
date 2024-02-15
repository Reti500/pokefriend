package com.example.pokelist.domain.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonById {
    suspend operator fun invoke(id: Int): Flow<SourceResult<Pokemon>>
}