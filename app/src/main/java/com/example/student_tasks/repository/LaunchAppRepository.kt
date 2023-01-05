package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.ErrorResponse
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.network.AuthService
import com.example.student_tasks.network.exceptions.NetworkResponse
import javax.inject.Inject

class LaunchAppRepository @Inject constructor(
    private val authService: AuthService
) : LaunchAppInterface {
    override suspend fun checkTokenValidity():
            NetworkResponse<ValidityResponse, ErrorResponse> =
        authService.checkAccessTokenValidity()

    override suspend fun refreshTokens(refreshRequest: RefreshRequest):
            NetworkResponse<AuthResponse, ErrorResponse> =
        authService.refreshTokens(refreshRequest = refreshRequest)
}