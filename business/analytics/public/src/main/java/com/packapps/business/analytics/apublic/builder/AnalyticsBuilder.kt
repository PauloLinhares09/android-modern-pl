package com.packapps.business.analytics.apublic.builder

class AnalyticsBuilder {

    private val eventBuilders = mutableListOf<EventBuilder>()

    fun setEvent(eventBuilder: EventBuilder): EventBuilder {
        this.eventBuilders.add(eventBuilder)
        return eventBuilder
    }

    fun build() : AnalyticsBuilder{
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


    class EventBuilder(val hash: String) {

        val dimensions = mutableMapOf<String, Any>()

        fun setDimension(dimension: String, value: Any) : EventBuilder {
            dimensions[dimension] = value
            return this
        }

        infix fun String.dimension(value:  Any) {
           setDimension(this, value)
        }
    }
}

