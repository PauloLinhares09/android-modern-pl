package com.packapps.features.places.model.interactor

import com.packapps.features.place.model.PlaceDetailViewData
import com.packapps.network.data.places.PlacesResponse
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.data.PlaceViewData
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
            placeDetailResponse?.let {
                PlaceDetailViewData(
                    venueName = it.name ?: "",
                    category = it.categories?.firstOrNull()?.name ?: "N/A",
                    priceRange = it.price?.let { price -> "$".repeat(price) } ?: "N/A",
                    userRating = it.rating?.toDouble() ?: 0.0,
                    rateCount = it.stats?.totalRatings ?: 0,
                    isFavorite = false, // Este valor pode ser determinado pela lógica de negócios ou pelo estado de UI
                    phoneNumber = it.tel ?: "N/A",
                    address = it.location?.formattedAddress ?: "N/A",
                    isOpenNow = it.hours?.openNow ?: false,
                    photos = it.photos!!.map { photo ->
                        PlaceDetailViewData.Photo(
                            id = photo?.id.orEmpty(),
                            createdAt = photo?.createdAt.orEmpty(),
                            prefix = photo?.prefix.orEmpty(),
                            suffix = photo?.suffix.orEmpty(),
                            width = photo?.width ?: 0,
                            height = photo?.height ?: 0
                        )
                    },
                    tips = it.tips!!.map { tip ->
                        PlaceDetailViewData.Tip(
                            id = tip?.id.orEmpty(),
                            createdAt = tip?.createdAt.orEmpty(),
                            text = tip?.text.orEmpty(),
                            url = tip?.url.orEmpty(),
                            lang = tip?.lang.orEmpty(),
                            agreeCount = tip?.agreeCount ?: 0,
                            disagreeCount = tip?.disagreeCount ?: 0,
                            profileImage = "", // Você precisará adaptar essa parte, já que o exemplo de JSON não inclui imagens de perfil diretas nas dicas
                            userName = "User" // Similarmente, o nome do usuário precisará ser adaptado conforme a sua estrutura de dados
                        )
                    }
                )
            }
        }
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
