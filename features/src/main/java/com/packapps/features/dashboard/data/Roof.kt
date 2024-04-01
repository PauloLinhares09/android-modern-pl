package com.packapps.features.dashboard.data


import com.google.gson.annotations.SerializedName

data class Roof(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)