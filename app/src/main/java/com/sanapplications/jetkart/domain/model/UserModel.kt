package com.sanapplications.jetkart.domain.model

data class UserModel(
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val userImageUrl: String = ""
)