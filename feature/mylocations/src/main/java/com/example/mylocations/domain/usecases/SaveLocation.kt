package com.example.mylocations.domain.usecases

import com.example.firebase.data.MyLocation

interface SaveLocation {
    suspend operator fun invoke(location:MyLocation)
}