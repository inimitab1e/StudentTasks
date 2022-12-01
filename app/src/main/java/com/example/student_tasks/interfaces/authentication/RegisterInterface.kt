package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.RegisterRequest
import retrofit2.Response

interface RegisterInterface {
    suspend fun RegisterUser(registerRequest: RegisterRequest): Response<AuthResponse>?
}