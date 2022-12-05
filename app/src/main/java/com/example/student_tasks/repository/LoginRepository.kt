package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response

class LoginRepository : LoginInterface {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse>? {
        return AuthService.getApi()?.login(loginRequest = loginRequest)
    }

    override suspend fun checkTokenValidity(token: String): Response<ValidityResponse>? {
        return AuthService.getApi()?.checkAccessTokenValidity(token = token)
    }

    override suspend fun refreshTokens(token: String, refreshRequest: RefreshRequest) : Response<AuthResponse>? {
        return AuthService.getApi()?.refreshTokens(token = token, refreshRequest = refreshRequest)
    }
}