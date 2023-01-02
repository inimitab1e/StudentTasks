package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RegisterRequest
import com.example.student_tasks.interfaces.authentication.RegisterInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val authService: AuthService
): RegisterInterface {
    override suspend fun RegisterUser(registerRequest: RegisterRequest): Response<AuthResponse> {
        return authService.register(registerRequest = registerRequest)
    }
}