package com.example.local.di

import android.app.Application
import androidx.room.Room
import com.example.local.LocalDB
import com.example.local.domain.Constants
import dagger.Provides
import javax.inject.Singleton

object LocalModule {
    @Provides
    @Singleton
    fun provideMyDatabase(application: Application): LocalDB {
        return Room.databaseBuilder(
            application,
            LocalDB::class.java,
            Constants.DBName
        )
            .enableMultiInstanceInvalidation()
            .build()
    }
}