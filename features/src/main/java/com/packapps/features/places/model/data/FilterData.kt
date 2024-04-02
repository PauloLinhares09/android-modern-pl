package com.packapps.features.places.model.data

data class FilterData (
    var priceRange: Int,
    var openedNow: Boolean,
    var ll: String,
    var radius: Int
    )