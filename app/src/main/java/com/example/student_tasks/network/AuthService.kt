package com.example.student_tasks.network

import com.example.student_tasks.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @GET("/validity")
    suspend fun checkAccessTokenValidity(@Header("Authorization") token: String) : Response<ValidityResponse>

    @POST("/refresh")
    suspend fun refreshTokens(
        @Header("Authorization") token: String,
        @Body refreshRequest: RefreshRequest
    ): Response<AuthResponse>

    @GET("/users")
    suspend fun updateUsersList(@Header("Authorization") token: String): Response<UsersListResponse>
}