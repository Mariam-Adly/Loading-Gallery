package com.example.loadinggallery.imagesScreen.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loadinggallery.R
import com.example.loadinggallery.databinding.ImageItemBinding
import com.example.loadinggallery.model.Pojo

class ImagesAdapter() : ListAdapter<Pojo, ImagesAdapter.ViewHolder>(MyDifUnit()) {
    lateinit var contxt: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contxt=parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = ImageItemBinding.inflate(inflater,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(contxt).load(getItem(position).galleryPath)
            .into(holder.img)

    }


    inner class ViewHolder(private val itemView: ImageItemBinding): RecyclerView.ViewHolder(itemView.root){

        val img : ImageView
            get() = itemView.findViewById(R.id.img_item)

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