package com.example.loadinggallery.videosScreen.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loadinggallery.model.RepoInterface
import com.example.loadinggallery.utility.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VideoViewModel  (private val _repo : RepoInterface): ViewModel() {

    private val _galleryItem : MutableStateFlow<State> = MutableStateFlow(State.Empty)
    var galleryItem : StateFlow<State> = _galleryItem

    fun getVideosFromGallery(activity: Activity) = viewModelScope.launch{
       _galleryItem.value = State.Loading
        _repo.getVideosFromGallery(activity)
            .catch {
                e -> _galleryItem.value = State.Failure(e)
            }
            .collect{
                data ->
                _galleryItem.value = State.Success(data)
            }
    }
}