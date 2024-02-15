package com.example.core.constants

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val DataStoreName = "settings"

    val CurrentUserKey = stringPreferencesKey("current_user")
}