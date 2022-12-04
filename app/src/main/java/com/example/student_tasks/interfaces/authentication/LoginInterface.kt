package com.example.student_tasks.interfaces.authentication

import com.example.student_tasks.data.model.AuthResponse
import com.example.student_tasks.data.model.LoginRequest
import retrofit2.Response

interface LoginInterface {
    suspend fun LoginUser(loginRequest: LoginRequest): Response<AuthResponse>?
    //suspend fun checkTokenValidity(token: String): Response<>
}