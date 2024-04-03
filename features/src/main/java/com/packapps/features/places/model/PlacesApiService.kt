package com.packapps.features.places.model

import com.packapps.network.data.place_detail.Photo
import com.packapps.network.data.place_detail.PlaceDetailResponse
import com.packapps.network.data.place_detail.TipXX
import com.packapps.network.data.places.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApiService {

    @GET("v3/places/search")
    suspend fun getPlaces(
        @Query("min_price") priceRange: Int?, //1 to 4
        @Query("open_now") openedNow: Boolean,
        @Query("ll") ll: String,
        @Query("radius") radius: Int = 100000,
    ): Response<PlacesResponse>

    @GET("v3/places/{fsqId}")
    suspend fun getPlaceDetails(
        @Path("fsqId") fsqId: String
    ): Response<PlaceDetailResponse>

    @GET("v3/places/{fsqId}/photos")
    suspend fun getPlacePhotos(
        @Path("fsqId") fsqId: String
    ): Response<List<Photo>>

    @GET("v3/places/{fsqId}/tips")
    suspend fun getPlaceTips(
        @Path("fsqId") fsqId: String
    ): Response<List<TipXX>>

}
