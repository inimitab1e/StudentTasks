package com.example.student_tasks.data.room

import android.content.Context
import androidx.annotation.WorkerThread

class UserRepository(context: Context) {

    var db: UsersDAO = AppDatabase.getInstance(context)?.UsersDAO()!!


    //Fetch All the Users
    fun getAllUsers(): List<Users> {
        return db.getAllUsers()
    }

    // Insert new user
    fun insertUsers(user: Users) {
        db.insertUsers(user)
    }

    // update user
    fun updateUsers(user: Users) {
        db.updateUsers(user)
    }

    // Delete user
    fun deleteUsers(user: Users) {
        db.deleteUsers(user)
    }

    fun deleteAll() {
        db.deleteAll()
    }

    @WorkerThread
    fun search(desc : String) : List<Users> {
        return db.getSearchResults(desc)
    }
}