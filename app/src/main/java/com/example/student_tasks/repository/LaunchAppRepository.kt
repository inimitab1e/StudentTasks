package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response

class LaunchAppRepository: LaunchAppInterface {
    override suspend fun checkTokenValidity(token: String): Response<ValidityResponse>? {
        return AuthService.getApi()?.checkAccessTokenValidity(token = token)
    }

    override suspend fun refreshTokens(token: String, refreshRequest: RefreshRequest) : Response<AuthResponse>? {
        return AuthService.getApi()?.refreshTokens(token = token, refreshRequest = refreshRequest)
    }
}