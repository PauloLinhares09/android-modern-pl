package com.packapps.business.analytics.apublic.builder

import com.packapps.business.analytics.apublic.AnalyticsDsl

@AnalyticsDsl
class AnalyticsBuilder {

    private val eventBuilders = mutableListOf<EventBuilder>()

    fun Event(hash: String, block: EventBuilder.() -> Unit): EventBuilder {
        return EventBuilder(hash).apply(block).also { eventBuilders.add(it) }
    }

    fun build(): AnalyticsBuilder {
        send()
        return this
    }

    private fun send() {
        eventBuilders.forEach { event ->
            println("Event -> Hash: ${event.hash}")
            event.dimensions.forEach { (key, value) ->
                println("Dimension -> Dimension: $key, Value: $value")
            }
        }
    }

    @AnalyticsDsl
    class EventBuilder(val hash: String) {

        val dimensions = mutableMapOf<String, Any>()

        fun dimension(key: String, value: Any): EventBuilder {
            dimensions[key] = value
            return this
        }

        infix fun String.dimension(value: Any) {
            dimension(this, value)
        }
    }
}
