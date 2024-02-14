package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class LocalDB : RoomDatabase() {
}