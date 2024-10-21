package com.sanapplications.jetkart.presentation.screens.dashboard_screen

import com.sanapplications.jetkart.domain.model.ProductModel

data class ProductState(
    val isLoading: Boolean = false,
    val product: List<ProductModel>? = null,
    val errorMessage: String = ""
)
