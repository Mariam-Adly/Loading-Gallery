package com.example.loadinggallery.videosScreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loadinggallery.databinding.FragmentVideosBinding
import com.example.loadinggallery.datasource.LocalSourceImpl
import com.example.loadinggallery.imagesScreen.ApiState
import com.example.loadinggallery.model.Repository
import com.example.loadinggallery.videosScreen.viewmodel.VideoViewModel
import com.example.loadinggallery.videosScreen.viewmodel.VideoViewModelFactory

class VideosFragment : Fragment() {

     lateinit var videoAdapter: VideoAdapter
     lateinit var binding : FragmentVideosBinding
     lateinit var videoVM : VideoViewModel
     lateinit var videoVMF : VideoViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        videoVMF = VideoViewModelFactory(
            Repository.getInstance(
               LocalSourceImpl(requireContext())
            ))

        videoVM = ViewModelProvider(this,videoVMF).get(VideoViewModel::class.java)
        submitVideos()
        videoVM.getVideosFromGallery(requireActivity())
        lifecycleScope.launchWhenStarted {
            videoVM.galleryItem.collect{
                when(it){
                    is ApiState.Loading->{
                        binding.videoItem.isVisible = false
                    }
                    is ApiState.Failure -> {
                        binding.videoItem.isVisible = false
                    }
                    is ApiState.Success->{
                        binding.videoItem.isVisible = true
                        videoAdapter.submitList(it.data)
                        videoAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }
        return binding.root
    }



    fun submitVideos(){
        videoAdapter = VideoAdapter()
        binding.videoItem.adapter = videoAdapter
        binding.videoItem.layoutManager = GridLayoutManager(requireContext(), 3)
    }


}