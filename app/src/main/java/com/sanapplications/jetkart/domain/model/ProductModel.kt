package com.sanapplications.jetkart.domain.model

data class ProductModel(
    val id: String = "",
    val title: String = "",
    val images: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val rating: Double = 0.0,
    val price: Double = 0.0,
    val isFavourite: Boolean = false,
    val isPopular: Boolean = false,
    val description: String = ""
)