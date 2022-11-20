package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.network.AuthService
import retrofit2.Response

class LoginRepository {
    suspend fun LoginUser(loginRequest: LoginRequest): Response<AuthResponse>? {
        return AuthService.getApi()?.login(loginRequest = loginRequest)
    }
}