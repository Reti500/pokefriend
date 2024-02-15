package com.example.mylocations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylocations.domain.usecases.GetAllLocations
import com.example.mylocations.domain.usecases.SaveLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val saveLocation: SaveLocation,
    private val getAllLocations: GetAllLocations
): ViewModel() {

    private val _state = MutableStateFlow(LocationState())
    val state: StateFlow<LocationState> = _state.asStateFlow()

    fun onEvent(e: LocationsEvent) {
        when(e) {
            LocationsEvent.OnLoadLocations -> {
                viewModelScope.launch {
                    val locations = getAllLocations()
                    _state.update {
                        it.copy(locations = locations)
                    }
                }
            }
        }
    }
}