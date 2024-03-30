package com.packapps.features.dashboard.data

import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {
    @GET("nameEndpoint")
    suspend fun getNameFromApi(): Response<String>
}