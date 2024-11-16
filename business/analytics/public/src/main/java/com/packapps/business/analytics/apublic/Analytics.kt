package com.packapps.business.analytics.apublic

import com.packapps.business.analytics.impl.Analytics
import com.packapps.business.analytics.impl.AnalyticsDsl


@AnalyticsDsl
fun Analytics(autoSend: Boolean = true, block: Analytics.() -> Unit) : Analytics {
    return if (autoSend)
        Analytics().apply(block).send()
    else
        Analytics().apply(block)
}
