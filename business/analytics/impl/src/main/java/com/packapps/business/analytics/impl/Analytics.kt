package com.packapps.business.analytics.impl

import android.util.Log

@AnalyticsDsl
class Analytics {
    private val events = mutableListOf<Event>()

    fun Analytics.Event(hash: String, block: Event.() -> Unit) {
        val event = Event(hash).apply(block)
        events.add(event)
    }

    fun send() : Analytics {
        events.forEach { event ->
//            Log.d("TAG","Enviando evento com hash: ${event.hash}")
            println("Enviando evento com hash: ${event.hash}")
            event.getDimensions().forEach { (key, value) ->
//                    Log.d("TAG","Dimensão $key: $value")
                println("Dimensão $key: $value")
            }
        }

        return this
    }
}