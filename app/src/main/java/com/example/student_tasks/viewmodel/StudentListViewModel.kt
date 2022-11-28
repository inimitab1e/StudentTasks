package com.example.student_tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.repository.StudentListRepository
import kotlinx.coroutines.launch

class StudentListViewModel(app: Application): AndroidViewModel(app) {

    private val repo = StudentListRepository()
    private var users: Users? = null
    private val roomRepo: UserRepository by lazy {
        UserRepository(getApplication())
    }

    private var _userList = MutableLiveData<List<Users>>()
    val userList get() = _userList

    fun updateList() {
        viewModelScope.launch {
            roomRepo.deleteAll()
            val response = repo.updateUsersList()
            val listOfUsers = response?.body()?.usersResponseList
            addUsers(listOfUsers, listOfUsers?.size)
            _userList.value = roomRepo.getAllUsers()
        }
    }

    private fun addUsers(list: List<String>?, size: Int?) {
        var i = 0
        if (size != null && list != null) {
            while (i < size - 1) {
                users?.let {
                    var user = Users(
                        userName = list[i],
                        userEmail = list[i+1]
                    )
                    roomRepo.updateUsers(user)
                } ?: kotlin.run {
                    val user = Users(
                        userName = list[i],
                        userEmail = list[i+1]
                    )
                    roomRepo.insertUsers(user)
                }
                i += 2
            }
        }
    }
}