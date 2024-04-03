package com.packapps.features.places.model.interactor

import com.packapps.features.place.model.PlaceDetailViewData
import com.packapps.network.data.places.PlacesResponse
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.network.data.place_detail.PlaceDetailResponse
import com.packapps.network.data.places.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlacesInteractor(private val placesRepository: PlacesRepository) {
    suspend fun getPlaces(priceRange: Int?, openedNow: Boolean, ll: String, radius: Int): Flow<List<PlaceViewData>> {
        return placesRepository.getPlaces(priceRange, openedNow, ll, radius).map { placesResponse ->
            placesResponse?.results?.mapNotNull { result ->
                result?.toPlaceViewData()
            } ?: emptyList()
        }
    }

    suspend fun getPlaceDetail(id: String): Flow<PlaceDetailViewData?> {
        return placesRepository.getPlaceDetail(id).map { placeDetailResponse ->
            placeDetailResponse.toPlaceDetailViewData()
        }
    }

    private fun PlaceDetailResponse?.toPlaceDetailViewData(): PlaceDetailViewData? =
        this?.let {
            PlaceDetailViewData(
                venueName = it?.name.orEmpty(),
//                categories = it.categories.map { category -> category.name },
                categories = mutableListOf(),
                address = it?.location?.formattedAddress.orEmpty(),
                isOpenNow = it.closedBucket != "Closed", // Simplificação para determinar disponibilidade
                photos = listOf(), // Simulação, pois a API de exemplo não inclui fotos
                tips = listOf() // Simulação, pois a API de exemplo não inclui dicas
            )
        }



    private fun Result?.toPlaceViewData(): PlaceViewData {
        return PlaceViewData(
            venueProfileImage = this?.categories?.firstOrNull()?.icon?.let { icon ->
            "${icon.prefix}bg_64${icon.suffix}"
        },
            fsqId = this?.fsqId,
            venueName = this?.name,
            priceRange = null,
            userRating = null,
            distance = this?.distance
        )
    }

}
