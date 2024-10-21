package com.sanapplications.jetkart.presentation.screens.product_detail_screen

import com.sanapplications.jetkart.domain.model.ProductModel

data class ProductDetailState(
    val isLoading: Boolean = false,
    val productDetail: ProductModel? = null,
    val errorMessage: String = ""
)