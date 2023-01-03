package com.example.student_tasks.interfaces.content

import com.example.student_tasks.data.model.UsersListResponse
import com.example.student_tasks.data.room.Users
import retrofit2.Response

interface StudentListInterface {
    suspend fun getRemoteUsersList(): Response<UsersListResponse>?
    fun updateLocalList(list: List<String>?, size: Int?)
    fun getLocalList(): List<Users>
}