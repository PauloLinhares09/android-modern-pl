package com.packapps.features.dashboard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizRepository(private val apiService: QuizApiService) {
    suspend fun getName(): Flow<String> {
        return flow {
            val name = apiService.getNameFromApi().body() ?: ""
            emit(name)
        }
    }
}
