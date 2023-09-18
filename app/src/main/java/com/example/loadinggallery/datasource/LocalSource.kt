package com.example.loadinggallery.datasource

import android.app.Activity
import com.example.loadinggallery.model.Pojo
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>>
    suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>>
}