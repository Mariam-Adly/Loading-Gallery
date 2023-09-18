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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loadinggallery.databinding.FragmentImagesBinding
import com.example.loadinggallery.model.Pojo

private val REQUEST_CODE = 500

class ImagesFragment : Fragment() {

    lateinit var imagesAdapter: ImagesAdapter
    lateinit var binding : FragmentImagesBinding


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
        submitImages()
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
        fetchImages()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            checkPermission()
            fetchImages()
        }
    }

    private fun fetchImages(): List<Pojo> {
        val images = mutableListOf<Pojo>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireActivity().contentResolver.query(
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
        return images
    }
    fun submitImages(){
        imagesAdapter = ImagesAdapter(fetchImages())
        binding.rvImages.adapter = imagesAdapter
        binding.rvImages.layoutManager = GridLayoutManager(requireContext(), 3)
    }


}