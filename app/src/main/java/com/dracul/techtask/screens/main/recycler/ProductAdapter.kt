package com.dracul.techtask.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.dracul.techtask.databinding.ItemProductBinding
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.screens.main.imagerecycler.ImageAdapter


class ProductAdapter(
    val listener:OnItemListener
) : ListAdapter<Product, ProductAdapter.ViewHolder>(ProductItemCallBack()){

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        if (position == itemCount.dec()) {
            listener.onEnd()
        }
    }


    class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val adapter = ImageAdapter()
        fun bind(item: Product) {
            adapter.submitList(item.images)
            binding.run {
                price.text = "$${item.price}"
                title.text = item.title
                rvThumbnails.adapter= adapter
                rvThumbnails.onFlingListener = null
                rvThumbnails.setHasFixedSize(true)
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rvThumbnails)
            }
        }
    }
    interface OnItemListener {
        fun onEnd()
    }
}

