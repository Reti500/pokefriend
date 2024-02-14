package com.example.pokelist.data.mappers

import com.example.core.DataMapper
import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.models.PokemonDetail
import com.example.remote.data.responses.PokemonDetailResponse
import com.example.remote.domain.NetworkResult

class PokemonDetailResponseToPokemon: DataMapper<NetworkResult<PokemonDetailResponse>, SourceResult<Pokemon>> {
    override fun map(input: NetworkResult<PokemonDetailResponse>): SourceResult<Pokemon> {
        return when(input) {
            is NetworkResult.Error -> SourceResult.Error(null, input.message)
            is NetworkResult.Exception -> SourceResult.Error(null, input.message)
            is NetworkResult.Success -> SourceResult.Success(
                Pokemon(
                    name = input.data?.name ?: "",
                    detail = input.data?.let {
                        PokemonDetail(
                            id = it.id,
                            image = it.sprites.frontDefault,
                            height = it.height,
                            weight = it.weight,
                            types = it.types.map { t -> t.type.name }
                        )
                    }
                )
            )
        }
    }
}