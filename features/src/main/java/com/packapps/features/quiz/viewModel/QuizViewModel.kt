package com.packapps.features.quiz.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This could be a Dynamic Delivery Module"
    }
    val text: LiveData<String> = _text
}