package com.packapps.features.places.data

import com.packapps.network.data.places.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("v3/places/search")
    suspend fun getPlaces(
        @Query("ll") ll: String = "40.748817,-73.985428",
    ): Response<PlacesResponse>

}
