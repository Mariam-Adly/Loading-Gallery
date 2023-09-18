package com.example.loadinggallery.imagesScreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loadinggallery.databinding.ImageItemBinding
import com.example.loadinggallery.model.Pojo

class ImagesAdapter (var images : List<Pojo>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {


    class ViewHolder (val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(imageModel: Pojo) {
                Glide.with(itemView)
                    .load(imageModel.galleryPath)
                    .into(binding.imgItem)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }


}
