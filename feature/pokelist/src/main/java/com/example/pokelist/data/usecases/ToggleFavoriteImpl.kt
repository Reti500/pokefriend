package com.example.pokelist.data.usecases

import com.example.pokelist.domain.repository.PokemonRepository
import com.example.pokelist.domain.usecases.ToggleFavorite
import javax.inject.Inject

class ToggleFavoriteImpl @Inject constructor(
    private val repository: PokemonRepository
): ToggleFavorite {
    override suspend fun invoke(id: Int, favorite: Boolean) {
        repository.setFavorite(id, favorite)
    }
}