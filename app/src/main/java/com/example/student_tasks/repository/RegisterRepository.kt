package com.example.student_tasks.repository

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RegisterRequest
import com.example.student_tasks.interfaces.authentication.RegisterInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response

class RegisterRepository : RegisterInterface {
    override suspend fun RegisterUser(registerRequest: RegisterRequest): Response<AuthResponse>? {
        return AuthService.getApi()?.register(registerRequest = registerRequest)
    }
}