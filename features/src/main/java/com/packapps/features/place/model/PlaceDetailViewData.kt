package com.packapps.features.place.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceDetailViewData(
    val venueName: String,
    val categories: List<String>,
    val priceRange: String? = null,
    val userRating: Double? = null,
    val rateCount: Int? = null,
    val isFavorite: Boolean = false,

    val phoneNumber: String? = null,
    val address: String,
    val isOpenNow: Boolean,
    val photos: List<Photo>,
    val tips: List<Tip>
) : Parcelable {

    @Parcelize
    data class Photo(
        val id: String,
        val createdAt: String,
        val prefix: String,
        val suffix: String,
        val width: Int,
        val height: Int
    ) : Parcelable

    @Parcelize
    data class Tip(
        val id: String,
        val createdAt: String,
        val text: String
    ) : Parcelable
}
