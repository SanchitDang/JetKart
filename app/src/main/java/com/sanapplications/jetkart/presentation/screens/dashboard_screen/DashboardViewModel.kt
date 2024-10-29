package com.sanapplications.jetkart.presentation.screens.dashboard_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sanapplications.jetkart.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _state = mutableStateOf(ProductState())
    val state: State<ProductState> = _state

    init {
        Log.d("hehhe", "ininni")
        getProductsFromDb()
    }

    private fun getProductsFromDb() {
        _state.value = ProductState(isLoading = true)

        firestore.collection("products")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val products = task.result?.mapNotNull { document ->
                        document.toObject(ProductModel::class.java)
                    } ?: emptyList()

                    // Log the first product if it exists
                    if (products.isNotEmpty()) {
                        Log.d("products length:", products.size.toString())
                    }

                    // Update state with fetched products
                    _state.value = ProductState(product = products, isLoading = false)
                } else {
                    // Handle failure
                    val errorMessage = task.exception?.message ?: "Error fetching products."
                    Log.e("FirestoreError", errorMessage) // Log the error
                    _state.value = ProductState(product = emptyList(), isLoading = false, errorMessage = errorMessage) // Update state with error
                }
            }
    }

    fun toggleFavorite(productId: String) {
        _state.value = _state.value.copy(
            product = _state.value.product?.map { product ->
                if (product.id == productId) {
                    Log.d("ToggleFavorite", productId.toString())
                    product.copy(isFavourite = !product.isFavourite)
                } else {
                    product
                }
            }
        )
    }
}
