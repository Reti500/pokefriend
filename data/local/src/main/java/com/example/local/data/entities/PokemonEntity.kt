package com.example.local.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemons",
    indices = [Index(value = ["name"], unique = true)]
)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("height") val height: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("is_favorite") val isFavorite: Boolean,
    @ColumnInfo("types") val types: List<String>,
)
