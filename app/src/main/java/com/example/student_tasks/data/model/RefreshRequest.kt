package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class RefreshRequest(
    @SerializedName("email")
    val email: String
)
