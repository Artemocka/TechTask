package com.dracul.techtask.data.storage.interfaces

import com.dracul.techtask.domain.models.Page
import com.example.technotestvk.data.Products

interface PageStorage {
    suspend fun get(page: Page):Products
}