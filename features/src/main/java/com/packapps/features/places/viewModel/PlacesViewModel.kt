package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {
    private val _nameLiveData = MutableLiveData<List<PlaceViewData>>()
    val nameLiveData: LiveData<List<PlaceViewData>> = _nameLiveData

    init {
        fetchPlace()
    }

    private fun fetchPlace() {
        viewModelScope.launch {
            placesInteractor.getPlaces().collect { places ->
                _nameLiveData.postValue(places)
            }
        }
    }
}


