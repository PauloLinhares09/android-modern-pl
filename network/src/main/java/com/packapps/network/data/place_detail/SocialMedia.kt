package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class SocialMedia(
    @SerializedName("facebook_id")
    val facebookId: String?,
    @SerializedName("instagram")
    val instagram: String?,
    @SerializedName("twitter")
    val twitter: String?
)