package com.example.student_tasks.network

import com.example.student_tasks.data.model.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("/register")
    suspend fun searchPhotos(
        @Query("query") query: String,
    ): AuthResponse

    companion object {
        private const val BASE_URL = "http://0.0.0.0:8080"

        fun create(): AuthService {
            val logger = HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthService::class.java)
        }
    }
}