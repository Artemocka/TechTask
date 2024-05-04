package com.dracul.techtask.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dracul.techtask.databinding.ItemProductBinding
import com.example.technotestvk.data.Product


class ProductAdapter : ListAdapter<Product, ProductAdapter.ViewHolder>(ProductItemCallBack()) {

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
    }


    class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.run {
                price.text = "$${item.price}"
                title.text = item.title
                description.text = item.description
                Glide
                    .with(binding.root)
                    .load(item.thumbnail)
                    .into(thumbnail)
            }
        }
    }
}


