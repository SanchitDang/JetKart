package com.sanapplications.jetkart.domain.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow(listOf<ProductModel>()) // Your product list
    val products: StateFlow<List<ProductModel>> = _products

    private val _favorites = MutableStateFlow(mutableSetOf<ProductModel>())
    val favorites: StateFlow<MutableSet<ProductModel>> = _favorites

    fun toggleFavorite(product: ProductModel) {
        _favorites.value = _favorites.value.toMutableSet().apply {
            if (contains(product)) remove(product) else add(product)
        }
    }
}
