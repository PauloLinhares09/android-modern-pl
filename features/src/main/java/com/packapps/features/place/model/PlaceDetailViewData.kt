package com.packapps.features.place.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceDetailViewData(
    val venueName: String,
    val category: String,
    val priceRange: String,
    val userRating: Double,
    val rateCount: Int,
    val isFavorite: Boolean,
    val phoneNumber: String,
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
        val text: String,
        val url: String?,
        val lang: String,
        val agreeCount: Int,
        val disagreeCount: Int,
        val profileImage: String,
        val userName: String
    ) : Parcelable
}
