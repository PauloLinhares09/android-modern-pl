package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)