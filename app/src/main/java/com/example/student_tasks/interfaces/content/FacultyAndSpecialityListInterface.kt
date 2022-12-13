package com.example.student_tasks.interfaces.content

import android.content.Context
import com.example.student_tasks.data.model.FacultyAndSpecialityModel

interface FacultyAndSpecialityListInterface {
    fun fetchFacAndSpec(context: Context): FacultyAndSpecialityModel
}