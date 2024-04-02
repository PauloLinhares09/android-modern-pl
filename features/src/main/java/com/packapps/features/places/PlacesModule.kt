package com.packapps.features.places

import com.packapps.features.places.model.PlacesApiService
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.interactor.PlacesInteractor
import com.packapps.network.createApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val placesModule = module {

    single { createApiService(PlacesApiService::class.java) }

    single { PlacesRepository(apiService = get()) }
    single { PlacesInteractor(placesRepository = get()) }
    viewModel { PlacesViewModel(placesInteractor = get()) }
}




