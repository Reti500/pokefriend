package com.example.pokelist.domain.usecases

interface ToggleFavorite {
    suspend operator fun invoke(id: Int, favorite: Boolean)
}