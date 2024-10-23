package com.sanapplications.jetkart.domain.use_case.get_product_detail

import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.domain.repository.ProductRepository
import com.sanapplications.jetkart.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(productId: Int): Flow<Resource<ProductModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getProductDetail(productId)
            emit(Resource.Success(data = data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}