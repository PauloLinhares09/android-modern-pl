package com.packapps.features.places.data.interactor

import com.packapps.network.data.places.PlacesResponse
import com.packapps.features.places.data.PlacesRepository
import kotlinx.coroutines.flow.Flow

class PlacesInteractor(private val placesRepository: PlacesRepository) {
    suspend fun getPlaces(): Flow<PlacesResponse?> {
        //Mapper here
        return placesRepository.getPlaces()
    }
}
