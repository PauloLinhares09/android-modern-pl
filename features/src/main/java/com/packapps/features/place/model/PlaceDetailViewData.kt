package com.packapps.features.place.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceDetailViewData(
    val title: String,
    val description: String
) : Parcelable