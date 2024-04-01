package com.packapps.features.dashboard.data


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("geo_bounds")
    val geoBounds: GeoBounds?
)