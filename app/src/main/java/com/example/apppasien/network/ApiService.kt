package com.example.apppasien.network

import com.example.apppasien.model.LoginRequest
import com.example.apppasien.model.LoginResponse
import com.example.apppasien.model.PasienCreateRequest
import com.example.apppasien.model.PasienResponse
import com.example.apppasien.model.PasienSingleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/pasien")
    suspend fun getPasien(@Header("Authorization") token: String): Response<PasienResponse>

    @POST("api/pasien")
    suspend fun createPasien(
        @Header("Authorization") token: String,
        @Body request: PasienCreateRequest
    ): Response<PasienSingleResponse>

    @PUT("api/pasien/{id}")
    suspend fun updatePasien(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: PasienCreateRequest
    ): Response<PasienSingleResponse>

    @DELETE("api/pasien/{id}")
    suspend fun deletePasien(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<PasienResponse>
}