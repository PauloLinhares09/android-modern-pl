package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Parking(
    @SerializedName("parking")
    val parking: ParkingX?,
    @SerializedName("private_lot")
    val privateLot: PrivateLot?,
    @SerializedName("public_lot")
    val publicLot: PublicLot?,
    @SerializedName("street_parking")
    val streetParking: StreetParking?,
    @SerializedName("valet_parking")
    val valetParking: ValetParking?
)