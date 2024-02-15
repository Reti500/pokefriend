package com.example.pokefriend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.datastore.DataStoreManager
import com.example.core.injections.dataStore
import com.example.location.LocationService
import com.example.mylocations.presentation.LocationScreen
import com.example.navigator.AppNavigator
import com.example.navigator.NavigationRoute
import com.example.pokefriend.data.BottomBarItem
import com.example.pokefriend.ui.components.BottomAppBarItem
import com.example.pokefriend.ui.components.Permissions
import com.example.pokefriend.ui.theme.PokeFriendTheme
import com.example.pokelist.presentation.detail.PokemonDetailScreen
import com.example.pokelist.presentation.list.PokeListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    @Inject lateinit var settings: DataStoreManager

    private val bottomBarItems = listOf(
        BottomBarItem(
            label = R.string.pokemon,
            name = "Home",
            selectedIcon = Icons.Default.Home,
            unSelectedIcon = Icons.Outlined.Home,
            route = NavigationRoute.PokeList
        ),
        BottomBarItem(
            label = R.string.locations,
            name = "Locations",
            selectedIcon = Icons.Default.LocationOn,
            unSelectedIcon = Icons.Outlined.LocationOn,
            route = NavigationRoute.Location
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
        }

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            var selectedItem by remember {
                mutableStateOf(bottomBarItems.first().name)
            }

            runBlocking {
                if (settings.getCurrentUser().isEmpty()) {
                    settings.setCurrentUser(UUID.randomUUID().toString())
                }
            }

            PokeFriendTheme {
                Permissions()

                LaunchedEffect("navigation") {
                    navigator.manager(navController, this)
                }

                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                bottomBarItems.forEach {
                                    BottomAppBarItem(
                                        modifier = Modifier.weight(1f),
                                        item = it,
                                        selectedItem = selectedItem) { item ->
                                        selectedItem = item.name
                                        scope.launch {
                                            navigator.navigateTo(item.route)
                                        }
                                    }
                                }
                            }
                        }
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .navigationBarsPadding()
                            .imePadding()
                            .padding(padding)
                    ) {
                        MainNavigationComponent(
                            navController = navController,
                            startDestination = NavigationRoute.PokeList
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavigationComponent(
    navController: NavHostController,
    startDestination: NavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(NavigationRoute.Location.route) { LocationScreen() }
        composable(NavigationRoute.PokeList.route) { PokeListScreen() }
        composable(
            NavigationRoute.PokeDetail().route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            PokemonDetailScreen(backStackEntry.arguments?.getInt("id") ?: 0)
        }
    }
}