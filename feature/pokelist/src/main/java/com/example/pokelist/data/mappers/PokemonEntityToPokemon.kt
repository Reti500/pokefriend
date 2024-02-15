package com.example.pokelist.data.mappers

import com.example.core.DataMapper
import com.example.local.data.entities.PokemonEntity
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.models.PokemonDetail

class PokemonEntityToPokemon: DataMapper<PokemonEntity, Pokemon> {
    override fun map(input: PokemonEntity): Pokemon {
        return Pokemon(
            name = input.name,
            isFavorite = input.isFavorite,
            detail = PokemonDetail(
                id = input.id,
                image = input.image,
                height = input.height,
                weight = input.weight,
                types = input.types
            )
        )
    }
}