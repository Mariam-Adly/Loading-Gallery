package com.example.loadinggallery.model

import android.app.Activity
import com.example.loadinggallery.datasource.LocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Repository private constructor(var localSource: LocalSource) : RepoInterface{

    private val _galleryItem = MutableStateFlow(ResultData())
    var galleryItem = _galleryItem.asStateFlow()

    companion object {
        private var instance : Repository? = null
        fun getInstance(localSource: LocalSource):Repository{
            return instance?: synchronized(this){
                val temp = Repository(localSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>> {
        return localSource.getImagesFromGallery(activity)
    }

    override suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>> {
        return localSource.getVideosFromGallery(activity)
    }
}