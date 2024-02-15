package com.example.pokelist.data.repository

import com.example.core.SourceResult
import com.example.local.data.dao.PokemonDao
import com.example.pokelist.data.mappers.PokemonDetailResponseToPokemon
import com.example.pokelist.data.mappers.PokemonEntityToPokemon
import com.example.pokelist.data.mappers.PokemonListEntityToPokemonList
import com.example.pokelist.data.mappers.PokemonResponseToListOfPokemon
import com.example.pokelist.data.mappers.PokemonToEntity
import com.example.pokelist.domain.models.Pokemon
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.remote.domain.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val pokemonDao: PokemonDao
): PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<SourceResult<List<Pokemon>>> = flow<SourceResult<List<Pokemon>>> {
        emit(SourceResult.OnLoading())

        // Get local data
        val localPokemon = pokemonDao.getPokemonList(limit, offset)
        if (localPokemon.isNotEmpty()) {
            // If has local data return this
            emit(PokemonListEntityToPokemonList().map(localPokemon))
        }

        // Find remote data
        val result = remoteSource.getPokemonList(limit, offset)
        emit(PokemonResponseToListOfPokemon().map(result))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonWhitDetail(name: String): Flow<SourceResult<Pokemon>> = flow {
        emit(SourceResult.OnLoading())

        val result = remoteSource.getPokemonDetail(name)

        emit(PokemonDetailResponseToPokemon().map(result))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonById(id: Int): Flow<SourceResult<Pokemon>> = flow<SourceResult<Pokemon>> {
        val pokemon = pokemonDao.findById(id)

        emit(SourceResult.Success(PokemonEntityToPokemon().map(pokemon)))
    }.flowOn(Dispatchers.IO)

    override suspend fun setFavorite(pokemonId: Int, isFavorite: Boolean) {
        pokemonDao.updateFavorite(pokemonId, isFavorite)
    }

    override suspend fun savePokemonList(pokemonList: List<Pokemon>) {
        pokemonDao.insertAll(PokemonToEntity().map(pokemonList))
    }
}