package com.packapps.features.dashboard

import com.packapps.features.dashboard.data.FourSquareApiService
import com.packapps.features.dashboard.data.QuizRepository
import com.packapps.features.dashboard.interactor.QuizInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val quizModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("Your API Base URL")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FourSquareApiService::class.java)
    }

    single { QuizRepository(apiService = get()) }
    single { QuizInteractor(quizRepository = get()) }
    viewModel { QuizViewModel(quizInteractor = get()) }
}
