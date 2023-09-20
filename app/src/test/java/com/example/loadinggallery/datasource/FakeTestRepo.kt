package com.example.loadinggallery.datasource

import android.app.Activity
import com.example.loadinggallery.model.Pojo
import com.example.loadinggallery.model.RepoInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeTestRepo(private var localSource: LocalSource) : RepoInterface {
    override suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>> {
       return localSource.getImagesFromGallery(activity)
    }

    override suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>> {
        return localSource.getVideosFromGallery(activity)
    }
}