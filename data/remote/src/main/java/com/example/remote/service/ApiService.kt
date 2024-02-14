package com.example.remote.service

import com.example.remote.data.responses.PokemonDetailResponse
import com.example.remote.data.responses.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun listOfPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun pokemonDetail(
        @Path("name") name: String,
    ): Response<PokemonDetailResponse>
}