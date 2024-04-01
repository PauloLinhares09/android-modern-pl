package com.packapps.features.dashboard.data


import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("main")
    val main: Main?,
    @SerializedName("roof")
    val roof: Roof?
)