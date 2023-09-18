package com.example.loadinggallery.videosScreen.view

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loadinggallery.databinding.FragmentVideosBinding
import com.example.loadinggallery.model.Pojo


class VideosFragment : Fragment() {

     lateinit var videoAdapter: VideoAdapter
     lateinit var binding : FragmentVideosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        submitImages()
        return binding.root
    }


    private fun getVideosFromGallery(): List<Pojo> {
        val videos = mutableListOf<Pojo>()
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = requireActivity().contentResolver.query(
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
        return videos
    }

    fun submitImages(){
        videoAdapter = VideoAdapter(getVideosFromGallery())
        binding.videoItem.adapter = videoAdapter
        binding.videoItem.layoutManager = GridLayoutManager(requireContext(), 3)
    }


}