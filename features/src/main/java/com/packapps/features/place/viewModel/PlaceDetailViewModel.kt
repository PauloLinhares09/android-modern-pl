package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.design.components.ErrorComponent
import com.packapps.design.components.toErrorComponent
import com.packapps.design.utils.ViewState
import com.packapps.features.places.model.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlaceDetailViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private val _placesStateLiveData = MutableLiveData<ViewState>()
    val placeDetailStateLiveData: LiveData<ViewState> = _placesStateLiveData

    fun fetchPlaceDetail(id: String) {
        viewModelScope.launch {
            _placesStateLiveData.postValue(ViewState.Loading)
            try {
                placesInteractor.getPlaceDetail(id).collect { place ->
                    if (place == null) {
                        _placesStateLiveData.postValue(ViewState.Failure(ErrorComponent.ErrorType.LIST_EMPTY))
                    } else {
                        _placesStateLiveData.postValue(ViewState.Success(place))
                    }
                }
            } catch (e: Exception) {
                _placesStateLiveData.postValue(ViewState.Failure(e.toErrorComponent()))
            }
        }
    }

}



