package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class DineIn(
    @SerializedName("essential_reservations")
    val essentialReservations: EssentialReservations?,
    @SerializedName("groups_only_reservations")
    val groupsOnlyReservations: GroupsOnlyReservations?,
    @SerializedName("online_reservations")
    val onlineReservations: OnlineReservations?,
    @SerializedName("reservations")
    val reservations: Reservations?
)