package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.repository.StudentListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val roomRepo: UserRepository,
    private val repo: StudentListRepository
): ViewModel() {

    private var users: Users? = null

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