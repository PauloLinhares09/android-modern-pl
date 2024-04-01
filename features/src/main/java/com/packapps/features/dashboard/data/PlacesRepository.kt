package com.packapps.features.dashboard.data

import com.packapps.network.FourSquareApiService
import com.packapps.network.data.places.PlacesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepository(private val apiService: PlacesApiService) {
    suspend fun getPlaces(): Flow<PlacesResponse?> {
        return flow {
            val places = apiService.getPlaces().body() ?: null
            emit(places)
        }
    }
}
