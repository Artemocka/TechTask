package com.dracul.techtask.data.storage.interfaces

import com.dracul.techtask.domain.models.Page

interface CategoriesStorage {
    suspend fun get(page: Page): Pair<List<String>, String?>
}