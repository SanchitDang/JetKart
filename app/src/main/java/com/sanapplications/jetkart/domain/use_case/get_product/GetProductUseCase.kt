package com.sanapplications.jetkart.domain.use_case.get_product

import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.domain.repository.ProductRepository
import com.sanapplications.jetkart.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductModel>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProduct()?.map { it }
            emit(Resource.Success(data = products))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}