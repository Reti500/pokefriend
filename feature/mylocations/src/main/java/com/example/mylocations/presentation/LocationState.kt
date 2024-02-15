package com.example.mylocations.presentation

import com.example.firebase.data.MyLocation

data class LocationState(
    val isLoading: Boolean = false,
    val locations: List<MyLocation> = emptyList()
)
