package com.example.student_tasks.data.room


import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.student_tasks.MainActivity

class StudentRepository(context: MainActivity) {

    var db: UsersDAO = AppDatabase.getInstance(context)?.UsersDAO()!!


    //Fetch All the Users
    fun getAllStudents(): List<Users> {
        return db.getAllStudents()
    }

    // Insert new user
    fun insertStudent(user: Users) {
        db.insertStudent(user)
    }

    // update user
    fun updateStudent(user: Users) {
        db.updateStudent(user)
    }

    // Delete user
    fun deleteStudent(user: Users) {
        db.deleteStudent(user)
    }

    @WorkerThread
    fun search(desc : String) : List<Users> {
        return db.getSearchResults(desc)
    }
}