package com.example.navigator

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

interface AppNavigator {
    suspend fun navigateTo(route: NavigationRoute)
    suspend fun manager(navController: NavHostController, scope: CoroutineScope)
    suspend fun toBack()
}