package com.dracul.techtask.screens.main.chiprecycler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dracul.techtask.R
import com.dracul.techtask.databinding.ItemChipBinding
import com.dracul.techtask.domain.models.FilterChip
import com.google.android.material.chip.Chip


class ChipsAdapter(private val listener: OnChipListner) : ListAdapter<FilterChip, ChipsAdapter.ViewHolder>(ChipsDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).name.hashCode().toLong()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            listener.onChipChecked((it as Chip).text.toString())
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemChipBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterChip) {
            binding.run {
                chip.text = item.name
                chip.isChecked = item.isChecked
            }
        }

    }

    interface OnChipListner {
        fun onChipChecked(category: String)
    }

}