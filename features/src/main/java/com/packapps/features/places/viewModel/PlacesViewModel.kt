package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {
    private val _placeListLiveData = MutableLiveData<List<PlaceViewData>>()
    val placesListLiveData: LiveData<List<PlaceViewData>> = _placeListLiveData

    init {
        fetchPlace()
    }

    fun fetchPlace(priceRange: Int? = null, openedNow: Boolean = false, ll: String = "", radius: Int = 100000) {
        viewModelScope.launch {
            placesInteractor.getPlaces(priceRange, openedNow, ll, radius).collect { places ->
                _placeListLiveData.postValue(places)
            }
        }
    }
}


