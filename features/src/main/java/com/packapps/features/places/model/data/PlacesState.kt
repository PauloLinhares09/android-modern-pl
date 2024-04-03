package com.packapps.features.places.model.data

import com.packapps.design.components.ErrorComponent

sealed class PlacesState {
    object Loading : PlacesState()
    data class Success(val places: List<PlaceViewData>) : PlacesState()
    data class Failure(val error: ErrorComponent.ErrorType) : PlacesState()
}
