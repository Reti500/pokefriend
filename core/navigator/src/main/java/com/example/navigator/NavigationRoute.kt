package com.example.navigator

sealed class NavigationRoute(
    val route: String,
) {
    data object Back: NavigationRoute("")
    data object PokeList: NavigationRoute("/pokelist")
}