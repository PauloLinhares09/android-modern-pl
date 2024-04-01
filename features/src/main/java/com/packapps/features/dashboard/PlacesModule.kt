package com.packapps.features.dashboard

import android.util.Log
import com.packapps.core.BuildConfig
import com.packapps.features.dashboard.data.FourSquareApiService
import com.packapps.features.dashboard.data.PlacesRepository
import com.packapps.features.dashboard.interactor.PlacesInteractor
import com.packapps.network.provideHeaderInterceptor
import com.packapps.network.provideHubOkHttpClient
import com.packapps.network.provideLoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val placesModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHubOkHttpClient(provideHeaderInterceptor(), provideLoggingInterceptor()))
            .build()
            .create(FourSquareApiService::class.java)
    }

    single { PlacesRepository(apiService = get()) }
    single { PlacesInteractor(placesRepository = get()) }
    viewModel { PlacesViewModel(placesInteractor = get()) }
}



