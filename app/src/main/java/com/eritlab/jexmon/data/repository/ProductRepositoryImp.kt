package com.sanapplications.jetkart.data.repository

import com.sanapplications.jetkart.data.demo_db.DemoDB
import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val demoDB: DemoDB
) : ProductRepository {
    override suspend fun getProduct(): List<ProductModel> {
        return demoDB.getProduct()
    }

    override suspend fun getProductDetail(id: Int): ProductModel {
        return demoDB.getProduct()[id-1]
    }
}