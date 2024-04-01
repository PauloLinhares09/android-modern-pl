package com.packapps.features.dashboard.interactor

import com.packapps.features.dashboard.data.PlacesResponse
import com.packapps.features.dashboard.data.QuizRepository
import kotlinx.coroutines.flow.Flow

class QuizInteractor(private val quizRepository: QuizRepository) {
    suspend fun getPlaces(): Flow<PlacesResponse?> {
        //Mapper here
        return quizRepository.getPlaces()
    }
}
