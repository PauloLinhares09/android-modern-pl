package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Services(
    @SerializedName("delivery")
    val delivery: Delivery?,
    @SerializedName("dine_in")
    val dineIn: DineIn?,
    @SerializedName("drive_through")
    val driveThrough: DriveThrough?,
    @SerializedName("takeout")
    val takeout: Takeout?
)