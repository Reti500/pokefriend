package com.example.firebase.domain

interface FireStoreSource<T> {
    suspend fun saveMany(id: String, list: List<T>)
    suspend fun saveOne(id: String, data: T)
    suspend fun getAll(id: String): List<T>
}