package com.example.student_tasks.data.room

import androidx.annotation.WorkerThread
import com.example.student_tasks.MainActivity

class UserRepository(context: MainActivity) {

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

    @WorkerThread
    fun search(desc : String) : List<Users> {
        return db.getSearchResults(desc)
    }
}