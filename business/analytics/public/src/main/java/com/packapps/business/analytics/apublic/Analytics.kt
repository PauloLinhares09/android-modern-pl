package com.packapps.business.analytics.apublic

import com.packapps.business.analytics.impl.Analytics


fun Analytics(block: Analytics.() -> Unit) {
    Analytics().apply(block).build()
}
