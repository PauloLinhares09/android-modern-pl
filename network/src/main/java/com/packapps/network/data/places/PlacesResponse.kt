package com.packapps.network.data.places


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("context")
    val context: Context?,
    @SerializedName("results")
    val results: List<Result?>?
)