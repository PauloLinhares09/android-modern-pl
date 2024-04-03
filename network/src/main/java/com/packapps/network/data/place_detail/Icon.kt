package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("classifications")
    val classifications: List<String>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("suffix")
    val suffix: String?,
    @SerializedName("tip")
    val tip: TipXX?,
    @SerializedName("width")
    val width: Int?
)