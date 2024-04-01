package com.packapps.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packapps.features.dashboard.interactor.QuizInteractor
import kotlinx.coroutines.launch

class QuizViewModel(private val quizInteractor: QuizInteractor) : ViewModel() {
    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData: LiveData<String> = _nameLiveData

    init {
        fetchPlace()
    }

    private fun fetchPlace() {
        viewModelScope.launch {
            quizInteractor.getPlaces().collect { places ->
                _nameLiveData.postValue(places?.context?.geoBounds?.toString())
            }
        }
    }
}


