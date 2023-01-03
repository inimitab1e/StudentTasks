package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response
import javax.inject.Inject

class LaunchAppRepository @Inject constructor(
    private val authService: AuthService
): LaunchAppInterface {
    override suspend fun checkTokenValidity(): Response<ValidityResponse> {
        return authService.checkAccessTokenValidity()
    }

    override suspend fun refreshTokens(refreshRequest: RefreshRequest) : Response<AuthResponse> {
        return authService.refreshTokens(refreshRequest = refreshRequest)
    }
}