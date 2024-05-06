package com.dracul.techtask.data.storage.interfaces

import com.dracul.techtask.domain.models.Page

interface CategoriesStorage {
    suspend fun get(): Pair<List<String>, String?>
}