package com.packapps.design.utils

import com.packapps.design.components.ErrorComponent

sealed class ViewState {
    object Loading : ViewState()
    data class Failure(val error: ErrorComponent.ErrorType) : ViewState()
    data class Success<T>(val data: T) : ViewState()
}



