package com.example.local.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.data.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entry: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE id=:id")
    suspend fun findById(id: Int): PokemonEntity

    @Query("SELECT * FROM pokemons ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonEntity>

    @Query("UPDATE pokemons SET is_favorite=:favorite WHERE id=:id")
    suspend fun updateFavorite(id: Int, favorite: Boolean)
}