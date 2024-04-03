package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class PlaceDetailResponse(
    @SerializedName("categories")
    val categories: List<Category>?,
    @SerializedName("chains")
    val chains: List<Chain>?,
    @SerializedName("closed_bucket")
    val closedBucket: String?,
    @SerializedName("date_closed")
    val dateClosed: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("distance")
    val distance: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fax")
    val fax: String?,
    @SerializedName("features")
    val features: Features?,
    @SerializedName("fsq_id")
    val fsqId: String?,
    @SerializedName("geocodes")
    val geocodes: Geocodes?,
    @SerializedName("hours")
    val hours: Hours?,
    @SerializedName("hours_popular")
    val hoursPopular: List<HoursPopular>?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("menu")
    val menu: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photos")
    val photos: List<Photo>?,
    @SerializedName("popularity")
    val popularity: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces?,
    @SerializedName("social_media")
    val socialMedia: SocialMedia?,
    @SerializedName("stats")
    val stats: Stats?,
    @SerializedName("store_id")
    val storeId: String?,
    @SerializedName("tastes")
    val tastes: List<String>?,
    @SerializedName("tel")
    val tel: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("tips")
    val tips: List<TipXX>?,
    @SerializedName("venue_reality_bucket")
    val venueRealityBucket: String?,
    @SerializedName("verified")
    val verified: Boolean?,
    @SerializedName("website")
    val website: String?
)