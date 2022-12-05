package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.data.model.RefreshRequest
import retrofit2.Response

interface LoginInterface {
    suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse>?

    suspend fun checkTokenValidity(token: String): Response<String>?

    suspend fun refreshTokens(refreshRequest: RefreshRequest) : Response<AuthResponse>?
}