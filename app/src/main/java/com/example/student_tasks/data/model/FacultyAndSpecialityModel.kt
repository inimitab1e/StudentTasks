package com.example.student_tasks.data.model

import com.google.gson.annotations.SerializedName

data class FacultyAndSpecialityModel(
    @SerializedName("facultyList")
    val facultyList: List<Faculty>
) {
    data class Faculty(
        @SerializedName("facultyName")
        val facultyName: String,
        @SerializedName("specialityList")
        val specialityList: List<Speciality>
    ) {
        data class Speciality(
            @SerializedName("specialityName")
            val specialityName: String
        )
    }
}