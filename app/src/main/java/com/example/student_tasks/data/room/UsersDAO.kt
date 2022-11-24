package com.example.student_tasks.data.room

import androidx.room.*

@Dao
interface UsersDAO {

    @Insert
    fun insertUsers(user: Users)

    @Query("Select * from users")
    fun getAllUsers(): List<Users>

    @Update
    fun updateUsers(user: Users)

    @Delete
    fun deleteUsers(user: Users)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("Select * from users where userName like  :desc")
    fun getSearchResults(desc : String) : List<Users>
}