package com.example.pokelist.domain.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetail {
    suspend operator fun invoke(name: String): Flow<SourceResult<Pokemon>>
}