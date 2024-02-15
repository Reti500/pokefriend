package com.example.mylocations.domain.usecases

import com.example.firebase.data.MyLocation

interface GetAllLocations {
    suspend operator fun invoke(): List<MyLocation>
}