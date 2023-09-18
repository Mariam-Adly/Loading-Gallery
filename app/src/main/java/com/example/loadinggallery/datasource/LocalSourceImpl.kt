package com.example.loadinggallery.datasource

import android.app.Activity
import android.content.Context
import android.provider.MediaStore
import com.example.loadinggallery.model.Pojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalSourceImpl(context: Context) : LocalSource {


    override suspend fun getImagesFromGallery(activity: Activity): Flow<List<Pojo>> {
        val images = mutableListOf<Pojo>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            ""
        )
        cursor?.use {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (cursor.moveToNext()) {
                val imagePath = cursor.getString(columnIndex)
                val imageModel = Pojo(imagePath)
                images.add(imageModel)
            }
        }
        val imagesColl : Flow<List<Pojo>> = flow {
            emit(images)
        }
        return imagesColl
    }

    override suspend fun getVideosFromGallery(activity: Activity): Flow<List<Pojo>> {
        val videos = mutableListOf<Pojo>()
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = activity.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.use {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            while (cursor.moveToNext()) {
                val videoPath = cursor.getString(columnIndex)
                val videoModel = Pojo(videoPath)
                videos.add(videoModel)
            }
        }
        val videosColl : Flow<List<Pojo>> = flow {
            emit(videos)
        }
        return videosColl
    }
}