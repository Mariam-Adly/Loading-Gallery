package com.example.loadinggallery.imagesScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loadinggallery.model.Pojo
import com.example.loadinggallery.databinding.RvItemBinding

class ImagesAdapter (var images : List<Pojo>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {


    class ViewHolder (val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(imageModel: Pojo) {
                Glide.with(itemView)
                    .load(imageModel.galleryPath)
                    .into(binding.imgItem)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemBinding.inflate(inflater, parent, false)
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
