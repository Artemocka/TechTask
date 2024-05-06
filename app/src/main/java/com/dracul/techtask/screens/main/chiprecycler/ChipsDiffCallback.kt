package com.dracul.techtask.screens.main.chiprecycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.techtask.domain.models.FilterChip


class ChipsDiffCallback: DiffUtil.ItemCallback<FilterChip>() {
    override fun areItemsTheSame(oldItem: FilterChip, newItem: FilterChip): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilterChip, newItem: FilterChip): Boolean {
        return oldItem == newItem
    }

}