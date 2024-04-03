package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class FoodAndDrink(
    @SerializedName("alcohol")
    val alcohol: Alcohol?,
    @SerializedName("meals")
    val meals: Meals?
)