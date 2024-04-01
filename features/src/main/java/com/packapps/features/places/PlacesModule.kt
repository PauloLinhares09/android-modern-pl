package com.packapps.features.places

import com.packapps.features.places.data.PlacesApiService
import com.packapps.features.places.data.PlacesRepository
import com.packapps.features.places.interactor.PlacesInteractor
import com.packapps.network.createApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val placesModule = module {

    single { createApiService(PlacesApiService::class.java) }

    single { PlacesRepository(apiService = get()) }
    single { PlacesInteractor(placesRepository = get()) }
    viewModel { PlacesViewModel(placesInteractor = get()) }
}




