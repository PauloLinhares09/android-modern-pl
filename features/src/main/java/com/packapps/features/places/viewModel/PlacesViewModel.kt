package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.data.PlacesState
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private val _placesStateLiveData = MutableLiveData<PlacesState>()
    val placesStateLiveData: LiveData<PlacesState> = _placesStateLiveData

    fun fetchPlace(filterData: FilterData) {
        viewModelScope.launch {
            _placesStateLiveData.postValue(PlacesState.Loading)
            try {
                placesInteractor.getPlaces(filterData.priceRange, filterData.openedNow, filterData.ll, filterData.radius).collect { places ->
                    _placesStateLiveData.postValue(PlacesState.Success(places))
                }
            } catch (e: Exception) {
                _placesStateLiveData.postValue(PlacesState.Failure(e))
            }
        }
    }
}


