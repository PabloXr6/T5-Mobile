package com.example.apppasien.model // Sesuaikan dengan package-mu

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: AuthData?
)

data class AuthData(
    val token: String,
    val user: User
)

data class User(
    val name: String,
    val email: String
)