package com.example.loadinggallery.datasource

import android.app.Activity
import com.example.loadinggallery.model.Pojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalDataSource (private var list: MutableList<Pojo> = mutableListOf()) : LocalSource {
    override suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>> {
        return flowOf(list)
    }

    override suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>> {
        return flowOf(list)
    }
}