package com.example.student_tasks.repository

import android.content.Context
import com.example.student_tasks.data.model.FacultyAndSpecialityModel
import com.example.student_tasks.interfaces.content.FacultyAndSpecialityListInterface
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class FacultyAndSpecialityListRepository : FacultyAndSpecialityListInterface {
    override fun fetchFacAndSpec(context: Context): FacultyAndSpecialityModel {
        val jsonString = context.assets.open("FacultiesAndSpecialities.json")
            .bufferedReader()
            .use { it.readText() }
        val gson = Gson()
        val objectFacAndSpecType: Type = object : TypeToken<FacultyAndSpecialityModel>() {}.type
        var facAndSpec = gson.fromJson<FacultyAndSpecialityModel>(jsonString, objectFacAndSpecType)
        return facAndSpec
    }
}