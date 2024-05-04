package com.dracul.techtask.domain.repository

import com.dracul.techtask.domain.models.Page
import com.example.technotestvk.data.Products

interface GetPageRepo {
    suspend fun get(page: Page):Products
}