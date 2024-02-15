package com.example.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.core.constants.Constants
import com.example.core.injections.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val settingsDataStore = context.dataStore

    val currentUser: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Constants.CurrentUserKey] ?: ""
    }

    suspend fun getCurrentUser(): String {
        val preferences = context.dataStore.data.first()
        return preferences[Constants.CurrentUserKey] ?: ""
    }

    suspend fun setCurrentUser(token: String) {
        settingsDataStore.edit { settings -> settings[Constants.CurrentUserKey] = token }
    }
}