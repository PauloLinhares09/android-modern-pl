package com.packapps.features.dashboard

import android.util.Log
import com.packapps.core.BuildConfig
import com.packapps.features.dashboard.data.FourSquareApiService
import com.packapps.features.dashboard.data.QuizRepository
import com.packapps.features.dashboard.interactor.QuizInteractor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val quizModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHubOkHttpClient(provideHeaderInterceptor(), provideLoggingInterceptor()))
            .build()
            .create(FourSquareApiService::class.java)
    }

    single { QuizRepository(apiService = get()) }
    single { QuizInteractor(quizRepository = get()) }
    viewModel { QuizViewModel(quizInteractor = get()) }
}


fun provideHubOkHttpClient(
    interceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient? {
    val httpClient = OkHttpClient.Builder()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.addInterceptor(interceptor)
    httpClient.addInterceptor(loggingInterceptor)
    httpClient
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)
    return httpClient.build()
}

fun provideHeaderInterceptor(): Interceptor {
    return Interceptor { chain: Interceptor.Chain ->
        var request = chain.request()

        val requestBuilder = request.newBuilder()
            .header("Authorization", BuildConfig.API_KEY)
            .header("x-consumer-system", "Mobile")

        request = requestBuilder.build()
        chain.proceed(request)
    }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor { message: String ->
        Log.d("HTTP INTERCEPTOR",
            message
        )
    }
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}

