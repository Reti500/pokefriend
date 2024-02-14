package com.example.pokelist.data.mappers

import com.example.core.DataMapper
import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import com.example.remote.data.responses.PokemonResponse
import com.example.remote.domain.NetworkResult

class PokemonResponseToListOfPokemon: DataMapper<NetworkResult<PokemonResponse>, SourceResult<List<Pokemon>>> {
    override fun map(input: NetworkResult<PokemonResponse>): SourceResult<List<Pokemon>> {
        return when(input) {
            is NetworkResult.Error -> SourceResult.Error(null, input.message)
            is NetworkResult.Exception -> SourceResult.Error(null, input.message)
            is NetworkResult.Success -> SourceResult.Success(
                input.data?.results?.map { Pokemon(name = it.name, detail = null) } ?: emptyList()
            )
        }
    }
}