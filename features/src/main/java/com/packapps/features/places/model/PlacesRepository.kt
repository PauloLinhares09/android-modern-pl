package com.packapps.features.places.model

import com.packapps.network.data.places.PlacesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepository(private val apiService: PlacesApiService) {
    suspend fun getPlaces(priceRange: Int?, openedNow: Boolean, ll: String, radius: Int): Flow<PlacesResponse?> {
        return flow {
            val places = apiService.getPlaces(priceRange, openedNow, ll, radius).body() ?: null
            emit(places)
        }
    }
}
