package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
