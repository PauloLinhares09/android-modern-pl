package com.packapps.features.dashboard.data


import com.google.gson.annotations.SerializedName

data class GeoBounds(
    @SerializedName("circle")
    val circle: Circle?
)