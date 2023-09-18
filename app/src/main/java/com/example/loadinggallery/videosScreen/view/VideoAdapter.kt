package com.example.loadinggallery.videosScreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loadinggallery.databinding.VideoItemBinding
import com.example.loadinggallery.model.Pojo

class VideoAdapter (var videos : List<Pojo>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {


    class ViewHolder (val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imageModel: Pojo) {
            Glide.with(itemView)
                .load(imageModel.galleryPath)
                .skipMemoryCache(false)
                .into(binding.videoItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = VideoItemBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = videos[position]
        holder.bind(item)
    }


}
