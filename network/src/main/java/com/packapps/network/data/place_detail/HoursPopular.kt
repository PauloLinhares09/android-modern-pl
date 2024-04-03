package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class HoursPopular(
    @SerializedName("close")
    val close: String?,
    @SerializedName("day")
    val day: Int?,
    @SerializedName("open")
    val `open`: String?
)