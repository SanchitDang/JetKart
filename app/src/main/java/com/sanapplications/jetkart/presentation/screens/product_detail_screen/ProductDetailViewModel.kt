package com.sanapplications.jetkart.presentation.screens.product_detail_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sanapplications.jetkart.domain.model.ProductModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(ProductDetailState())
    val state: State<ProductDetailState> = _state

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getProductDetail(productId: String) {
        _state.value = ProductDetailState(isLoading = true)

        firestore.collection("products").document(productId).get()
            .addOnSuccessListener { document ->
                val productDetail = document?.toObject(ProductModel::class.java)

                if (productDetail != null) {
                    _state.value = ProductDetailState(productDetail = productDetail)
                    Log.d("ProductDetail", "Product ID: ${productDetail.id}")
                } else {
                    _state.value = ProductDetailState(errorMessage = "Product not found.")
                }
            }
            .addOnFailureListener { exception ->
                _state.value = ProductDetailState(errorMessage = exception.message ?: "Error fetching product.")
            }
    }
}
