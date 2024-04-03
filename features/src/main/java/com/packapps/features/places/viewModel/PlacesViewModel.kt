package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.design.components.ErrorComponent
import com.packapps.design.components.toErrorComponent
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.data.PlacesState
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.EmptyStackException

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private val _placesStateLiveData = MutableLiveData<PlacesState>()
    val placesStateLiveData: LiveData<PlacesState> = _placesStateLiveData

    fun fetchPlace(filterData: FilterData) {
        viewModelScope.launch {
            _placesStateLiveData.postValue(PlacesState.Loading)
            try {
                placesInteractor.getPlaces(filterData.priceRange, filterData.openedNow, filterData.ll, filterData.radius).collect { places ->
                    if (places.isNullOrEmpty()){
                        _placesStateLiveData.postValue(PlacesState.Failure(ErrorComponent.ErrorType.LIST_EMPTY))
                    } else {
                        _placesStateLiveData.postValue(PlacesState.Success(places))
                    }

                }
            } catch (e: Exception) {
                _placesStateLiveData.postValue(PlacesState.Failure(e.toErrorComponent()))
            }
        }
    }
}



