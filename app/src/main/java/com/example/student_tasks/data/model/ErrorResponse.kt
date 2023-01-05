package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String
)
