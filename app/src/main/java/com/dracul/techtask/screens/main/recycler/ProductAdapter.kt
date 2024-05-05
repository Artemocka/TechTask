package com.dracul.techtask.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dracul.techtask.databinding.ItemProductBinding
import com.dracul.techtask.domain.models.Product


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
        fun bind(item: Product) {
            binding.run {
                price.text = "$${item.price}"
                title.text = item.title



                Glide
                    .with(binding.root)
                    .load(item.thumbnail)
                    .into(thumbnail)
            }
        }
    }
    interface OnItemListener {
        fun onEnd()
    }

}

