package com.example.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface AppLocationManager {
    suspend fun getLocation(): Location?
    suspend fun getLocationUpdates(interval: Long): Flow<Location>
    class LocationException(message: String): Exception()
}