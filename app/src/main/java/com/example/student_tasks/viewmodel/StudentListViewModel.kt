package com.example.student_tasks.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.MainActivity
import com.example.student_tasks.MainApplication
import com.example.student_tasks.adapters.StudentsListAdapter
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.repository.StudentListRepository
import kotlinx.coroutines.launch

class StudentListViewModel(): ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = MainApplication.appContext
    private val repo = StudentListRepository()
    private var users: Users? = null

    private val roomRepo: UserRepository by lazy {
        UserRepository(context)
    }

    private val usersAdapter: StudentsListAdapter by lazy {
        StudentsListAdapter()
    }

    fun updateList() {
        viewModelScope.launch {
            val response = repo.updateUsersList()
            val listOfUsers = response?.body()?.usersResponseList

            if (listOfUsers != null) {
                for (item in listOfUsers)
                users?.let {
                    var user = Users(
                        userName = item,
                        userEmail = item
                    )
                    roomRepo.updateUsers(user)
                } ?: kotlin.run {
                    val user = Users(
                        userName = item,
                        userEmail = item
                    )
                    roomRepo.insertUsers(user)
                }
                fetchUsers()
            }
        }
    }

    private fun fetchUsers() {
        val allUsers = roomRepo.getAllUsers()
        usersAdapter.setUsers(allUsers)
    }
}