package com.packapps.business.analytics.impl

import android.util.Log

@DslMarker
annotation class AnalyticsDsl

@AnalyticsDsl
class Analytics {
    private val events = mutableListOf<Event>()

    fun Event(hash: String, block: Event.() -> Unit) {
        val event = Event(hash).apply(block)
        events.add(event)
    }

    private fun send() {
        events.forEach { event ->
            Log.d("TAG","Enviando evento com hash: ${event.hash}")
            event.getDimensions().forEach { dimension ->
                dimension.getAttributes().forEach { (key, value) ->
                    Log.d("TAG","Dimensão $key: $value")
                }
            }
        }
    }

    fun build() {
        send()
    }
}