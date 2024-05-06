package com.dracul.techtask.screens.main.imagerecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dracul.techtask.databinding.ItemImageBinding


class ImageAdapter : ListAdapter<String, ImageAdapter.ViewHolder>(ImageItemCallBack()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].hashCode().toLong()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)


        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)

    }


    class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.run {
                Glide.with(binding.root).load(item).transition(DrawableTransitionOptions.withCrossFade()) .into(thumbnail)
            }
        }
    }



}

