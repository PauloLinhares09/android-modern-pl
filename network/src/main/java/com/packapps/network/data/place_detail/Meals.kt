package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName("bar_snacks")
    val barSnacks: BarSnacks?,
    @SerializedName("breakfast")
    val breakfast: Breakfast?,
    @SerializedName("brunch")
    val brunch: Brunch?,
    @SerializedName("dessert")
    val dessert: Dessert?,
    @SerializedName("dinner")
    val dinner: Dinner?,
    @SerializedName("happy_hour")
    val happyHour: HappyHour?,
    @SerializedName("lunch")
    val lunch: Lunch?,
    @SerializedName("tasting_menu")
    val tastingMenu: TastingMenu?
)