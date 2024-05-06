package com.dracul.techtask.domain.models

data class FilterChip(val name: String, val isChecked: Boolean)

fun List<String>.toFilterChip() = map { FilterChip(it, false) }