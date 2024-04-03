package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Alcohol(
    @SerializedName("bar_service")
    val barService: BarService?,
    @SerializedName("beer")
    val beer: Beer?,
    @SerializedName("byo")
    val byo: Byo?,
    @SerializedName("cocktails")
    val cocktails: Cocktails?,
    @SerializedName("full_bar")
    val fullBar: FullBar?,
    @SerializedName("wine")
    val wine: Wine?
)