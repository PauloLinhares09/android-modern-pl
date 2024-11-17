package com.packapps.business.analytics.apublic

import com.packapps.business.analytics.apublic.builder.AnalyticsBuilder

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class AnalyticsDsl


fun Analytics(block: AnalyticsBuilder.() -> Unit): AnalyticsBuilder {
    return AnalyticsBuilder().apply(block).build()
}
