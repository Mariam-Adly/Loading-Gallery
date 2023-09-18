package com.example.loadinggallery.imagesScreen.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loadinggallery.databinding.FragmentImagesBinding
import com.example.loadinggallery.datasource.LocalSourceImpl
import com.example.loadinggallery.imagesScreen.viewmodel.ImagesViewModel
import com.example.loadinggallery.imagesScreen.viewmodel.ImagesViewModelFactory
import com.example.loadinggallery.model.Repository
import com.example.loadinggallery.utility.State


private val REQUEST_CODE = 500

class ImagesFragment : Fragment() {

    lateinit var imagesAdapter: ImagesAdapter
    lateinit var binding : FragmentImagesBinding
    lateinit var imagesVM : ImagesViewModel
    lateinit var imagesVMF : ImagesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        checkPermission()
        imagesVMF = ImagesViewModelFactory(
            Repository.getInstance(
                LocalSourceImpl(requireContext())
            ))

        imagesVM = ViewModelProvider(this,imagesVMF).get(ImagesViewModel::class.java)
        submitImages()
        imagesVM.getImagesFromGallery(requireActivity())
        lifecycleScope.launchWhenStarted {
            imagesVM.galleryItem.collect{
                when(it){
                    is State.Loading->{
                        binding.rvImages.isVisible = false
                    }
                    is State.Failure -> {
                        binding.rvImages.isVisible = false
                    }
                    is State.Success->{
                        binding.rvImages.isVisible = true
                        imagesAdapter.submitList(it.data)
                        imagesAdapter.notifyDataSetChanged()
                    }
                    is State.Empty->{

                    }
                }
            }
        }
        return binding.root
    }

    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                requireActivity(),
                Array(1) { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            checkPermission()
        }
    }


    fun submitImages(){
        imagesAdapter = ImagesAdapter()
        binding.rvImages.adapter = imagesAdapter
        binding.rvImages.layoutManager = GridLayoutManager(requireContext(), 3)
    }


}