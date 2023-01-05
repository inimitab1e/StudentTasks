package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.ErrorResponse
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.network.exceptions.NetworkResponse
import retrofit2.Response

interface LaunchAppInterface {
    suspend fun checkTokenValidity():
            NetworkResponse<ValidityResponse, ErrorResponse>

    suspend fun refreshTokens(refreshRequest: RefreshRequest):
            NetworkResponse<AuthResponse, ErrorResponse>
}