package com.dracul.techtask.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracul.techtask.di.DaggerInjector
import com.dracul.techtask.di.components.main.DaggerMainComponent
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.models.toFilterChip
import com.dracul.techtask.domain.models.toFilterChipChecked
import com.dracul.techtask.domain.usecase.GetCategoriesUseCase
import com.dracul.techtask.domain.usecase.GetPageUseCase
import com.dracul.techtask.screens.main.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class MainViewModel : ViewModel() {
    @Inject
    lateinit var getPageUseCase: GetPageUseCase

    @Inject
    lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private var page = Page(0)
    var selectedCategory = MutableStateFlow<String?>(null)
    private var _categories = emptyList<String>()
    var categories = MutableStateFlow(_categories.toFilterChip())
    val listProduct = MutableStateFlow<List<Product>>(emptyList())
    val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val state = MutableStateFlow(State.Main)

    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
        getProducts()
        getCategories()
    }

    private fun getProducts() {

        viewModelScope.launch {
            val pair = getPageUseCase.execute(page)
            when {
                pair.second != null && listProduct.value.isEmpty() -> {
                    state.value = State.Error
                    error.emit(pair.second!!)
                    val index = page.index
                    page = page.copy(index = index.dec())
                }

                pair.second != null -> {
                    error.emit(pair.second!!)
                    val index = page.index
                    page = page.copy(index = index.dec())
                }

                pair.second == null -> {
                    state.value = State.Main
                    listProduct.value += pair.first
                }
            }
        }
    }

    private fun getCategories() {

        viewModelScope.launch(Dispatchers.Main.immediate) {
            val response = getCategoriesUseCase.execute()
            if (response.second == null) {
                _categories = response.first
                categories.value = _categories.toFilterChip()
            } else {
                error.emit(response.second!!)
            }
        }
    }

    fun nextPage() {
        val index = page.index
        page = page.copy(index = index.inc())
        getProducts()
    }

    fun reloadPage() {
        getProducts()
        getCategories()
    }

    fun setCategoryFilter(category: String) {
        selectedCategory.value = when (selectedCategory.value) {
            category -> {
                categories.value = _categories.toFilterChip()
                null
            }
            else -> {
                categories.value =_categories.toFilterChipChecked(category)
                category
            }
        }

    }

}
