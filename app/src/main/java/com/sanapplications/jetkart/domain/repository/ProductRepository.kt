package com.sanapplications.jetkart.domain.repository

import com.sanapplications.jetkart.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProduct(): List<ProductModel>? = null
    suspend fun getProductDetail(id: Int): ProductModel? = null
}