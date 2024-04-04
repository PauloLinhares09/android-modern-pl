package com.packapps.features.places.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PlaceViewData(
    val fsqId: String?,
    val id: Int?,
    val venueProfileImage: String?,
    val venueName: String?,
    val shortName: String?,
    val priceRange: String?,
    val userRating: String?,
    val distance: Int?
) : Parcelable
