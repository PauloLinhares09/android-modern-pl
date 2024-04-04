package com.packapps.android_modern_pl

import android.app.Application
import com.packapps.features.notifications.cassinoModule
import com.packapps.features.places.placesModule
import com.packapps.features.quiz.quizModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(placesModule, cassinoModule, quizModule))
        }

    }
}