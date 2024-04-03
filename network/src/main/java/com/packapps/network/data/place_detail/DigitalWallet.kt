package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class DigitalWallet(
    @SerializedName("accepts_nfc")
    val acceptsNfc: AcceptsNfc?
)