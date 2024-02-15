package com.example.pokelist.domain.repository

import com.example.core.SourceResult
import com.example.pokelist.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<SourceResult<List<Pokemon>>>
    suspend fun getPokemonWhitDetail(name: String): Flow<SourceResult<Pokemon>>
    suspend fun getPokemonById(id: Int): Flow<SourceResult<Pokemon>>
    suspend fun setFavorite(pokemonId: Int, isFavorite: Boolean)
    suspend fun savePokemonList(pokemonList: List<Pokemon>)
}