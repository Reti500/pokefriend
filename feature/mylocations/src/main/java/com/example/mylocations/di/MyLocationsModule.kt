package com.example.mylocations.di

import com.example.core.datastore.DataStoreManager
import com.example.firebase.MyLocationFireStoreSource
import com.example.firebase.data.MyLocation
import com.example.firebase.domain.FireStoreSource
import com.example.mylocations.data.usecases.GetAllLocationsImpl
import com.example.mylocations.data.usecases.SaveLocationImpl
import com.example.mylocations.domain.usecases.GetAllLocations
import com.example.mylocations.domain.usecases.SaveLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MyLocationsModule {
    @Provides
    fun provideLocationFireStoreSource(): FireStoreSource<MyLocation> =
        MyLocationFireStoreSource()

    @Provides
    fun provideSaveLocation(
        source: FireStoreSource<MyLocation>,
        settings: DataStoreManager
    ): SaveLocation = SaveLocationImpl(source, settings)

    @Provides
    fun provideGetAllLocations(
        source: FireStoreSource<MyLocation>,
        settings: DataStoreManager
    ): GetAllLocations = GetAllLocationsImpl(source, settings)
}