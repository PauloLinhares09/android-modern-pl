package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class TipXX(
    @SerializedName("agree_count")
    val agreeCount: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("disagree_count")
    val disagreeCount: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("url")
    val url: String?
)