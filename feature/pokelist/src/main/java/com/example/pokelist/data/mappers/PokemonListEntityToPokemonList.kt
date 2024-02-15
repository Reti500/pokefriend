package com.example.pokelist.data.mappers

import com.example.core.DataMapper
import com.example.core.SourceResult
import com.example.local.data.entities.PokemonEntity
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.models.PokemonDetail

class PokemonListEntityToPokemonList: DataMapper<List<PokemonEntity>,SourceResult<List<Pokemon>>>{
    override fun map(input: List<PokemonEntity>): SourceResult<List<Pokemon>> {
        return SourceResult.Success(input.map {
            PokemonEntityToPokemon().map(it)
        })
    }
}