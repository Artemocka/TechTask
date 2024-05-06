package com.dracul.techtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracul.techtask.di.DaggerInjector
import com.dracul.techtask.di.components.main.DaggerMainComponent
import com.dracul.techtask.domain.models.Page
import com.dracul.techtask.domain.models.Product
import com.dracul.techtask.domain.usecase.GetPageUseCase
import com.dracul.techtask.screens.main.state.State
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var getPageUseCase: GetPageUseCase
    private var page = Page(0)

    val listProduct = MutableStateFlow<List<Product>>(emptyList())
    val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val state = MutableStateFlow<State>(State.Main)

    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
        getProducts()
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

    fun nextPage() {
        val index = page.index
        page = page.copy(index = index.inc())
        getProducts()
    }

    fun reloadPage() {
        getProducts()
    }


}
