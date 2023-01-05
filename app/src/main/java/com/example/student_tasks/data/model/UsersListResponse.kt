package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class UsersListResponse(
    @SerializedName("usersResponseList")
    val usersResponseList: List<String>,
    val errorResponse: ErrorResponse
)
