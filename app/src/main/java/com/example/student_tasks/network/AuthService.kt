package com.example.student_tasks.network

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RegisterRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    companion object {
        fun getApi(): AuthService? {
            return ApiClient.client?.create(AuthService::class.java)
        }
    }
}