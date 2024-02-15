package com.example.navigator

sealed class NavigationRoute(
    val route: String,
    val params: Map<String, Any> = emptyMap()
) {
    data object Back: NavigationRoute("")
    data object PokeList: NavigationRoute("/pokelist")
    data object Location: NavigationRoute("/locations")
    class PokeDetail(params: Map<String, Any> = emptyMap()): NavigationRoute("/pokelist/{id}", params)
}