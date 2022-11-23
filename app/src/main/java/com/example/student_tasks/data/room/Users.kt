package com.example.student_tasks.data.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) var ID: Int? = null,
    var userName: String?,
    var userEmail: String?
)
