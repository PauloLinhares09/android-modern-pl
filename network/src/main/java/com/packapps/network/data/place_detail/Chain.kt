package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Chain(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)