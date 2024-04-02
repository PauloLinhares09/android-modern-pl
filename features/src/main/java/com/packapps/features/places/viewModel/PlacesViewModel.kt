package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {
    private val _placeListLiveData = MutableLiveData<List<PlaceViewData>>()
    val placesListLiveData: LiveData<List<PlaceViewData>> = _placeListLiveData

    fun fetchPlace(filterData: FilterData) {
        viewModelScope.launch {
            placesInteractor.getPlaces(filterData.priceRange, filterData.openedNow, filterData.ll, filterData.radius).collect { places ->
                _placeListLiveData.postValue(places)
            }
        }
    }
}


