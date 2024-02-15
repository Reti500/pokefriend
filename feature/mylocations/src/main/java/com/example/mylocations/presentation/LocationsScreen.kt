package com.example.mylocations.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mylocations.presentation.components.LocationCard

@Composable
fun LocationScreen() {
    val viewModel = hiltViewModel<LocationsViewModel>()
    viewModel.onEvent(LocationsEvent.OnLoadLocations)

    Page(
        state = viewModel.state.collectAsState(),
        onEvent = viewModel::onEvent
    )
}

@Composable
fun Page(
    state: State<LocationState>,
    onEvent: (e: LocationsEvent) -> Unit
) {
    LazyColumn() {
        state.value.locations.forEachIndexed { index, myLocation ->
            item { LocationCard(index+1, myLocation.lat, myLocation.lng) }
        }
    }
}