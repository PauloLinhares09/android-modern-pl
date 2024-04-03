package com.packapps.features.places.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PlaceViewData(
    val fsqId: String?,
    val id: Int?,
    val venueProfileImage: String?,
    val venueName: String?,
    val priceRange: String?,
    val userRating: Double?,
    val distance: Int?
) : Parcelable
