package com.packapps.features.places.model.interactor

import com.packapps.features.place.model.PlaceDetailViewData
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.network.data.place_detail.Photo

import com.packapps.network.data.place_detail.PlaceDetailResponse
import com.packapps.network.data.place_detail.TipXX

import com.packapps.network.data.places.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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
        val placeDetailsFlow = placesRepository.getPlaceDetail(id)
        val placePhotosFlow = placesRepository.getPlacePhotos(id)
        val placeTipsFlow = placesRepository.getPlaceTips(id)

        return combine(placeDetailsFlow, placePhotosFlow, placeTipsFlow) { placeDetail, photos, tips ->
            placeDetail?.toPlaceDetailViewData(photos.orEmpty(), tips.orEmpty())
        }
    }

    private fun PlaceDetailResponse.toPlaceDetailViewData(photos: List<Photo>, tips: List<TipXX>): PlaceDetailViewData =
        PlaceDetailViewData(
            venueName = name.orEmpty(),
//            priceRange = "Category • ${price?.toString()} • ★ $popularity (${rating})", //Category • $ • ★ 4.5 (200)
            priceRange = "Category • \$ • ★ 4.5 (200)",
            categories = categories?.mapNotNull { it?.name.orEmpty() }.orEmpty(),
            address = location?.formattedAddress.orEmpty(),
            isOpenNow = closedBucket != "Closed",
            photos = photos.map { photo ->
                PlaceDetailViewData.Photo(
                    id = photo.id.orEmpty(),
                    createdAt = photo.createdAt.orEmpty(),
                    prefix = photo.prefix.orEmpty(),
                    suffix = photo.suffix.orEmpty(),
                    width = photo.width ?: 0,
                    height = photo.height ?: 0
                )
            },
            tips = tips.map { tip ->
                PlaceDetailViewData.Tip(
                    id = tip.id.orEmpty(),
                    createdAt = tip.createdAt.orEmpty(),
                    text = tip.text.orEmpty()
                )
            }
        )




    private fun Result?.toPlaceViewData(): PlaceViewData {
        return PlaceViewData(
            venueProfileImage = this?.categories?.firstOrNull()?.icon?.let { icon ->
            "${icon.prefix}bg_64${icon.suffix}"
        },
            fsqId = this?.fsqId,
            id = this?.categories?.firstOrNull()?.id,
            venueName = this?.name,
            shortName = this?.categories?.firstOrNull()?.shortName,
            priceRange = null,
            userRating = null,
            distance = this?.distance
        )
    }

}
