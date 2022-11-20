package com.example.student_tasks.repository

import com.example.student_tasks.data.model.UsersListResponse
import com.example.student_tasks.network.AuthService
import retrofit2.Response

class StudentListRepository {
    suspend fun updateUsersList(): Response<UsersListResponse>? {
        return AuthService.getApi()?.updateUsersList()
    }
}