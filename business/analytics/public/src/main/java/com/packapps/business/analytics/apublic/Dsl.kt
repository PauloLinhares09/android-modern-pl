package com.packapps.business.analytics.apublic

import com.packapps.business.analytics.apublic.builder.AnalyticsBuilder

@DslMarker
annotation class AnalyticsDsl


fun Analytics(block : AnalyticsBuilder.() -> Unit) : AnalyticsBuilder {
    return AnalyticsBuilder().apply(block).build()
}

@AnalyticsDsl
fun AnalyticsBuilder.Event(hash: String, block: AnalyticsBuilder.EventBuilder.() -> Unit) : AnalyticsBuilder.EventBuilder {
    return this.setEvent(AnalyticsBuilder.EventBuilder(hash).apply(block))

}
