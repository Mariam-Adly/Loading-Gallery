package com.example.loadinggallery.videosScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loadinggallery.model.RepoInterface

class VideoViewModelFactory (private val repo : RepoInterface) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(VideoViewModel::class.java)){
            VideoViewModel(repo) as T
        }else{
            throw IllegalArgumentException("view model class not found")
        }
    }
}