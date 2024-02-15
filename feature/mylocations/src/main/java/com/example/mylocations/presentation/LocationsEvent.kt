package com.example.mylocations.presentation

sealed class LocationsEvent {
    data object OnLoadLocations: LocationsEvent()
}