package com.example.pokelist.data.usecases

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.GetPokemonDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetPokemonDetail {
    override suspend fun invoke(name: String): Flow<SourceResult<Pokemon>> {
        return pokemonRepository.getPokemonWhitDetail(name)
    }
}