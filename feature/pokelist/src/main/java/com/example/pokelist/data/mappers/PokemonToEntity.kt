package com.example.pokelist.data.mappers

import com.example.core.DataMapper
import com.example.local.data.entities.PokemonEntity
import com.example.pokelist.domain.models.Pokemon

class PokemonToEntity: DataMapper<List<Pokemon>, List<PokemonEntity>> {
    override fun map(input: List<Pokemon>): List<PokemonEntity> {
        return input.map {
            PokemonEntity(
                name = it.name,
                isFavorite = it.isFavorite,
                id = it.detail?.id ?: 0,
                image = it.detail?.image ?: "",
                height = it.detail?.height ?: 0,
                weight = it.detail?.weight ?: 0,
                types = it.detail?.types ?: emptyList()
            )
        }
    }
}