package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address")
    val address: String?,
    @SerializedName("address_extended")
    val addressExtended: String?,
    @SerializedName("admin_region")
    val adminRegion: String?,
    @SerializedName("census_block")
    val censusBlock: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("cross_street")
    val crossStreet: String?,
    @SerializedName("dma")
    val dma: String?,
    @SerializedName("formatted_address")
    val formattedAddress: String?,
    @SerializedName("locality")
    val locality: String?,
    @SerializedName("neighborhood")
    val neighborhood: List<String?>?,
    @SerializedName("po_box")
    val poBox: String?,
    @SerializedName("post_town")
    val postTown: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("region")
    val region: String?
)