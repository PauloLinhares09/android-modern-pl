package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.design.components.ErrorComponent
import com.packapps.design.components.toErrorComponent
import com.packapps.features.place.model.data.PlaceDetailState
import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.data.PlacesState
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.EmptyStackException

class PlaceDetailViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private val _placesStateLiveData = MutableLiveData<PlaceDetailState>()
    val placeDetailStateLiveData: LiveData<PlaceDetailState> = _placesStateLiveData

    fun fetchPlaceDetail(id: String) {
        viewModelScope.launch {
            _placesStateLiveData.postValue(PlaceDetailState.Loading)
            try {
                placesInteractor.getPlaceDetail(id).collect { place ->
                    if (place == null) {
                        _placesStateLiveData.postValue(PlaceDetailState.Failure(ErrorComponent.ErrorType.LIST_EMPTY))
                    } else {
                        _placesStateLiveData.postValue(PlaceDetailState.Success(place))
                    }

                }
            } catch (e: Exception) {
                _placesStateLiveData.postValue(PlaceDetailState.Failure(e.toErrorComponent()))
            }
        }
    }
}



