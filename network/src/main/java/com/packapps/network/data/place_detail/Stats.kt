package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("total_photos")
    val totalPhotos: Int?,
    @SerializedName("total_ratings")
    val totalRatings: Int?,
    @SerializedName("total_tips")
    val totalTips: Int?
)