package com.packapps.features.dashboard

import com.packapps.features.dashboard.data.PlacesApiService
import com.packapps.features.dashboard.data.PlacesRepository
import com.packapps.features.dashboard.interactor.PlacesInteractor
import com.packapps.network.createApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val placesModule = module {

    single { createApiService(PlacesApiService::class.java) }

    single { PlacesRepository(apiService = get()) }
    single { PlacesInteractor(placesRepository = get()) }
    viewModel { PlacesViewModel(placesInteractor = get()) }
}




