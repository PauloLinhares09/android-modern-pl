package com.packapps.features.dashboard.interactor

import com.packapps.features.dashboard.data.QuizRepository
import kotlinx.coroutines.flow.Flow

class QuizInteractor(private val quizRepository: QuizRepository) {
    suspend fun getNameFromApi(): Flow<String> {
        return quizRepository.getName()
    }
}
