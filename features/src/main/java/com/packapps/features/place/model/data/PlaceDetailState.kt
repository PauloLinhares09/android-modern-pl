package com.packapps.features.place.model.data

import com.packapps.design.components.ErrorComponent
import com.packapps.features.place.model.PlaceDetailViewData

sealed class PlaceDetailState {
    object Loading : PlaceDetailState()
    data class Success(val place: PlaceDetailViewData) : PlaceDetailState()
    data class Failure(val error: ErrorComponent.ErrorType) : PlaceDetailState()
}
