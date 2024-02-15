package com.example.pokefriend.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navigator.NavigationRoute

data class BottomBarItem(
    @StringRes val label: Int,
    val name: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: NavigationRoute
)