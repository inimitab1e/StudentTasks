package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.data.model.ValidityResponse
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val authService: AuthService
) : LoginInterface {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse> {
        return authService.login(loginRequest = loginRequest)
    }
}