package com.packapps.features.places.model.data

sealed class PlacesState {
    object Loading : PlacesState()
    data class Success(val places: List<PlaceViewData>) : PlacesState()
    data class Failure(val error: Throwable) : PlacesState()
}
