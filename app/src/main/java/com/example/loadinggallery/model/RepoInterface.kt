package com.example.loadinggallery.model

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>>
    suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>>
}