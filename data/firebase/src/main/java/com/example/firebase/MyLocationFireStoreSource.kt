package com.example.firebase

import com.example.firebase.data.MyLocation
import com.example.firebase.data.MyLocationDocument
import com.example.firebase.domain.FireStoreSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MyLocationFireStoreSource : FireStoreSource<MyLocation> {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = "Locations"

    override suspend fun saveMany(id: String, list: List<MyLocation>) {
        val document = firestore.collection(collection).document(id)
        document.set(MyLocationDocument(list))
            .addOnSuccessListener {}
            .addOnFailureListener { exception -> }
    }

    override suspend fun saveOne(id: String, data: MyLocation) {
        val document = firestore.collection(collection).document(id)
        val snapshot = document.get().await()
        val locations = snapshot.toObject(MyLocationDocument::class.java)?.locations?.toMutableList()
            ?: mutableListOf()

        locations.add(data)

        document.set(MyLocationDocument(locations = locations))
            .addOnSuccessListener {}
            .addOnFailureListener { exception -> }
    }

    override suspend fun getAll(id: String): List<MyLocation> {
        val document = firestore.collection(collection).document(id)
        val snapshot = document.get().await()

        return snapshot.toObject(MyLocationDocument::class.java)?.locations?.toMutableList()
            ?: mutableListOf()
    }
}