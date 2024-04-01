package com.packapps.network.data.places


import com.google.gson.annotations.SerializedName

data class GeoBounds(
    @SerializedName("circle")
    val circle: Circle?
)