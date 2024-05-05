package com.dracul.techtask.data.storage.impl

import com.dracul.techtask.data.api.ProductApi
import com.dracul.techtask.data.storage.interfaces.PageStorage
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.Products
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkPageStorage @Inject constructor(
    val api: ProductApi,
) : PageStorage {
    override suspend fun get(page: Page): Pair<List<Product>, String?> {

        var products: List<Product> = emptyList()
        var errorMessage:String? = null

        try {
            products= api.getPage(page.getSkip(), page.getLimit()).products
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