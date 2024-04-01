package com.packapps.network.data.places


import com.google.gson.annotations.SerializedName

data class Chain(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)