package com.packapps.network

import android.util.Log
import com.packapps.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


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

