package com.packapps.network.data.places


import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("main")
    val main: Main?,
    @SerializedName("roof")
    val roof: Roof?
)