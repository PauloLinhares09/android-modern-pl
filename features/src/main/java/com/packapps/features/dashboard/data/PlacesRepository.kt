package com.packapps.features.dashboard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepository(private val apiService: FourSquareApiService) {
    suspend fun getPlaces(): Flow<PlacesResponse?> {
        return flow {
            val places = apiService.getPlaces().body() ?: null
            emit(places)
        }
    }
}
