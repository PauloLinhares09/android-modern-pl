package com.packapps.features.places.model.interactor

import com.packapps.network.data.places.PlacesResponse
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.network.data.places.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlacesInteractor(private val placesRepository: PlacesRepository) {
    suspend fun getPlaces(): Flow<List<PlaceViewData>> {
        return placesRepository.getPlaces().map { placesResponse ->
            placesResponse?.results?.mapNotNull { result ->
                result?.toPlaceViewData()
            } ?: emptyList()
        }
    }


    private fun Result?.toPlaceViewData(): PlaceViewData {
        return PlaceViewData(
            venueProfileImage = null,
            venueName = this?.name,
            priceRange = null,
            userRating = null,
            distance = this?.distance
        )
    }

}
