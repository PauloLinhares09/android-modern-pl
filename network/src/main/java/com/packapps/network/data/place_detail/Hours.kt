package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Hours(
    @SerializedName("display")
    val display: String?,
    @SerializedName("is_local_holiday")
    val isLocalHoliday: Boolean?,
    @SerializedName("open_now")
    val openNow: Boolean?,
    @SerializedName("regular")
    val regular: List<Regular?>?
)