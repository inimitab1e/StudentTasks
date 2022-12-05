package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import retrofit2.Response

interface LoginInterface {
    suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse>?

    suspend fun checkTokenValidity(token: String): Response<ValidityResponse>?

    suspend fun refreshTokens(token: String, refreshRequest: RefreshRequest) : Response<AuthResponse>?
}