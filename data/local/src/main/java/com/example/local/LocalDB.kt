package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.data.converters.DataConverters
import com.example.local.data.dao.PokemonDao
import com.example.local.data.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
@TypeConverters(DataConverters::class)
abstract class LocalDB : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}