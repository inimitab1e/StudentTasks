package com.example.student_tasks.data.room

import androidx.room.*

@Dao
interface UsersDAO {

    @Insert
    fun insertStudent(user: Users)

    @Query("Select * from users")
    fun getAllStudents(): List<Users>

    @Update
    fun updateStudent(user: Users)

    @Delete
    fun deleteStudent(user: Users)

    @Query("Select * from users where userName like  :desc")
    fun getSearchResults(desc : String) : List<Users>
}