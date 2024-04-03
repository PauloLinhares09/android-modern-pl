package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Features(
    @SerializedName("amenities")
    val amenities: Amenities?,
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("food_and_drink")
    val foodAndDrink: FoodAndDrink?,
    @SerializedName("payment")
    val payment: Payment?,
    @SerializedName("services")
    val services: Services?
)