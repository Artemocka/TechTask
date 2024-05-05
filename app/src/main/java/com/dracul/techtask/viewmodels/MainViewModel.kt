package com.dracul.techtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracul.techtask.di.DaggerInjector
import com.dracul.techtask.di.components.main.DaggerMainComponent
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.usecase.GetPageUseCase
import com.dracul.techtask.screens.main.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var getPageUseCase: GetPageUseCase
    private var page = Page(0)

    val listProduct = MutableStateFlow<List<Product>>(emptyList())
    val error = MutableStateFlow<String>("")
    val state = MutableStateFlow<State>(State.Main)

    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch(Dispatchers.Main.immediate) {
            val pair = getPageUseCase.execute(page)
            listProduct.value += pair.first
            when {
                pair.second != null && listProduct.value.isEmpty() -> {
                    state.value = State.Error
                    error.value = pair.second!!
                }
                pair.second == null -> {
                    state.value = State.Main
                }
               pair.second!=null -> {
                    error.value = pair.second!!
                }
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
    }

}