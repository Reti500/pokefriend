package com.example.pokelist.di

import com.example.pokelist.data.repository.PokemonRepositoryImpl
import com.example.pokelist.data.usecases.GetPokemonDetailImpl
import com.example.pokelist.data.usecases.GetPokemonListImpl
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.GetPokemonDetail
import com.example.pokelist.domain.usecases.GetPokemonList
import com.example.remote.data.source.DefaultRemoteSource
import com.example.remote.domain.RemoteSource
import com.example.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PokeListModule {
    @Provides
    fun provideRemoteSource(apiService: ApiService): RemoteSource =
        DefaultRemoteSource(apiService)

    @Provides
    fun providePokemonRepository(remoteSource: RemoteSource): PokemonRepository =
        PokemonRepositoryImpl(remoteSource)

    @Provides
    fun provideGetPokemonList(repository: PokemonRepository): GetPokemonList =
        GetPokemonListImpl(repository)

    @Provides
    fun provideGetPokemonDetail(repository: PokemonRepository): GetPokemonDetail =
        GetPokemonDetailImpl(repository)
}