package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class ValidityResponse(
    @SerializedName("message")
    val message: String,
    val errorResponse: ErrorResponse
)
