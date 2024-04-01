package com.packapps.features.dashboard.interactor

import com.packapps.features.dashboard.data.PlacesResponse
import com.packapps.features.dashboard.data.PlacesRepository
import kotlinx.coroutines.flow.Flow

class PlacesInteractor(private val placesRepository: PlacesRepository) {
    suspend fun getPlaces(): Flow<PlacesResponse?> {
        //Mapper here
        return placesRepository.getPlaces()
    }
}
