package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Amenities(
    @SerializedName("atm")
    val atm: Atm?,
    @SerializedName("coat_check")
    val coatCheck: CoatCheck?,
    @SerializedName("jukebox")
    val jukebox: Jukebox?,
    @SerializedName("live_music")
    val liveMusic: LiveMusic?,
    @SerializedName("music")
    val music: Music?,
    @SerializedName("outdoor_seating")
    val outdoorSeating: OutdoorSeating?,
    @SerializedName("parking")
    val parking: Parking?,
    @SerializedName("private_room")
    val privateRoom: PrivateRoom?,
    @SerializedName("restroom")
    val restroom: Restroom?,
    @SerializedName("sit_down_dining")
    val sitDownDining: SitDownDining?,
    @SerializedName("smoking")
    val smoking: Smoking?,
    @SerializedName("tvs")
    val tvs: Tvs?,
    @SerializedName("wheelchair_accessible")
    val wheelchairAccessible: WheelchairAccessible?,
    @SerializedName("wifi")
    val wifi: String?
)