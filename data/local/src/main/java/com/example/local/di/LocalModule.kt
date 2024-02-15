package com.example.local.di

import android.app.Application
import androidx.room.Room
import com.example.local.LocalDB
import com.example.local.data.dao.PokemonDao
import com.example.local.domain.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideMyDatabase(application: Application): LocalDB {
        return Room.databaseBuilder(
            application,
            LocalDB::class.java,
            Constants.DBName)
            .enableMultiInstanceInvalidation()
            .build()
    }

    @Provides
    fun providePokemonDao(db: LocalDB): PokemonDao =
        db.pokemonDao()
}