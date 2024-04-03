package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Roof(
    @SerializedName("latitude")
    val latitude: Int?,
    @SerializedName("longitude")
    val longitude: Int?
)