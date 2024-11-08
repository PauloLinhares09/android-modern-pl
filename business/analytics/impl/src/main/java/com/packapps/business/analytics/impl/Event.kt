package com.packapps.business.analytics.impl

@AnalyticsDsl
class Event(val hash: String) {
    private val dimensions = mutableListOf<Dimension>()

    fun Dimension(block: Dimension.() -> Unit) {
        val dimension = Dimension().apply(block)
        dimensions.add(dimension)
    }

    fun getDimensions() = dimensions
}