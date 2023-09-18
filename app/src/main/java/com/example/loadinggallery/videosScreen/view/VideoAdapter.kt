package com.example.loadinggallery.videosScreen.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loadinggallery.R
import com.example.loadinggallery.databinding.VideoItemBinding
import com.example.loadinggallery.model.Pojo

class VideoAdapter() : ListAdapter<Pojo, VideoAdapter.ViewHolder>(MyDifUnit()) {
    lateinit var contxt: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contxt=parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(contxt).load(getItem(position).galleryPath)
            .into(holder.video)

    }


    inner class ViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView){

        val video : ImageView
            get() = itemView.findViewById(R.id.video_item)

    }
}


class MyDifUnit: DiffUtil.ItemCallback<Pojo>() {
    override fun areItemsTheSame(oldItem: Pojo, newItem: Pojo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pojo, newItem: Pojo): Boolean {
        return oldItem == newItem
    }

}