package com.example.remote.data.responses

import com.google.gson.annotations.SerializedName

data class PokemonSlot(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: EntryResult
)
