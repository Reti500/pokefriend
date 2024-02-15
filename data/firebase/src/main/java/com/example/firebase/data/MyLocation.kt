package com.example.firebase.data

data class MyLocation(
    val lat: Double,
    val lng: Double
) {
    constructor() : this(0.0, 0.0)
}
