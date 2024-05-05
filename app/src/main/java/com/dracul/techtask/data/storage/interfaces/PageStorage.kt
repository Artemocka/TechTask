package com.dracul.techtask.data.storage.interfaces

import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.Products

interface PageStorage {
    suspend fun get(page: Page): Pair<List<Product>, String?>
}