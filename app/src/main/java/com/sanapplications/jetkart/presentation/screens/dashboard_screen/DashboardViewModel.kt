package com.sanapplications.jetkart.presentation.screens.dashboard_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanapplications.jetkart.domain.use_case.get_product.GetProductUseCase
import com.sanapplications.jetkart.common.Resource
import com.sanapplications.jetkart.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productUseCase: GetProductUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProductState())
    val state: State<ProductState> = _state

    init {
        getProduct()
    }

    private fun getProduct() {
        productUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ProductState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ProductState(product = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductState(errorMessage = result.message ?: "Unexpected error.")
                }
            }
        }.launchIn(viewModelScope)
    }

    // Toggle isFavourite for a product by ID
    fun toggleFavorite(productId: Int) {
        _state.value = _state.value.copy(
            product = _state.value.product?.map { product ->
                if (product.id == productId) {
                    Log.d("",productId.toString())
                    product.copy(isFavourite = !product.isFavourite)
                } else {
                    product
                }
            }
        )
    }
}
