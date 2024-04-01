package com.packapps.features.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.places.data.interactor.PlacesInteractor
import kotlinx.coroutines.launch

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {
    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData: LiveData<String> = _nameLiveData

    init {
        fetchPlace()
    }

    private fun fetchPlace() {
        viewModelScope.launch {
            placesInteractor.getPlaces().collect { places ->
                _nameLiveData.postValue(places?.context?.geoBounds?.toString())
            }
        }
    }
}


