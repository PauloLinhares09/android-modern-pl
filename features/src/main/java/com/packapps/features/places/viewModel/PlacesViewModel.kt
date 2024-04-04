package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.design.components.ErrorComponent
import com.packapps.design.components.toErrorComponent
import com.packapps.design.utils.ViewState
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private val _placesStateLiveData = MutableLiveData<ViewState>()
    val placesStateLiveData: LiveData<ViewState> = _placesStateLiveData

    fun fetchPlace(filterData: FilterData) {
        viewModelScope.launch {
            _placesStateLiveData.postValue(ViewState.Loading)
            try {
                placesInteractor.getPlaces(filterData.priceRange, filterData.openedNow, filterData.ll, filterData.radius).collect { places ->
                    if (places.isNullOrEmpty()){
                        _placesStateLiveData.postValue(ViewState.Failure(ErrorComponent.ErrorType.LIST_EMPTY))
                    } else {
                        _placesStateLiveData.postValue(ViewState.Success(places))
                    }

                }
            } catch (e: Exception) {
                _placesStateLiveData.postValue(ViewState.Failure(e.toErrorComponent()))
            }
        }
    }
}



