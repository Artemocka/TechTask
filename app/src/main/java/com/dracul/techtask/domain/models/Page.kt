package com.dracul.techtask.domain.models

data class Page(
    val index: Int = 0,
) {
    fun getLimit(): Int {
        return 20
    }
    fun getSkip(): Int {
        return index * 20
    }
}