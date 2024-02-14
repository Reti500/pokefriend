package com.example.remote.data.responses

import com.google.gson.annotations.SerializedName

data class EntryResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
