package com.example.loadinggallery.utility

import com.example.loadinggallery.model.Pojo

sealed class State {
    class Success(val data: List<Pojo>) : State()
    class Failure(val msg: Throwable): State()
    object Loading : State()
    object Empty :  State()
}