package com.dracul.techtask.domain.models

data class FilterChip(val name: String, val isChecked: Boolean)

fun List<String>.toFilterChip() = map { FilterChip(it, false) }
fun List<String>.toFilterChipChecked(name: String) = map {
    if (it == name) FilterChip(it, true) else FilterChip(it, false)
}