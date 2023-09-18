package com.example.loadinggallery.imagesScreen.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loadinggallery.model.RepoInterface
import com.example.loadinggallery.utility.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ImagesViewModel (private val _repo : RepoInterface): ViewModel() {

    private val _galleryItem : MutableStateFlow<State> = MutableStateFlow(State.Empty)
    var galleryItem : StateFlow<State> = _galleryItem

    fun getImagesFromGallery(activity: Activity) = viewModelScope.launch{
        _galleryItem.value = State.Loading
        _repo.getImagesFromGallery(activity)
            .catch {
                    e -> _galleryItem.value = State.Failure(e)
            }
            .collect{
                    data ->
                _galleryItem.value = State.Success(data)
            }
    }
}