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
    suspend fun checkAccessTokenValidity(@Header("Bearer") token: String) : Response<String>

    @POST("/refresh")
    suspend fun refreshTokens(@Body refreshRequest: RefreshRequest): Response<AuthResponse>

    @GET("/users")
    suspend fun updateUsersList(): Response<UsersListResponse>

    companion object {
        fun getApi(): AuthService? {
            return ApiClient.client?.create(AuthService::class.java)
        }
    }
}