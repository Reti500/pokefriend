package com.example.mylocations.data.usecases

import com.example.core.datastore.DataStoreManager
import com.example.firebase.data.MyLocation
import com.example.firebase.domain.FireStoreSource
import com.example.mylocations.domain.usecases.SaveLocation
import javax.inject.Inject

class SaveLocationImpl @Inject constructor(
    private val fireStoreSource: FireStoreSource<MyLocation>,
    private val settings: DataStoreManager
): SaveLocation {
    override suspend fun invoke(location: MyLocation) {
        val id = settings.getCurrentUser()
        fireStoreSource.saveOne(id, location)
    }
}