package com.example.navigator

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultNavigatorImp(): AppNavigator {
    private val _sharedFlow = MutableSharedFlow<NavigationRoute>(extraBufferCapacity = 1)
    private val sharedFlow = _sharedFlow.asSharedFlow()

    override suspend fun navigateTo(route: NavigationRoute) {
        _sharedFlow.emit(route)
    }

    override suspend fun toBack() {
        _sharedFlow.emit(NavigationRoute.Back)
    }

    override suspend fun manager(navController: NavHostController, scope: CoroutineScope) {
        sharedFlow.onEach {
            if (it is NavigationRoute.Back) {
                navController.popBackStack()
                return@onEach
            }

            navController.navigate(it.route)
        }.launchIn(scope)
    }
}