package com.example.apppasien.network

import com.example.apppasien.model.LoginRequest
import com.example.apppasien.model.LoginResponse
import com.example.apppasien.model.PasienResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/pasien")
    suspend fun getPasien(@Header("Authorization") token: String): Response<PasienResponse>
}