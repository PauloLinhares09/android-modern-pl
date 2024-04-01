package com.packapps.network.data.places


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("geo_bounds")
    val geoBounds: GeoBounds?
)