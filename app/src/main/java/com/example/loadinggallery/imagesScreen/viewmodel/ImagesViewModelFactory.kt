package com.example.loadinggallery.imagesScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loadinggallery.model.RepoInterface

class ImagesViewModelFactory (private val repo : RepoInterface) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ImagesViewModel::class.java)){
            ImagesViewModel(repo) as T
        }else{
            throw IllegalArgumentException("view model class not found")
        }
    }
}