package com.example.mylocations.data.usecases

import com.example.core.datastore.DataStoreManager
import com.example.firebase.data.MyLocation
import com.example.firebase.domain.FireStoreSource
import com.example.mylocations.domain.usecases.GetAllLocations
import javax.inject.Inject

class GetAllLocationsImpl @Inject constructor(
    private val fireStoreSource: FireStoreSource<MyLocation>,
    private val settings: DataStoreManager
): GetAllLocations {
    override suspend fun invoke(): List<MyLocation> {
        val id = settings.getCurrentUser()
        return fireStoreSource.getAll(id)
    }
}