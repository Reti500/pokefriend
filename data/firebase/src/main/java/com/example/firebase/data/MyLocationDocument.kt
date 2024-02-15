package com.example.firebase.data

data class MyLocationDocument(
    val locations: List<MyLocation>
) {
    constructor() : this(listOf())
}
