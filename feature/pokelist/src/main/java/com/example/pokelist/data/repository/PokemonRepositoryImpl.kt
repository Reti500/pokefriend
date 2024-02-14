package com.example.pokelist.data.repository

import com.example.core.SourceResult
import com.example.pokelist.data.mappers.PokemonDetailResponseToPokemon
import com.example.pokelist.data.mappers.PokemonResponseToListOfPokemon
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.remote.domain.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
): PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<SourceResult<List<Pokemon>>> = flow<SourceResult<List<Pokemon>>> {
        emit(SourceResult.OnLoading())

        val result = remoteSource.getPokemonList(limit, offset)

        emit(PokemonResponseToListOfPokemon().map(result))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonWhitDetail(name: String): Flow<SourceResult<Pokemon>> = flow {
        emit(SourceResult.OnLoading())

        val result = remoteSource.getPokemonDetail(name)

        emit(PokemonDetailResponseToPokemon().map(result))
    }.flowOn(Dispatchers.IO)

    override suspend fun setFavorite(pokemonId: Int, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }
}