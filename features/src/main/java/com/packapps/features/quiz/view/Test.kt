package com.packapps.features.quiz.view

import com.packapps.business.analytics.apublic.Analytics

fun main() {

    Analytics {
        Event("hash 12345") {
            "device-id" Dimension "1237493lksjdwd"
        }
    }

}