package com.example.pokelist.di

import com.example.local.data.dao.PokemonDao
import com.example.pokelist.data.repository.PokemonRepositoryImpl
import com.example.pokelist.data.usecases.GetPokemonByIdImpl
import com.example.pokelist.data.usecases.GetPokemonDetailImpl
import com.example.pokelist.data.usecases.GetPokemonListImpl
import com.example.pokelist.data.usecases.SavePokemonListImpl
import com.example.pokelist.data.usecases.ToggleFavoriteImpl
import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.GetPokemonById
import com.example.pokelist.domain.usecases.GetPokemonDetail
import com.example.pokelist.domain.usecases.GetPokemonList
import com.example.pokelist.domain.usecases.SavePokemonList
import com.example.pokelist.domain.usecases.ToggleFavorite
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
    fun providePokemonRepository(
        remoteSource: RemoteSource,
        pokemonDao: PokemonDao
    ): PokemonRepository =
        PokemonRepositoryImpl(remoteSource, pokemonDao)

    @Provides
    fun provideGetPokemonList(repository: PokemonRepository): GetPokemonList =
        GetPokemonListImpl(repository)

    @Provides
    fun provideGetPokemonDetail(repository: PokemonRepository): GetPokemonDetail =
        GetPokemonDetailImpl(repository)

    @Provides
    fun provideSavePokemonList(repository: PokemonRepository): SavePokemonList =
        SavePokemonListImpl(repository)

    @Provides
    fun provideToggleFavorite(repository: PokemonRepository): ToggleFavorite =
        ToggleFavoriteImpl(repository)

    @Provides
    fun provideGetPokemonById(repository: PokemonRepository): GetPokemonById =
        GetPokemonByIdImpl(repository)
}