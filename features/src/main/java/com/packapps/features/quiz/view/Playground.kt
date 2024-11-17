package com.packapps.features.quiz.view

import com.packapps.business.analytics.apublic.Analytics

fun main() {
//    val builder = AnalyticsBuilder()
//
//    val event = AnalyticsBuilder.EventBuilder("hash-event-123")
//    event.setDimension("DEVICE-ID", "788222409810a")
//    event.setDimension("USER-ID", 123)
//    event.setDimension("IS-LOGGED", true)
//
//    builder.setEvent(event)
//    builder.build()

    Analytics {
        Event("hash-1233439483"){
            "DEVICE-ID" dimension "788222409810a"
            "IS-LOGGED" dimension true
            "USER-ID" dimension 213243
        }
    }




}