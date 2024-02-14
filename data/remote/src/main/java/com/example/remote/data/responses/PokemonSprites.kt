package com.example.remote.data.responses

import com.google.gson.annotations.SerializedName

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
)
