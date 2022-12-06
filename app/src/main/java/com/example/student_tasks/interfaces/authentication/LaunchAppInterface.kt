package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import retrofit2.Response

interface LaunchAppInterface {
    suspend fun checkTokenValidity(token: String): Response<ValidityResponse>?

    suspend fun refreshTokens(token: String, refreshRequest: RefreshRequest) : Response<AuthResponse>?
}