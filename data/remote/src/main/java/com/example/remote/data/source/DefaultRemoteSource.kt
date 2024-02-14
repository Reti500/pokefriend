package com.example.remote.data.source

import com.example.remote.data.responses.PokemonDetailResponse
import com.example.remote.data.responses.PokemonResponse
import com.example.remote.domain.BaseApiResponse
import com.example.remote.domain.NetworkResult
import com.example.remote.domain.RemoteSource
import com.example.remote.service.ApiService
import javax.inject.Inject

class DefaultRemoteSource @Inject constructor(
    private val apiService: ApiService
): BaseApiResponse(), RemoteSource {
    override suspend fun getPokemonList(limit: Int, offset: Int): NetworkResult<PokemonResponse> {
        return safeApiCall { apiService.listOfPokemon(limit, offset) }
    }

    override suspend fun getPokemonDetail(name: String): NetworkResult<PokemonDetailResponse> {
        return safeApiCall { apiService.pokemonDetail(name) }
    }
}