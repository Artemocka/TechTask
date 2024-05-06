package com.dracul.techtask.data.storage.impl

import com.dracul.techtask.data.api.ProductApi
import com.dracul.techtask.data.storage.interfaces.CategoriesStorage
import com.dracul.techtask.domain.models.Page
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkCategoryStorage @Inject constructor(
    val api: ProductApi,
) : CategoriesStorage {
    override suspend fun get(page: Page): Pair<List<String>, String?> {

        var products: List<String> = emptyList()
        var errorMessage:String? = null

        try {
            products= api.getCategories()
        } catch (e: UnknownHostException) {
            errorMessage ="No internet connections!"
        } catch (e: SocketTimeoutException) {
            errorMessage="Bad internet connections"
        } catch (e: Exception) {
            errorMessage="Unknown error"
        }

        return Pair(products, errorMessage)
    }
}