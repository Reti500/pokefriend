package com.example.remote.domain

import com.example.remote.data.responses.PokemonDetailResponse
import com.example.remote.data.responses.PokemonResponse

interface RemoteSource {
    suspend fun getPokemonList(limit: Int, offset: Int): NetworkResult<PokemonResponse>
    suspend fun getPokemonDetail(name: String): NetworkResult<PokemonDetailResponse>
}